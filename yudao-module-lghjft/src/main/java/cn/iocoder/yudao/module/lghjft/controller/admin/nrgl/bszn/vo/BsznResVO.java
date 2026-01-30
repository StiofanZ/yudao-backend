package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 办事指南 Response VO")
@Data
public class BsznResVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "父编号", example = "0")
    private Long parentId;

    @Schema(description = "事项名称", example = "办事指南")
    private String sxmc;

    @Schema(description = "内容", example = "<p>HTML Content</p>")
    private String content;

    @Schema(description = "排序", example = "0")
    private Integer sort;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "发布部门编号", example = "10")
    private Long deptId;

    @Schema(description = "可见范围", example = "1")
    private Integer kjfw;

    @Schema(description = "办理部门", example = "A大厅3号窗口")
    private String blbm;

    @Schema(description = "下架原因", example = "1")
    private String xjyy;

    @Schema(description = "业务分类", example = "1")
    private Integer ywfl;

    @Schema(description = "办理主体", example = "1")
    private Integer blzt;

    @Schema(description = "发布时间", example = "2023-01-01")
    private LocalDate fbsj;

    @Schema(description = "咨询电话", example = "010-12345678")
    private String zxdh;

    @Schema(description = "法定时限", example = "20个工作日")
    private String fdsx;

    @Schema(description = "承诺时限", example = "5个工作日")
    private String cnsx;

    @Schema(description = "收费标准", example = "免费")
    private String sfbz;

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

    @Schema(description = "部门名称", example = "研发部")
    private String deptName;
}

