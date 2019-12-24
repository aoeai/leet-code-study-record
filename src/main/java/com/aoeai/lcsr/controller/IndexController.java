package com.aoeai.lcsr.controller;

import com.aoeai.lcsr.constant.KeyConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(@RequestParam(required = false) String errMsg, ModelMap model){
        if(errMsg != null){
            model.put(KeyConstant.ERROR_MSG, errMsg);
        }
        return "index";
    }

    @RequestMapping("/nani")
    public String error(){
        return "error";
    }
}
