package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 常见问题 Response VO")
@Data
public class CjwtResVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "父编号", example = "0")
    private Long parentId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "排序", example = "0")
    private Integer sort;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "发布部门编号", example = "10")
    private Long deptId;

    @Schema(description = "可见范围", example = "1")
    private Integer kjfw;

    @Schema(description = "问题分类")
    private String wtfl;

    @Schema(description = "下架原因")
    private String xjyy;

    @Schema(description = "阅读量", example = "100")
    private Integer readCount;

    @Schema(description = "热度排名", example = "1")
    private Integer rank;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "最后更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "是否删除")
    private Boolean deleted;

    @Schema(description = "部门名称")
    private String deptName;
}

