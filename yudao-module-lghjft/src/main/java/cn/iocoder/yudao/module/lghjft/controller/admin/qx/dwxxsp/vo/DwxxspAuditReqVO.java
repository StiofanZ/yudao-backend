package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 单位信息审批处理 Request VO")
@Data
public class DwxxspAuditReqVO {

    @Schema(description = "业务类型 ACCOUNT_CHANGE / IDENTITY", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "业务类型不能为空")
    private String businessType;

    @Schema(description = "业务 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "业务 ID 不能为空")
    private Long businessId;

    @Schema(description = "状态 1-通过 2-驳回", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "审核状态不能为空")
    private Integer status;

    @Schema(description = "审核意见")
    private String remark;
}
