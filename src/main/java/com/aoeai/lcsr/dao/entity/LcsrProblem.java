package com.aoeai.lcsr.dao.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * LeetCode问题
 * </p>
 *
 * @author aoe
 * @since 2019-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LcsrProblem implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Integer difficulty;

    /**
     * 类型 （类型i的集合，例如 1,2)
     */
    private String tag;


}
