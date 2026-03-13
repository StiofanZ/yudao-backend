package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo;

import lombok.*;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工会隶属关系调拨申请分页 Request VO")
@Data
public class WfDbsqPageReqVO extends PageParam {

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "单位名称")
    private String dwmc;

    @Schema(description = "申请日期")
    private LocalDate sqrq;

    @Schema(description = "原主管工会名称")
    private String yzghmc;

    @Schema(description = "现主管工会名称")
    private String xzgghmc;

    @Schema(description = "调拨原因")
    private String dbyy;

    @Schema(description = "原主管工会意见（调拨相关）")
    private String yzghyj;

    @Schema(description = "原主管工会负责人")
    private String yzghfzr;

    @Schema(description = "原主管工会经办人")
    private String yzghjbr;

    @Schema(description = "原主管工会日期")
    private LocalDate yzghgzrq;

    @Schema(description = "现主管工会审核意见（调拨相关）")
    private String xzgghspyj;

    @Schema(description = "现主管工会负责人")
    private String xzgghfzr;

    @Schema(description = "现主管工会经办人")
    private String xzgghjbr;

    @Schema(description = "现主管工会审核日期")
    private LocalDate xzgghsprq;

    @Schema(description = "省总工会审核意见（调拨相关）")
    private String sghspyj;

    @Schema(description = "省总工会负责人")
    private String sghfzr;

    @Schema(description = "省总工会经办人")
    private String sghjbr;

    @Schema(description = "省总工会审核日期")
    private LocalDate sghsprq;

    @Schema(description = "流程实例ID（调拨审批流程）", example = "3154")
    private String lcslId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}