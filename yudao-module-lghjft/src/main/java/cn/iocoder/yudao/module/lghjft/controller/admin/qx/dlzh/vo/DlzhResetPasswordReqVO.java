package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(description = "管理后台 - 登录账号重置密码 Request VO")
@Data
public class DlzhResetPasswordReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "ID不能为空")
    private Long id;

    @Schema(description = "新密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotEmpty(message = "新密码不能为空")
    @Length(max = 100, message = "新密码长度不能超过100个字符")
    private String password;

}

