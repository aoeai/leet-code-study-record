package com.aoeai.lcsr.form.problem;

import lombok.Data;

/**
 * 问题列表表单
 */
@Data
public class ProblemListForm {

    private String salt;

    private String username;

    /**
     * 题目过滤id
     */
    private String ids;

    /**
     * 几天前
     */
    private Integer fewDaysAgo;

    /**
     * 当前状态
     */
    private int status;

    /**
     * 当前页
     */
    private int page;
}
