package com.aoeai.lcsr.vo.user;

import com.aoeai.lcsr.enums.ProblemDifficultyEnum;
import lombok.Data;

@Data
public class ProblemVo {

    private Integer id;

    /**
     * 问题
     */
    private String problem;

    /**
     * 问题的英文版
     */
    private String problemEn;

    /**
     * 难度 1：简单；2：中等；3困难
     */
    private ProblemDifficultyEnum difficulty;

    /**
     * 中文站链接
     */
    private String urlCN;

    /**
     * 国际站链接
     */
    private String urlEN;

    /**
     * 刷题次数
     */
    private String doCount;

    /**
     * 当前状态
     */
    private String status;

    /**
     * 最后做题时间
     */
    private String lastDoTime;
}
