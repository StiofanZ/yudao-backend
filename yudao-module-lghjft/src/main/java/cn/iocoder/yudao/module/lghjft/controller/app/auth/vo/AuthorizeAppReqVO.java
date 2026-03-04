package cn.iocoder.yudao.module.lghjft.controller.app.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 登录 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizeAppReqVO {
    @Schema(description = "登录账号", example = "admin")
    private String dlzh;
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "Abcd1234")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
