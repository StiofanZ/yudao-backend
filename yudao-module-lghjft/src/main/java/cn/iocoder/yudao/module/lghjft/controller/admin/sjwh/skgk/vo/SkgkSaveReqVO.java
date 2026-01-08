package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 收款国库新增/修改 Request VO")
@Data
public class SkgkSaveReqVO {

    @Schema(description = "国库ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19910")
    private Integer gkId;

    @Schema(description = "税款国库代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "税款国库代码不能为空")
    private String skgkDm;

    @Schema(description = "税款国库名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "税款国库名称不能为空")
    private String skgkmc;

    @Schema(description = "税款国库简称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "税款国库简称不能为空")
    private String skgkjc;

    @Schema(description = "行政区划代码")
    private String xzqhDm;

}