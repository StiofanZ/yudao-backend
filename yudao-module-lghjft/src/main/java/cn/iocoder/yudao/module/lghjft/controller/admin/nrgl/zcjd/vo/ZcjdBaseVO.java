package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 政策解读 Base VO，提供给添加、修改、详情的子 VO 使用
 */
@Data
public class ZcjdBaseVO {

    @Schema(description = "父编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "父编号不能为空")
    private Long parentId;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "政策解读")
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

    @Schema(description = "附件路径", example = "https://example.com/file.pdf")
    private String fjlj;

    @Schema(description = "发布部门(0:全总,1:省总,2:市州)", example = "1")
    private Integer fbbm;

    @Schema(description = "下架原因(1:已失效政策,2:新政策替代)", example = "1")
    private String xjyy;

    @Schema(description = "原文件发布日期", example = "2023-01-01 12:00:00")
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime fbrq;

    @Schema(description = "关联政策ID", example = "100")
    private Long glzcId;

}
