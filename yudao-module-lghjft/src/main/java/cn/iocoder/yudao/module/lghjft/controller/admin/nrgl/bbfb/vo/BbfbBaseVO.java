package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Schema(description = "管理后台 - 版本发布 Base VO")
@Data
@ToString(callSuper = true)
public class BbfbBaseVO {

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED, example = "v1.0.0")
    @NotNull(message = "版本号不能为空")
    private String version;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "版本更新")
    @NotNull(message = "标题不能为空")
    private String title;

    @Schema(description = "内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "更新内容...")
    @NotNull(message = "内容不能为空")
    private String content;

    @Schema(description = "状态", example = "0")
    private Integer status;

    @Schema(description = "发布时间")
    private LocalDate fbsj;

}
