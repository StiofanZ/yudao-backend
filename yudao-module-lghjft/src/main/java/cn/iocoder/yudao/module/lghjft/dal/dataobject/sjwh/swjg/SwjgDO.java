package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swjg;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 税务机关 DO
 *
 * @author 李文军
 */
@TableName("dm_swjg")
@KeySequence("dm_swjg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwjgDO extends BaseDO {

    public static final Long SJSWJG_DM_ROOT = 0L;

    /**
     * 税务机关代码
     */
    @TableId(type = IdType.INPUT)
    private String swjgDm;
    /**
     * 税务机关名称
     */
    private String swjgmc;
    /**
     * 税务机关简称
     */
    private String swjgjc;
    /**
     * 地址
     */
    private String dz;
    /**
     * 邮政编码
     */
    private String yzbm;
    /**
     * 联系人
     */
    private String lxr;
    /**
     * 联系电话
     */
    private String lxdh;
    /**
     * 手续费账号
     */
    private String sxfzh;
    /**
     * 户名
     */
    private String sxfhm;
    /**
     * 行号
     */
    private String sxfhh;
    /**
     * 银行
     */
    private String sxfyh;
    /**
     * 上级税务机关代码
     */
    private String sjswjgDm;
    /**
     * 稽查局标记
     */
    private String jcjbj;
    /**
     * 工会机构代码
     */
    private String ghjgDm;
    /**
     * 顺序号
     */
    private Integer sxh;
    /**
     * 校验码
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