package com.aoeai.lcsr.controller;

import com.aoeai.lcsr.constant.KeyConstant;
import com.aoeai.lcsr.constant.ResponseText;
import com.aoeai.lcsr.form.problem.ProblemListForm;
import com.aoeai.lcsr.service.ProblemService;
import com.aoeai.lcsr.service.UserService;
import com.aoeai.lcsr.utils.TextUtils;
import com.aoeai.lcsr.utils.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public String listGet(String salt, String username, ModelMap model){
        ProblemListForm form = new ProblemListForm();
        form.setSalt(salt);
        form.setUsername(username);

        return showProblemList(model, form);
    }

    @PostMapping("list")
    public String list(ProblemListForm form, ModelMap model){
        return showProblemList(model, form);
    }

    private String showProblemList(ModelMap model, ProblemListForm form) {
        String errMsg = checkProblemListForm(form);
        if(errMsg != null){
            return String.format("redirect:/?%s=%s",KeyConstant.ERROR_MSG, TextUtils.urlEncoder(errMsg));
        }

        model.put("form", form);
        model.put("pagination", problemService.list(form, userService.getIdBySalt(form.getSalt())));
        return "problem/list";
    }

    private String checkProblemListForm(ProblemListForm form) {
        if (StringUtils.isBlank(form.getSalt()) || form.getSalt().length() != 64) {
            return ResponseText.PLEASE_LOGIN;
        }
        if (!userService.checkSalt(form.getSalt())){
            return ResponseText.PLEASE_LOGIN;
        }
        return null;
    }
}
