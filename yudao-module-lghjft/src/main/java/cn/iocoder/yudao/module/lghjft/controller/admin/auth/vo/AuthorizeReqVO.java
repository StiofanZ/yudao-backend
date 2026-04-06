package cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo;

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
public class AuthorizeReqVO {
    @Schema(description = "登录账号（用户名/手机号/邮箱/社会信用代码）", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    @NotEmpty(message = "登录账号不能为空")
    private String dlzh;
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "Abcd1234")
    @NotEmpty(message = "密码不能为空")
    private String password;
    @Schema(description = "登录方式", example = "100")
    private Integer dlfs;
    @Schema(description = "用户类型", example = "1")
    private Integer yhlx;
}
