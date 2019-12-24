package com.aoeai.lcsr.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 刷题记录
 * </p>
 *
 * @author aoe
 * @since 2019-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LcsrProblemRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 问题id
     */
    private Integer problemId;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 逻辑删除 0：为删除； 1：已删除
     */
    private Boolean isDelete;


}
