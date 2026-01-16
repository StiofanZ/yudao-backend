package cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 授权码登录 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticateReqVO {

    @Schema(description = "授权码", requiredMode = Schema.RequiredMode.REQUIRED, example = "D0__ZQHeMQAwmwfh9GiJ...")
    @NotEmpty(message = "授权码不能为空")
    private String authCode;
//    APP登录标识
    @Schema(description = "登录标识：PC-工作平台登录，APP-移动端登录",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "PC")

    @NotEmpty(message = "登录标识不能为空")
    private  String loginSign;
}
