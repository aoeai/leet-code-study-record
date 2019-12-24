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
 * 用户刷题记录概要
 * </p>
 *
 * @author aoe
 * @since 2019-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LcsrUserProblem implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 刷题次数
     */
    private Integer doCount;

    /**
     * '状态 1：未解答；2：已解答；3：尝试过；4：略懂；5：掌握；6：精通
     */
    private Integer status;

    /**
     * 最后做题时间
     */
    private LocalDateTime lastDoTime;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
