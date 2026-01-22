package cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo;

import cn.iocoder.yudao.module.lghjft.enums.logger.LoginTypeEnum;
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
    @Schema(description = "用户账号", example = "admin")
    private String yhzh;
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "Abcd1234")
    @NotEmpty(message = "密码不能为空")
    private String password;
    @Schema(description = "联系电话", example = "15500000000")
    private String lxdh;
    @Schema(description = "用户邮箱", example = "yudao@iocoder.cn")
    private String yhyx;
    @Schema(description = "社会信用代码", example = "91320114MA1X7L7X7G")
    private String shxydm;
    @Schema(description = "登录类型", example = "91320114MA1X7L7X7G")
    private LoginTypeEnum loginType;
}
