package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ZcwjBaseVO {

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "标题不能为空")
    private String title;

    @Schema(description = "摘要")
    private String summary;

    @Schema(description = "文件内容")
    private String content;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "可见范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "可见范围不能为空")
    private Integer kjfw;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "版本号不能为空")
    private String versionNo;

    @Schema(description = "标签，逗号分隔")
    private String tags;

    @Schema(description = "搜索关键词，逗号分隔")
    private String searchKeywords;

    @Schema(description = "附件地址，逗号分隔")
    private String attachmentUrls;

    @Schema(description = "发布部门(0:全总,1:省总,2:市州)")
    private Integer fbbm;

    @Schema(description = "发布日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fbrq;

}
