package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.hbfhz.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 银行拨付汇总分页 Request VO")
@Data
public class YhbfhzPageReqVO extends PageParam {

    @Schema(description = "拨付汇总批次号")
    private String bfhzpch;

    @Schema(description = "业务参考号起")
    private Long bfidq;

    @Schema(description = "业务参考号止")
    private Long bfidz;

    @Schema(description = "拨付包名称")
    private String bfbmc;

    @Schema(description = "拨付笔数")
    private Long bs;

    @Schema(description = "拨付合计金额")
    private BigDecimal hzje;

    @Schema(description = "退回笔数")
    private String thbs;

    @Schema(description = "退回金额")
    private BigDecimal thje;

    @Schema(description = "失败笔数")
    private String sbbs;

    @Schema(description = "失败金额")
    private BigDecimal sbje;

    @Schema(description = "成功笔数")
    private String cgbs;

    @Schema(description = "成功金额")
    private BigDecimal cgje;

    @Schema(description = "唯一识别号", example = "4194")
    private String uuid;

    @Schema(description = "拨付状态")
    private String bfzt;

    @Schema(description = "处理结果")
    private String bfjg;

    @Schema(description = "退回日期")
    private String thrq;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "修改人")
    private String updateBy;

    @Schema(description = "全部业务参考号")
    private String bfidStr;

//    日期选择
    private  String endCreateTime;
    private  String beginCreateTime;
}