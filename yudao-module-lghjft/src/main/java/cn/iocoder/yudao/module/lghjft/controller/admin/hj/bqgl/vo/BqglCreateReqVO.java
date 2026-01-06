package cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "管理后台 - 标签管理创建 Request VO")
@Data
public class BqglCreateReqVO {

    @Schema(description = "标签名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "重要标签")
    @NotBlank(message = "标签名称不能为空")
    @Size(max = 20, message = "标签名称长度不能超过20个字符")
    private String bqMc;

}
