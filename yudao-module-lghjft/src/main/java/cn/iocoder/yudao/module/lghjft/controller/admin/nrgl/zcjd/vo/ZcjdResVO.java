package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 政策解读 Response VO")
@Data
public class ZcjdResVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "父编号", example = "0")
    private Long parentId;

    @Schema(description = "标题", example = "政策解读")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "排序", example = "0")
    private Integer sort;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "发布部门编号", example = "1024")
    private Long deptId;

    @Schema(description = "可见范围", example = "1")
    private Integer kjfw;

    @Schema(description = "附件路径")
    private String fjlj;

    @Schema(description = "发布部门(0:全总,1:省总,2:市州)", example = "0")
    private Integer fbbm;

    @Schema(description = "下架原因")
    private String xjyy;

    @Schema(description = "原文件发布日期")
    private LocalDateTime fbrq;

    @Schema(description = "关联政策ID", example = "1024")
    private Long glzcId;

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

