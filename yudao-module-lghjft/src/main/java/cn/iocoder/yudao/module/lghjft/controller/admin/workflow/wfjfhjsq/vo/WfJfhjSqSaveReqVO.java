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

    @Schema(description = "联系人")
    @NotEmpty(message = "联系人不能为空")
    private String contact;

    @Schema(description = "联系电话")
    @NotEmpty(message = "联系电话不能为空")
    private String contactPhone;

    @Schema(description = "适用费率（%）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "适用费率（%）不能为空") // 修正：BigDecimal用@NotNull，不能用@NotEmpty
    @DecimalMin(value = "0.01", message = "适用费率不能小于0.01%") // 附加：费率不能为0/负数，贴合业务
    private BigDecimal applicableRate;

    @Schema(description = "职工人数（人）", example = "1056", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "职工人数（人）不能为空")
    @Min(value = 1, message = "职工人数不能少于1人") // 附加：人数不能为0/负数，贴合业务
    private Integer employeeCount;

    @Schema(description = "月工资总额（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "月工资总额（元）不能为空")
    @DecimalMin(value = "0.01", message = "月工资总额不能小于0.01元") // 附加：金额不能为0/负数，贴合业务
    private BigDecimal monthlySalaryTotal;

    @Schema(description = "月拨缴金额（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "月拨缴金额（元）不能为空")
    @DecimalMin(value = "0.00", message = "月拨缴金额不能为负数") // 拨缴金额可0，不可负
    private BigDecimal monthlyPayAmount;

    @Schema(description = "缓缴开始日期（年月）", example = "2026-03", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "缓缴开始日期（年月）不能为空") // 字符串类型的年月，用@NotEmpty
    private String deferStartDate; // 原 LocalDate 改为 String 存储年月

    @Schema(description = "缓缴结束日期（年月）", example = "2026-06", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "缓缴结束日期（年月）不能为空") // 字符串类型的年月，用@NotEmpty
    private String deferEndDate; // 原 LocalDate 改为 String 存储年月

    @Schema(description = "申请缓缴期限-共（月）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请缓缴期限（月）不能为空")
    @Min(value = 1, message = "缓缴期限不能少于1个月")
    @Max(value = 24, message = "缓缴期限不能超过24个月") // 附加：贴合业务，限制最大24个月
    private Integer deferTotalMonth;

    @Schema(description = "累计缓缴金额（元）", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "累计缓缴金额（元）不能为空")
    @DecimalMin(value = "0.01", message = "累计缓缴金额不能小于0.01元")
    private BigDecimal totalDeferAmount;

    @Schema(description = "申请缓缴情况说明", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申请缓缴情况说明不能为空")
    @Size(min = 10, message = "申请缓缴情况说明不能少于10个字符") // 附加：贴合前端，限制最小字数
    private String situationDesc;

    @Schema(description = "缴费单位-单位负责人", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "缴费单位负责人不能为空")
    private String unitLeader;

    @Schema(description = "缴费单位-经办", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "缴费单位经办人不能为空")
    private String handler;

    @Schema(description = "缴费单位-日期", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate applyDate;

    @Schema(description = "基层工会意见（章）", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "基层工会意见不能为空")
    private String grassrootsOpinion;

    @Schema(description = "基层工会-工会负责人", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "基层工会负责人不能为空")
    private String grassrootsLeader;

    @Schema(description = "基层工会-经办", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "基层工会经办不能为空")
    private String grassrootsHandler;

    @Schema(description = "基层工会-盖章日期（年/月/日）", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "基层工会盖章日期不能为空")
    private LocalDate grassrootsApproveTime;

    @Schema(description = "主管工会审核意见", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "主管工会审核意见不能为空")
    private String managerOpinion;

    @Schema(description = "主管工会-工会负责人", example = "李四", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "主管工会负责人不能为空")
    private String managerLeaderName;

    @Schema(description = "主管工会-经办人", example = "芋艿", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "主管工会经办人不能为空")
    private String managerHandlerName;

    @Schema(description = "主管工会-日期", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "主管工会审核日期不能为空")
    private LocalDate managerApproveTime;

    @Schema(description = "主管工会-财务负责人", requiredMode = Schema.RequiredMode.REQUIRED)

    private String managerFinanceLeader;

    @Schema(description = "流程实例ID（BPMN）", example = "15431", requiredMode = Schema.RequiredMode.REQUIRED)
    private String processInstanceId;

}