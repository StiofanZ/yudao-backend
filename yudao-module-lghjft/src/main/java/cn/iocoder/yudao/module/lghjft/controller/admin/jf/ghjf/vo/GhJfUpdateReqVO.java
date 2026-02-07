package cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Schema(description = "管理后台 - 税务入库更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GhJfUpdateReqVO extends GhJfBaseVO {

    @Schema(description = "税票ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "税票ID不能为空")
    private Integer ghjfId;

}
