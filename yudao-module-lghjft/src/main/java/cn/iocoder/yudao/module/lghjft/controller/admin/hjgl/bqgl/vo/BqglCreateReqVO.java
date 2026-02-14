package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 标签创建 Request VO")
@Data
@ToString(callSuper = true)
public class BqglCreateReqVO {

    @Schema(description = "标签归类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "重点关注")
    @NotNull(message = "标签归类名称不能为空")
    private String bqMc;

}
