package com.aoeai.lcsr.service;

import com.aoeai.lcsr.bo.user.RegistOrLoginBo;
import com.aoeai.lcsr.dao.entity.LcsrUser;
import com.aoeai.lcsr.dao.service.ILcsrUserService;
import com.aoeai.lcsr.utils.Cipher;
import com.aoeai.lcsr.utils.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private ILcsrUserService lcsrUserService;

    /**
     * 注册或者登录
     * @param username
     * @param password
     * @return
     */
    public RegistOrLoginBo registOrLogin(String username, String password){
        QueryWrapper query = new QueryWrapper();
        query.select("id", "username", "password", "salt");
        query.eq("username", username);
        LcsrUser user = lcsrUserService.getOne(query);

        RegistOrLoginBo result = new RegistOrLoginBo();
        // 注册
        if (user == null) {
            user = new LcsrUser();
            user.setUsername(username);
            user.setSalt(Cipher.getSalt());
            user.setPassword(getPassword(password, user.getSalt()));
            user.setAddTime(DateUtils.now());

            boolean flag = lcsrUserService.save(user);
            user.setPassword(null);
            log.info("新增用户 结果={} 用户={}", flag, user);
        }else {
            boolean isOk = verifyPassword(password, user.getPassword(), user.getSalt());
            if (!isOk) {
                result.setSuccess(false);
                // TODO 后期优化
                result.setErrMsg("用户已存在！用户名或密码错误");
                return result;
            }
            // 登录后替换salt
            user.setSalt(Cipher.getSalt());
            user.setPassword(getPassword(password, user.getSalt()));
            boolean flag = lcsrUserService.updateById(user);
            user.setPassword(null);
            log.info("用户登录 结果={} 用户={}", flag, user);
        }

        result.setSuccess(true);
        result.setSalt(user.getSalt());
        return result;
    }

    /**
     * 校验salt是否有效
     * @param salt true 有效
     * @return
     */
    public boolean checkSalt(String salt){
        QueryWrapper query = new QueryWrapper();
        query.eq("salt", salt);
        int count = lcsrUserService.count(query);
        return count > 0;
    }

    /**
     * 根据salt获得用户id
     * @param salt
     * @return null salt已失效；用户id
     */
    public Integer getIdBySalt(String salt) {
        QueryWrapper query = new QueryWrapper();
        query.select("id");
        query.eq("salt", salt);

        List<LcsrUser> list = lcsrUserService.list(query);
        if (list.isEmpty()) {
            return null;
        }
        // 居然salt碰撞了
        if (list.size() > 1) {
            return null;
        }

        LcsrUser user = list.get(0);
        return user.getId();
    }

    private String getPassword(String password, String salt) {
        return Cipher.sha256(password + salt);
    }

    /**
     * 验证密码
     * @param receivePwd 接收到的密码
     * @param dbPwd 数据存储的密码
     * @param salt
     * @return true 验证通过
     */
    private boolean verifyPassword(String receivePwd, String dbPwd, String salt){
        return dbPwd.equals(getPassword(receivePwd, salt));
    }
}
