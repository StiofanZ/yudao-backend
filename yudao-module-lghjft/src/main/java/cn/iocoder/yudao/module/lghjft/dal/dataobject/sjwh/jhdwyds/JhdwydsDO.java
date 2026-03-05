package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 应代收单位 DO
 *
 * @author 李文军
 */
@TableName("jhdwyds_dr")
@KeySequence("jhdwyds_dr_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JhdwydsDO {

    /**
     * 建会单位代收ID
     */
    @TableId
    private Integer jhdwId;
    /**
     * 工会机构
     */
    private String deptId;
    /**
     * 社会信用代码
     */
    private String shxydm;
    /**
     * 纳税人名称
     */
    private String nsrmc;
    /**
     * 工会法人统一社会信用代码
     */
    private String ghshxydm;
    /**
     * 工会组织名称
     */
    private String ghmc;
    /**
     * 工会联系人
     */
    private String ghlxr;
    /**
     * 工会联系电话
     */
    private String ghlxdh;
    /**
     * 成立工会标志（0 筹备建会 1 已建会）
     */
    private String clghbj;
    /**
     * 成立工会日期（筹备金建会日期）
     */
    private String clghrq;
    /**
     * 工会状态代码
     */
    private String ghztDm;
    /**
     * 备注
     */
    private String bz;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}