package cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 账户维护审核 Request VO")
@Data
public class ZhwhAuditReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号不能为空")
    private Long id;

    @Schema(description = "状态 1-通过 2-驳回", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "审核状态不能为空")
    private Integer status;

    @Schema(description = "审核意见")
    private String remark;
}
