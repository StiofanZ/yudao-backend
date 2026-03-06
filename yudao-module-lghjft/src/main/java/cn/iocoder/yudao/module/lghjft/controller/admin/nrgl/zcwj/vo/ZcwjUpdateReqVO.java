package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 政策文件更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ZcwjUpdateReqVO extends ZcwjBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号不能为空")
    private Long id;
}
