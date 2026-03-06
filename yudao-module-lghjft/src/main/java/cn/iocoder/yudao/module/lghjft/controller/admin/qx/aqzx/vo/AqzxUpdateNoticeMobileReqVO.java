package cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo;

import cn.iocoder.yudao.framework.common.validation.Mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 安全中心更新通知手机号 Request VO")
@Data
public class AqzxUpdateNoticeMobileReqVO {

    @Schema(description = "新通知手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "通知手机号不能为空")
    @Mobile
    private String mobile;

    @Schema(description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "验证码不能为空")
    private String code;
}
