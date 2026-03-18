package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 银行拨付汇总 Response VO")
@Data
@ExcelIgnoreUnannotated
public class YhbfhzRespVO {

    @Schema(description = "拨付汇总ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31361")
    @ExcelProperty("拨付汇总ID")
    private Integer bfhzid;

    @Schema(description = "拨付汇总批次号")
    @ExcelProperty("拨付汇总批次号")
    private String bfhzpch;

    @Schema(description = "业务参考号起", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("业务参考号起")
    private Long bfidq;

    @Schema(description = "业务参考号止", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("业务参考号止")
    private Long bfidz;

    @Schema(description = "拨付包名称")
    @ExcelProperty("拨付包名称")
    private String bfbmc;

    @Schema(description = "拨付笔数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("拨付笔数")
    private Long bs;

    @Schema(description = "拨付合计金额")
    @ExcelProperty("拨付合计金额")
    private BigDecimal hzje;

    @Schema(description = "退回笔数")
    @ExcelProperty("退回笔数")
    private String thbs;

    @Schema(description = "退回金额")
    @ExcelProperty("退回金额")
    private BigDecimal thje;

    @Schema(description = "失败笔数")
    @ExcelProperty("失败笔数")
    private String sbbs;

    @Schema(description = "失败金额")
    @ExcelProperty("失败金额")
    private BigDecimal sbje;

    @Schema(description = "成功笔数")
    @ExcelProperty("成功笔数")
    private String cgbs;

    @Schema(description = "成功金额")
    @ExcelProperty("成功金额")
    private BigDecimal cgje;

    @Schema(description = "唯一识别号", example = "4194")
    @ExcelProperty("唯一识别号")
    private String uuid;

    @Schema(description = "拨付状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("拨付状态")
    private String bfzt;

    @Schema(description = "处理结果")
    @ExcelProperty("处理结果")
    private String bfjg;

    @Schema(description = "退回日期")
    @ExcelProperty("退回日期")
    private String thrq;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("修改人")
    private String updateBy;

    @Schema(description = "全部业务参考号")
    @ExcelProperty("全部业务参考号")
    private String bfidStr;

}