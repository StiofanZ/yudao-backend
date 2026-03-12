package cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "短信登录 Request VO")
@Data
public class DxdlReqVO {

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "13800000000")
    @NotBlank(message = "联系电话不能为空")
    private String lxdh;

    @Schema(description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotBlank(message = "验证码不能为空")
    private String yzm;

    @Schema(description = "用户类型", example = "2")
    private Integer yhlx;
}
