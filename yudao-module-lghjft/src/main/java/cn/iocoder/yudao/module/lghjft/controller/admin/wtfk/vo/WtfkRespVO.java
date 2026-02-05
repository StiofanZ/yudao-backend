package cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 工会经费通-问题反馈 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WtfkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16018")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "反馈ID")
    @ExcelProperty("反馈单号")
    private String feedbackId;

    @Schema(description = "用户ID（关联系统用户表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18846")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "用户名（冗余存储）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "反馈类型：bug-功能异常 suggestion-体验建议 question-其他问题", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("反馈类型")
    private String type;

    @Schema(description = "平台名称")
    @ExcelProperty("平台名称")
    private String platformName;

    @Schema(description = "反馈内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈内容")
    private String content;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    @ExcelProperty("联系邮箱")
    private String contactEmail;

    @Schema(description = "处理状态：1-待处理 2已处理 3-已处理", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("处理状态")
    private Integer status;

    @Schema(description = "处理人ID", example = "3038")
    @ExcelProperty("处理人ID")
    private Long processorId;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime processTime;

    @Schema(description = "处理人姓名")
    @ExcelProperty("处理人姓名")
    private String processorName;

    @Schema(description = "处理备注：管理员处理说明")
    @ExcelProperty("处理备注")
    private String processNotes;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "附件列表")
    private List<FileItem> files;

    /**
     * 附件项结构 (用于响应给前端)
     */
    @Data
    @Schema(description = "附件项")
    public static class FileItem {

        @Schema(description = "文件访问地址", example = "http://localhost/a.png")
        private String fileUrl;

        @Schema(description = "文件名", example = "截图.png")
        private String fileName;

        @Schema(description = "原始文件名")
        private String fileOriginName;
    }
}