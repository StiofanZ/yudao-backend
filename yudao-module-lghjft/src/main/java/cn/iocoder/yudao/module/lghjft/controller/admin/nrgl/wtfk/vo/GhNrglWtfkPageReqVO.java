package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Collection;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 问题反馈分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GhNrglWtfkPageReqVO extends PageParam {

    @Schema(description = "反馈编号", example = "FK202603060001")
    private String fkbh;

    @Schema(description = "用户ID", example = "1")
    private Long yhId;

    @Schema(description = "用户名")
    private String yhmc;

    @Schema(description = "反馈类型")
    private String lx;

    @Schema(description = "反馈内容")
    private String nr;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "联系邮箱")
    private String lxyx;

    @Schema(description = "处理状态")
    private Integer zt;

    @Schema(description = "处理人ID")
    private Long clrId;

    @Schema(description = "处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] clsj;

    @Schema(description = "处理说明")
    private String clsm;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "是否管理端视图")
    private Boolean isAdminView;

    @Schema(description = "内部状态集合")
    private Collection<Integer> statuses;
}
