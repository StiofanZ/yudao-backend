package cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 工会经费通-问题反馈新增/修改 Request VO")
@Data
public class WtfkSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16018")
    private Long id;

    @Schema(description = "用户ID（关联系统用户表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18846")
    @NotNull(message = "用户ID（关联系统用户表）不能为空")
    private Long userId;

    @Schema(description = "用户名（冗余存储）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "用户名（冗余存储）不能为空")
    private String userName;

    @Schema(description = "反馈类型：bug-功能异常 suggestion-体验建议 question-其他问题", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "反馈类型：bug-功能异常 suggestion-体验建议 question-其他问题不能为空")
    private String type;

    @Schema(description = "平台名称")
    private String platformName;

    @Schema(description = "反馈内容（Fan Kui Nei Rong）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "反馈内容（Fan Kui Nei Rong）不能为空")
    private String content;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    private String contactEmail;

    @Schema(description = "处理状态：0-未处理 1-已处理 2-已关闭", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "处理状态：0-未处理 1-已处理 2-已关闭不能为空")
    private Integer status;

    @Schema(description = "处理人ID", example = "3038")
    private Long processorId;

    @Schema(description = "处理时间")
    private LocalDateTime processTime;

    @Schema(description = "处理备注：管理员处理说明")
    private String processNotes;

}