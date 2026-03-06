package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 问题反馈处理 Request VO")
@Data
public class GhNrglWtfkClReqVO {

    @Schema(description = "问题反馈ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "问题反馈ID不能为空")
    private Long id;

    @Schema(description = "处理状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "处理状态不能为空")
    private Integer zt;

    @Schema(description = "处理说明", requiredMode = Schema.RequiredMode.REQUIRED, example = "已联系技术人员处理")
    @NotBlank(message = "处理说明不能为空")
    private String clsm;
}
