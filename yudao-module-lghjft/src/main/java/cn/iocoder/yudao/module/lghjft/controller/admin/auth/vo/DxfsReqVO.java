package cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "短信验证码发送 Request VO")
@Data
public class DxfsReqVO {

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "13800000000")
    @NotBlank(message = "联系电话不能为空")
    private String lxdh;
}
