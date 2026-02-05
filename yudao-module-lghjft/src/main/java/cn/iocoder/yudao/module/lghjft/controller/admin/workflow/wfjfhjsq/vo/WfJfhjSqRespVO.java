package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 工会经费缓缴申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WfJfhjSqRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11647")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "缴费单位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("缴费单位名称")
    private String nsrmc;

    @Schema(description = "联系人及电话")
    @ExcelProperty("联系人及电话")
    private String contactPhone;

    @Schema(description = "适用费率（%）")
    @ExcelProperty("适用费率（%）")
    private BigDecimal applicableRate;

    @Schema(description = "职工人数（人）", example = "1056")
    @ExcelProperty("职工人数（人）")
    private Integer employeeCount;

    @Schema(description = "月工资总额（元）")
    @ExcelProperty("月工资总额（元）")
    private BigDecimal monthlySalaryTotal;

    @Schema(description = "月拨缴金额（元）")
    @ExcelProperty("月拨缴金额（元）")
    private BigDecimal monthlyPayAmount;

    @Schema(description = "申请缓缴期限-自（年/月）")
    @ExcelProperty("申请缓缴期限-自（年/月）")
    private LocalDate deferStartDate;

    @Schema(description = "申请缓缴期限-至（年/月）")
    @ExcelProperty("申请缓缴期限-至（年/月）")
    private LocalDate deferEndDate;

    @Schema(description = "申请缓缴期限-共（月）")
    @ExcelProperty("申请缓缴期限-共（月）")
    private Integer deferTotalMonth;

    @Schema(description = "累计缓缴金额（元）")
    @ExcelProperty("累计缓缴金额（元）")
    private BigDecimal totalDeferAmount;

    @Schema(description = "申请缓缴情况说明")
    @ExcelProperty("申请缓缴情况说明")
    private String situationDesc;

    @Schema(description = "缴费单位-单位负责人")
    @ExcelProperty("缴费单位-单位负责人")
    private String unitLeader;

    @Schema(description = "缴费单位-经办")
    @ExcelProperty("缴费单位-经办")
    private String handler;

    @Schema(description = "缴费单位-日期")
    @ExcelProperty("缴费单位-日期")
    private LocalDate applyDate;

    @Schema(description = "基层工会意见（章）")
    @ExcelProperty("基层工会意见（章）")
    private String grassrootsOpinion;

    @Schema(description = "基层工会-工会负责人")
    @ExcelProperty("基层工会-工会负责人")
    private String grassrootsLeader;

    @Schema(description = "基层工会-经办")
    @ExcelProperty("基层工会-经办")
    private String grassrootsHandler;

    @Schema(description = "基层工会-盖章日期（年/月/日）")
    @ExcelProperty("基层工会-盖章日期（年/月/日）")
    private LocalDate grassrootsApproveTime;

    @Schema(description = "主管工会审核意见")
    @ExcelProperty("主管工会审核意见")
    private String managerOpinion;

    @Schema(description = "主管工会-工会负责人", example = "李四")
    @ExcelProperty("主管工会-工会负责人")
    private String managerLeaderName;

    @Schema(description = "主管工会-经办", example = "芋艿")
    @ExcelProperty("主管工会-经办")
    private String managerHandlerName;

    @Schema(description = "主管工会-日期")
    @ExcelProperty("主管工会-日期")
    private LocalDate managerApproveTime;

    @Schema(description = "主管工会-财务负责人")
    @ExcelProperty("主管工会-财务负责人")
    private String managerFinanceLeader;

    @Schema(description = "流程实例ID（BPMN）", example = "15431")
    @ExcelProperty("流程实例ID（BPMN）")
    private String processInstanceId;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}