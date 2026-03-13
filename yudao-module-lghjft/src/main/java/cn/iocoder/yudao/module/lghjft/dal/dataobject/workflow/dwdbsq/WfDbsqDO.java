package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq;

import lombok.*;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工会隶属关系调拨申请 DO
 *
 * @author 李文军
 */
@TableName("gh_wf_dbsq")
@KeySequence("gh_wf_dbsq_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfDbsqDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 社会信用代码
     */
    private String shxydm;
    /**
     * 单位名称
     */
    private String dwmc;
    /**
     * 申请日期
     */
    private LocalDate sqrq;
    /**
     * 原主管工会名称
     */
    private String yzghmc;
    /**
     * 现主管工会名称
     */
    private String xzgghmc;
    /**
     * 调拨原因
     */
    private String dbyy;
    /**
     * 原主管工会意见（调拨相关）
     */
    private String yzghyj;
    /**
     * 原主管工会负责人
     */
    private String yzghfzr;
    /**
     * 原主管工会经办人
     */
    private String yzghjbr;
    /**
     * 联系电话
     */
    private  String lxdh;
    /**
     * 原主管工会日期
     */
    private LocalDate yzghgzrq;
    /**
     * 现主管工会审核意见（调拨相关）
     */
    private String xzgghspyj;
    /**
     * 现主管工会负责人
     */
    private String xzgghfzr;
    /**
     * 现主管工会经办人
     */
    private String xzgghjbr;
    /**
     * 现主管工会审核日期
     */
    private LocalDate xzgghsprq;
    /**
     * 省总工会审核意见（调拨相关）
     */
    private String sghspyj;
    /**
     * 省总工会负责人
     */
    private String sghfzr;
    /**
     * 省总工会经办人
     */
    private String sghjbr;
    /**
     * 省总工会审核日期
     */
    private LocalDate sghsprq;
    /**
     * 流程实例ID（调拨审批流程）
     */
    private String lcslId;


}