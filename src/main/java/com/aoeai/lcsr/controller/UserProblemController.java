package com.aoeai.lcsr.controller;

import com.aoeai.lcsr.constant.AoeHttpStatus;
import com.aoeai.lcsr.constant.ResponseText;
import com.aoeai.lcsr.enums.ProblemStatusEnum;
import com.aoeai.lcsr.service.UserProblemService;
import com.aoeai.lcsr.service.UserService;
import com.aoeai.lcsr.vo.userproblem.CurrentDoCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户刷题概要记录
 */
@RestController
@RequestMapping("userProblem")
public class UserProblemController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProblemService userProblemService;

    /**
     * 更新刷题状态
     * @param id 问题id
     * @param status 刷题状态
     * @param salt
     * @return
     */
    @GetMapping("changeStatus")
    public String changeStatus(int id, int status, String salt) {
        if (!userService.checkSalt(salt)) {
            return ResponseText.PLEASE_LOGIN;
        }
        if (!ProblemStatusEnum.check(status)) {
            return ResponseText.ERROR_PROBLEM_STATUS;
        }
        if (!userProblemService.updateStatus(id, status, salt)) {
            return ResponseText.OPERATION_FAILED_TRY_TO_LOGIN_AGAIN;
        }

        return ResponseText.SUCCESS;
    }

    /**
     * 更新刷题状态
     * @param id 问题id
     * @param salt
     * @return
     */
    @GetMapping("plusDoCount")
    public ResponseEntity plusDoCount(int id, String salt){
        if (!userService.checkSalt(salt)) {
            return ResponseEntity.status(AoeHttpStatus.FAIL)
                    .body(ResponseText.PLEASE_LOGIN);
        }
        Integer userId = userService.getIdBySalt(salt);
        if (!userProblemService.plusDoCount(id, userId)) {
            return ResponseEntity.status(AoeHttpStatus.FAIL)
                    .body(ResponseText.OPERATION_FAILED_TRY_TO_LOGIN_AGAIN);
        }
        CurrentDoCountVo currentDoCountVo = userProblemService.currentDoCountVo(id, userId);
        if (currentDoCountVo == null) {
            return ResponseEntity.status(AoeHttpStatus.FAIL)
                    .body(ResponseText.OPERATION_FAILED_TRY_TO_LOGIN_AGAIN);
        }
        return ResponseEntity.ok(currentDoCountVo);
    }
}
