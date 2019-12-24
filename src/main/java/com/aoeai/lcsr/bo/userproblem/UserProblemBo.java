package com.aoeai.lcsr.bo.userproblem;

import lombok.Data;

/**
 * 用户刷题记录概要
 */
@Data
public class UserProblemBo {

    /**
     * 问题id
     */
    private Integer problemId;

    /**
     * 刷题次数
     */
    private Integer doCount;

    /**
     * '状态 1：未解答；2：已解答；3：尝试过；4：略懂；5：掌握；6：精通
     */
    private String status;

    /**
     * 最后做题时间
     */
    private String lastDoTime;
}
