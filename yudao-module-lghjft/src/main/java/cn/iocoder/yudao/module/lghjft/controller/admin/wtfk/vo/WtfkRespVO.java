package cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
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
    private String feedbackId;

    @Schema(description = "用户ID（关联系统用户表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18846")
    @ExcelProperty("用户ID（关联系统用户表）")
    private Long userId;

    @Schema(description = "用户名（冗余存储）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("用户名（冗余存储）")
    private String userName;

    @Schema(description = "反馈类型：bug-功能异常 suggestion-体验建议 question-其他问题", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("反馈类型：bug-功能异常 suggestion-体验建议 question-其他问题")
    private String type;

    @Schema(description = "平台名称")
    private String platformName;

    @Schema(description = "反馈内容（Fan Kui Nei Rong）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈内容（Fan Kui Nei Rong）")
    private String content;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    @ExcelProperty("联系邮箱")
    private String contactEmail;



    @Schema(description = "处理状态（Chu Li Zhuang Tai）：0-未处理 1-已处理 2-已关闭", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("处理状态（Chu Li Zhuang Tai）：0-未处理 1-已处理 2-已关闭")
    private Integer status;

    @Schema(description = "处理人ID", example = "3038")
    @ExcelProperty("处理人ID")
    private Long processorId;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime processTime;

    @Schema(description = "处理人姓名")
    private String processorName;

    @Schema(description = "处理备注（Chu Li Bei Zhu）：管理员处理说明")
    @ExcelProperty("处理备注（Chu Li Bei Zhu）：管理员处理说明")
    private String processNotes;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "附件URL列表")
    private List<String> fileUrls;

}