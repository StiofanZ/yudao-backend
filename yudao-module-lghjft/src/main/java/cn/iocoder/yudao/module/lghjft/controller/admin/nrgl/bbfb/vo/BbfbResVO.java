package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 版本发布 Response VO")
@Data
public class BbfbResVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "版本号", example = "v1.0.0")
    private String version;

    @Schema(description = "标题", example = "版本更新")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "状态", example = "0")
    private Integer status;

    @Schema(description = "发布部门编号", example = "100")
    private Long deptId;

    @Schema(description = "发布时间")
    private LocalDate fbsj;

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
}

