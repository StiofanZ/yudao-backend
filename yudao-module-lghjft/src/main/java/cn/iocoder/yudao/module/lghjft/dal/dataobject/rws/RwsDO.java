package cn.iocoder.yudao.module.lghjft.dal.dataobject.rws;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 年度任务 DO
 *
 * @author 李文军
 */
@TableName("cxtj_rws")
@KeySequence("cxtj_rws_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RwsDO extends BaseDO {

    /**
     * 任务id
     */
    @TableId
    private Integer rwid;

    private String rwlx;

    private String nd;
    /**
     * 单位代码
     */
    private String dwdm;
    /**
     * 单位名称
     */
    private String dwmc;
    /**
     * 任务数
     */
    private BigDecimal rws;
    /**
     * 完成数
     */
    private String wcs;


    @TableField(exist = false)  // 告诉MyBatis这个字段不在表中
    private LocalDateTime createTime;

    @TableField(exist = false)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String creator;

    @TableField(exist = false)
    private String updater;

    @TableField(exist = false)
    private Boolean deleted;
}