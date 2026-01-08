package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.fpblcopy;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 分配比例 DO
 *
 * @author 李文军
 */
@TableName("cs_fpbl_copy")
@KeySequence("cs_fpbl_copy_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FpblCopyDO extends BaseDO {

    /**
     * 比例ID
     */
    @TableId(type = IdType.INPUT)
    private Integer blId;
    /**
     * 比例ID
     */
    private String bluuid;

    private String lx;
    /**
     * 描述
     */
    private String ms;
    /**
     * 有效期起
     */
    private LocalDateTime yxqq;
    /**
     * 有效期止
     */
    private LocalDateTime yxqz;


    private String xybz;
    /**
     * 基层工会比例
     */
    private BigDecimal jcghbl;
    /**
     * 行业工会比例
     */
    private BigDecimal hyghbl;
    /**
     * 属地工会比例
     */
    private BigDecimal sdghbl;
    /**
     * 县级工会比例
     */
    private BigDecimal xjghbl;
    /**
     * 市级工会比例
     */
    private BigDecimal sjghbl;
    /**
     * 省总工会比例
     */
    private BigDecimal szghbl;
    /**
     * 全总工会比例
     */
    private BigDecimal qgzghbl;
    /**
     * 省稽查局比例
     */
    private BigDecimal sjcjbl;
    /**
     * 省税局比例
     */
    private BigDecimal sdsjbl;
    /**
     * 主管税务机关比例
     */
    private BigDecimal swjgbl;
    /**
     * TJ
     */
    private String tj;
    /**
     * YXJ
     */
    private Integer yxj;
    /**
     * MRBZ
     */
    private String mrbz;
    /**
     * JYM
     */
    private String jym;
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