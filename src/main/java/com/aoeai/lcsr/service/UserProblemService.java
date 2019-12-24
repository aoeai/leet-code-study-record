package com.aoeai.lcsr.service;

import com.aoeai.lcsr.bo.userproblem.UserProblemBo;
import com.aoeai.lcsr.constant.TextConstant;
import com.aoeai.lcsr.dao.entity.LcsrUserProblem;
import com.aoeai.lcsr.dao.service.ILcsrUserProblemService;
import com.aoeai.lcsr.enums.ProblemStatusEnum;
import com.aoeai.lcsr.utils.DateUtils;
import com.aoeai.lcsr.utils.TextUtils;
import com.aoeai.lcsr.vo.userproblem.CurrentDoCountVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户刷题概要记录
 */
@Service
public class UserProblemService {

    @Autowired
    private ILcsrUserProblemService lcsrUserProblemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemRecordService problemRecordService;

    /**
     * 更新刷题状态
     * @param problemId 题目id
     * @param status 状态
     * @param salt
     * @return true 成功
     */
    public boolean updateStatus(int problemId, int status, String salt){
        Integer userId = userService.getIdBySalt(salt);
        if (!checkPrerequisite(userId, problemId, false)) {
            return false;
        }

        UpdateWrapper update = new UpdateWrapper();
        update.eq("user_id", userId);
        update.eq("problem_id", problemId);
        update.set("status", status);

        return lcsrUserProblemService.update(update);
    }

    /**
     * 刷题次数+1
     * @param problemId 问题id
     * @param userId 用户id
     * @return true 成功
     */
    public boolean plusDoCount(int problemId, Integer userId) {
        if (!checkPrerequisite(userId, problemId, true)) {
            return false;
        }

        UpdateWrapper update = new UpdateWrapper();
        update.setSql("do_count = do_count + 1");
        update.set("last_do_time", DateUtils.now());
        update.eq("problem_id", problemId);
        update.eq("user_id", userId);

        problemRecordService.insert(userId, problemId);
        return lcsrUserProblemService.update(update);
    }

    public CurrentDoCountVo currentDoCountVo(int problemId, Integer userId){
        QueryWrapper query = new QueryWrapper();
        query.select("do_count", "last_do_time");
        query.eq("user_id", userId);
        query.eq("problem_id", problemId);

        LcsrUserProblem up = lcsrUserProblemService.getOne(query);
        if (up == null) {
            return null;
        }

        CurrentDoCountVo countVo = new CurrentDoCountVo();
        countVo.setDoCount(up.getDoCount());
        countVo.setLastDoTime(DateUtils.YyyyMmDd(up.getLastDoTime()));
        return countVo;
    }

    /**
     * 用户刷题记录概要Map
     * @param userId 用户id
     * @param status 刷题状态
     * @param fewDaysAgo 几天前
     * @return key 题目id
     */
    public Map<Integer, UserProblemBo> getUserProblemMap(int userId, int status, Integer fewDaysAgo){
        QueryWrapper query = new QueryWrapper();
        query.select("problem_id", "do_count", "status", "last_do_time");
        query.eq("user_id", userId);
        if (status > 0) {
            query.eq("status", status);
        }
        if (fewDaysAgo != null) {
            LocalDateTime time = DateUtils.now();
            time = time.plusDays(-fewDaysAgo);
            query.apply("DATE_FORMAT(last_do_time,'%Y-%m-%d')='" + DateUtils.YyyyMmDd(time) + "'");
        }

        List<LcsrUserProblem> list = lcsrUserProblemService.list(query);
        if (list.isEmpty()) {
            return new HashMap<>();
        }

        Map<Integer, UserProblemBo> map = new HashMap<>(list.size());
        for (LcsrUserProblem up : list) {
            UserProblemBo bo = new UserProblemBo();
            BeanUtils.copyProperties(up, bo);
            bo.setLastDoTime(up.getLastDoTime().getYear() < 2019 ? TextConstant.NONE
                    : DateUtils.YyyyMmDd(up.getLastDoTime()));
            bo.setStatus(ProblemStatusEnum.title(up.getStatus()));
            map.put(up.getProblemId(), bo);
        }
        return map;
    }

    /**
     * 检查并初始化前提条件：更新刷题状态、完成+1的数据
     * @param userId 用户id
     * @param problemId 问题id
     * @param initLastDoTime true 初始化刷题时间
     * @return true 初始化前提条件成功，可以继续操作
     */
    private boolean checkPrerequisite(Integer userId, int problemId, boolean initLastDoTime){
        if (userId == null) {
            return false;
        }

        boolean isExistProblem = isExistProblem(userId, problemId, initLastDoTime);
        if (!isExistProblem) {
            return false;
        }

        return true;
    }

    /**
     * 判断用户刷题概要记录是否已存在，如果不存在就新增一条记录
     * @param userId 用户id
     * @param problemId 问题id
     * @param initLastDoTime true 初始化刷题时间
     * @return true 已存在
     */
    private boolean isExistProblem(int userId, int problemId, boolean initLastDoTime){
        QueryWrapper query = new QueryWrapper();
        query.eq("user_id", userId);
        query.eq("problem_id", problemId);
        boolean isExist = lcsrUserProblemService.count(query) > 0 ? true : false;

        if (isExist) {
            return true;
        }

        LcsrUserProblem up = new LcsrUserProblem();
        up.setUserId(userId);
        up.setProblemId(problemId);
        up.setAddTime(DateUtils.now());
        up.setDoCount(0);
        up.setLastDoTime(initLastDoTime ? up.getAddTime() : DateUtils.now().plusYears(-9));

        return lcsrUserProblemService.save(up);
    }
}
