package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "在线咨询处理 Request VO")
@Data
public class ZxzxProcessReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "处理意见", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "处理意见不能为空")
    private String clsm;

    @Schema(description = "处理状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "处理状态不能为空")
    private Integer zt;
}
