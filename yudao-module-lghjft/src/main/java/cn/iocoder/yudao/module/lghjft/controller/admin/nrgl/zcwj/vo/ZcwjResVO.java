package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 政策文件 Response VO")
@Data
public class ZcwjResVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "摘要")
    private String summary;

    @Schema(description = "文件内容")
    private String content;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "发布部门编号")
    private Long deptId;

    @Schema(description = "发布部门名称")
    private String deptName;

    @Schema(description = "可见范围")
    private Integer kjfw;

    @Schema(description = "版本号")
    private String versionNo;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "搜索关键词")
    private String searchKeywords;

    @Schema(description = "附件地址，逗号分隔")
    private String attachmentUrls;

    @Schema(description = "发布部门")
    private Integer fbbm;

    @Schema(description = "发布日期")
    private LocalDate fbrq;

    @Schema(description = "下架原因")
    private String offShelfReason;

    @Schema(description = "阅读量")
    private Integer readCount;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
