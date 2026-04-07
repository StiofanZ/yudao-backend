package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 已建会信息新增/修改 Request VO")
@Data
public class GhYjhxxSaveReqVO {

    @Schema(description = "建会信息ID")
    private Long jhxxId;

    @NotBlank(message = "纳税人名称不能为空")
    @Schema(description = "纳税人名称")
    private String nsrmc;

    @NotBlank(message = "社会信用代码不能为空")
    @Schema(description = "社会信用代码")
    private String shxydm;

    @NotBlank(message = "纳税人识别号不能为空")
    @Schema(description = "纳税人识别号")
    private String nsrsbh;

    @NotBlank(message = "有效标记不能为空")
    @Schema(description = "有效标记")
    private String yxbj;
}
