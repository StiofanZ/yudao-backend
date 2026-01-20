package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 常见问题 Base VO，提供给添加、修改、详情的子 VO 使用
 */
@Data
public class CjwtBaseVO {

    @Schema(description = "父编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "父编号不能为空")
    private Long parentId;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "常见问题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @Schema(description = "内容", example = "<p>HTML Content</p>")
    private String content;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "可见范围", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "可见范围不能为空")
    private Integer kjfw;

    @Schema(description = "问题分类", example = "业务问题")
    private String wtfl;

}
