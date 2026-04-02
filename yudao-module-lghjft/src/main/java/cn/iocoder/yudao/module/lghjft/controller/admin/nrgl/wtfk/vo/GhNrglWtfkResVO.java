package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 问题反馈 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhNrglWtfkResVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "反馈编号")
    @ExcelProperty("反馈编号")
    private String fkbh;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private Long yhId;

    @Schema(description = "用户名")
    @ExcelProperty("用户名")
    private String yhmc;

    @Schema(description = "反馈类型")
    @ExcelProperty("反馈类型")
    private String lx;

    @Schema(description = "平台名称")
    @ExcelProperty("平台名称")
    private String ptmc;

    @Schema(description = "反馈内容")
    @ExcelProperty("反馈内容")
    private String nr;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String lxdh;

    @Schema(description = "联系邮箱")
    @ExcelProperty("联系邮箱")
    private String lxyx;

    @Schema(description = "处理状态")
    @ExcelProperty("处理状态")
    private Integer zt;

    @Schema(description = "处理人ID")
    @ExcelProperty("处理人ID")
    private Long clrId;

    @Schema(description = "处理人姓名")
    @ExcelProperty("处理人姓名")
    private String clrmc;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime clsj;

    @Schema(description = "处理说明")
    @ExcelProperty("处理说明")
    private String clsm;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "附件列表")
    private List<FjItem> fjList;

    @Data
    @Schema(description = "附件项")
    public static class FjItem {
        @Schema(description = "文件路径")
        private String wjlj;
        @Schema(description = "文件名")
        private String wjmc;
        @Schema(description = "原始文件名")
        private String ywjmc;
    }
}
