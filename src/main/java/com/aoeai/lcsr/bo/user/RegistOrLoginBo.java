package com.aoeai.lcsr.bo.user;

import lombok.Data;

/**
 * 用户注册或登录的结果
 */
@Data
public class RegistOrLoginBo {

    /**
     * 处理结果 true：成功
     */
    private boolean success;

    /**
     * 和密码一起使用的盐
     */
    private String salt;

    /**
     * 错误原因
     */
    private String errMsg;
}
