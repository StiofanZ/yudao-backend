package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 标签更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BqglUpdateReqVO extends BqglCreateReqVO {

    @Schema(description = "归类管理代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "归类管理代码不能为空")
    private String id;

}
