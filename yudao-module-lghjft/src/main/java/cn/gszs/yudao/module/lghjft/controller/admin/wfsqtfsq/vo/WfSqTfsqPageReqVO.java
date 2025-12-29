package cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 申请-退费申请分页 Request VO")
@Data
public class WfSqTfsqPageReqVO extends PageParam {

    @Schema(description = "退费申请明细ID", example = "22410")
    private Long tfsqmxId;

    @Schema(description = "申请退费类型代码")
    private Integer sqtflxDm;

    @Schema(description = "流程实例的编号", example = "9171")
    private String processInstanceId;

    @Schema(description = "审批结果", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}