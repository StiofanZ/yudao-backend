package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工会经费缓缴申请 DO
 *
 * @author 李文军
 */
@TableName("lghjft_wf_jfhj_sq")
@KeySequence("lghjft_wf_jfhj_sq_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfJfhjSqDO extends BaseDO {

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
     * 缴费单位名称
     */
    private String nsrmc;
    /**
     * 联系人及电话
     */
    private String contactPhone;
    /**
     * 适用费率（%）
     */
    private BigDecimal applicableRate;
    /**
     * 职工人数（人）
     */
    private Integer employeeCount;
    /**
     * 月工资总额（元）
     */
    private BigDecimal monthlySalaryTotal;
    /**
     * 月拨缴金额（元）
     */
    private BigDecimal monthlyPayAmount;
    /**
     * 申请缓缴期限-自（年/月）
     */
    private LocalDate deferStartDate;
    /**
     * 申请缓缴期限-至（年/月）
     */
    private LocalDate deferEndDate;
    /**
     * 申请缓缴期限-共（月）
     */
    private Integer deferTotalMonth;
    /**
     * 累计缓缴金额（元）
     */
    private BigDecimal totalDeferAmount;
    /**
     * 申请缓缴情况说明
     */
    private String situationDesc;
    /**
     * 缴费单位-单位负责人
     */
    private String unitLeader;
    /**
     * 缴费单位-经办
     */
    private String handler;
    /**
     * 缴费单位-日期
     */
    private LocalDate applyDate;
    /**
     * 基层工会意见（章）
     */
    private String grassrootsOpinion;
    /**
     * 基层工会-工会负责人
     */
    private String grassrootsLeader;
    /**
     * 基层工会-经办
     */
    private String grassrootsHandler;
    /**
     * 基层工会-盖章日期（年/月/日）
     */
    private LocalDate grassrootsApproveTime;
    /**
     * 主管工会审核意见
     */
    private String managerOpinion;
    /**
     * 主管工会-工会负责人
     */
    private String managerLeaderName;
    /**
     * 主管工会-经办
     */
    private String managerHandlerName;
    /**
     * 主管工会-日期
     */
    private LocalDate managerApproveTime;
    /**
     * 主管工会-财务负责人
     */
    private String managerFinanceLeader;
    /**
     * 流程实例ID（BPMN）
     */
    private String processInstanceId;


}