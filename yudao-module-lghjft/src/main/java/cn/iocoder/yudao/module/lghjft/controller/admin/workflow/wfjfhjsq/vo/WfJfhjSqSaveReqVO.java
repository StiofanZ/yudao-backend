package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 工会经费缓缴申请新增/修改 Request VO")
@Data
public class WfJfhjSqSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11647")
    private Long id;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "缴费单位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "缴费单位名称不能为空")
    private String nsrmc;

    @Schema(description = "联系人及电话")
    private String contactPhone;

    @Schema(description = "适用费率（%）")
    private BigDecimal applicableRate;

    @Schema(description = "职工人数（人）", example = "1056")
    private Integer employeeCount;

    @Schema(description = "月工资总额（元）")
    private BigDecimal monthlySalaryTotal;

    @Schema(description = "月拨缴金额（元）")
    private BigDecimal monthlyPayAmount;


    @Schema(description = "缓缴开始日期（年月）", example = "2026-03")
    private String deferStartDate; // 原 LocalDate 改为 YearMonth

    @Schema(description = "缓缴结束日期（年月）", example = "2026-06")
    private String deferEndDate; // 原 LocalDate 改为 YearMonth

    @Schema(description = "申请缓缴期限-共（月）")
    private Integer deferTotalMonth;

    @Schema(description = "累计缓缴金额（元）")
    private BigDecimal totalDeferAmount;

    @Schema(description = "申请缓缴情况说明")
    private String situationDesc;

    @Schema(description = "缴费单位-单位负责人")
    private String unitLeader;

    @Schema(description = "缴费单位-经办")
    private String handler;

    @Schema(description = "缴费单位-日期")
    private LocalDate applyDate;

    @Schema(description = "基层工会意见（章）")
    private String grassrootsOpinion;

    @Schema(description = "基层工会-工会负责人")
    private String grassrootsLeader;

    @Schema(description = "基层工会-经办")
    private String grassrootsHandler;

    @Schema(description = "基层工会-盖章日期（年/月/日）")
    private LocalDate grassrootsApproveTime;

    @Schema(description = "主管工会审核意见")
    private String managerOpinion;

    @Schema(description = "主管工会-工会负责人", example = "李四")
    private String managerLeaderName;

    @Schema(description = "主管工会-经办", example = "芋艿")
    private String managerHandlerName;

    @Schema(description = "主管工会-日期")
    private LocalDate managerApproveTime;

    @Schema(description = "主管工会-财务负责人")
    private String managerFinanceLeader;

    @Schema(description = "流程实例ID（BPMN）", example = "15431")
    private String processInstanceId;

}