package com.aoeai.lcsr.controller;

import com.aoeai.lcsr.bo.user.RegistOrLoginBo;
import com.aoeai.lcsr.constant.KeyConstant;
import com.aoeai.lcsr.service.UserService;
import com.aoeai.lcsr.utils.TextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registOrLogin")
    public String registOrLogin(String username, String password) {
        String errUsername = checkUsername(username);
        String errPassword = checkPassword(password);
        String errMsg = errUsername == null ? errPassword : errUsername;
        if (errMsg != null){
            return String.format("redirect:/?%s=%s",
                    KeyConstant.ERROR_MSG, TextUtils.urlEncoder(errMsg));
        }

        RegistOrLoginBo result = userService.registOrLogin(username.trim(), password.trim());
        if (result.isSuccess()) {
            return String.format("redirect:/problem/list?salt=%s&username=%s",
                    result.getSalt(), TextUtils.urlEncoder(username));
        }

        return String.format("redirect:/?%s=%s",
                KeyConstant.ERROR_MSG, TextUtils.urlEncoder(result.getErrMsg()));
    }

    private String checkUsername(String username) {
        if (StringUtils.isBlank(username)){
            return "用户名不能为空";
        }
        if (username.length() > 30){
            return "用户名最大长度30";
        }
        return null;
    }

    private String checkPassword(String password) {
        if (StringUtils.isBlank(password)){
            return "密码不能为空";
        }
        if (password.length() > Integer.MAX_VALUE - 1){
            return "密码太长了";
        }
        return null;
    }
}
