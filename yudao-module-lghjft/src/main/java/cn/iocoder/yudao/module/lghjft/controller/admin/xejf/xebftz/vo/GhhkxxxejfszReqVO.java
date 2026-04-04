package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 小额拨付省总查询 Request VO")
@Data
public class GhhkxxxejfszReqVO {

    @Schema(description = "缴费期间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "缴费期间不能为空")
    private String jfqj;
}
