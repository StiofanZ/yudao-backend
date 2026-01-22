package cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工会经费通-问题反馈分页 Request VO")
@Data
public class WtfkPageReqVO extends PageParam {


    @Schema(description = "用户ID（关联系统用户表）", example = "18846")
    private Long userId;

    private String feedbackId;

    @Schema(description = "用户名", example = "芋艿")
    private String userName;

    @Schema(description = "反馈类型", example = "2")
    private String type;

    @Schema(description = "反馈内容（Fan Kui Nei Rong）")
    private String content;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    private String contactEmail;



    @Schema(description = "处理状态", example = "2")
    private Integer status;

    @Schema(description = "处理人ID", example = "3038")
    private Long processorId;

    @Schema(description = "处理时间（Chu Li Shi Jian）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] processTime;

    @Schema(description = "处理备注（Chu Li Bei Zhu）：管理员处理说明")
    private String processNotes;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "是否管理端视图")
    private Boolean isAdminView;


}