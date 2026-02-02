package cn.iocoder.yudao.module.lghjft.controller.app.workflow.wftdfsq.vo;//package cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Data;
//
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//
//@Data
//public class WfTdfSqApproveReqVO {
//
//    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "申请ID不能为空")
//    private Long applyId;
//
//    @Schema(description = "任务Key（如 managerAudit / provinceAudit）", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "任务Key不能为空")
//    private String taskKey;
//
//    @Schema(description = "审批结果（AGREE / REJECT）", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "审批结果不能为空")
//    private String result; // AGREE, REJECT
//
//    @Schema(description = "审批意见", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "审批意见不能为空")
//    private String opinion;
//
//    @Schema(description = "负责人")
//    private String leaderName;
//
//    @Schema(description = "经办人")
//    private String handlerName;
//
//    @Schema(description = "审批日期", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "审批日期不能为空")
//    private java.time.LocalDate approveTime;
//
//    @Schema(description = "退还方式（仅省总节点需要）")
//    private Integer refundMethod;
//}