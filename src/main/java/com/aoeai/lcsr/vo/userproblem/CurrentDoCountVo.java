package com.aoeai.lcsr.vo.userproblem;

import lombok.Data;

/**
 * 当前刷题数据
 */
@Data
public class CurrentDoCountVo {

    /**
     * 刷题次数
     */
    private Integer doCount;

    /**
     * 最后刷题时间
     */
    private String lastDoTime;
}
