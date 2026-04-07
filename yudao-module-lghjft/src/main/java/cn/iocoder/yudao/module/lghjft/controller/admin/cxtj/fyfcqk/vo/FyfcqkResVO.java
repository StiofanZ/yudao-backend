package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分月分成情况 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FyfcqkResVO {

    @Schema(description = "代收月份")
    @ExcelProperty("代收月份")
    private String dsyf;

    @Schema(description = "入库经费")
    @ExcelProperty("入库经费")
    private BigDecimal rkjf;

    @Schema(description = "退库经费")
    @ExcelProperty("退库经费")
    private BigDecimal tkjf;

    @Schema(description = "应分成经费")
    @ExcelProperty("应分成经费")
    private BigDecimal ygfcjf;

    @Schema(description = "已分成经费")
    @ExcelProperty("已分成经费")
    private BigDecimal yfcjf;

    @Schema(description = "未分成经费")
    @ExcelProperty("未分成经费")
    private BigDecimal wfcjf;

    @Schema(description = "省总分成")
    @ExcelProperty("省总分成")
    private BigDecimal szfc;

    @Schema(description = "滞纳金")
    @ExcelProperty("滞纳金")
    private BigDecimal znj;

    @Schema(description = "全总分成")
    @ExcelProperty("全总分成")
    private BigDecimal qzfc;

    @Schema(description = "合计分成（省总+全总）")
    @ExcelProperty("合计分成（省总+全总）")
    private BigDecimal hj;
}
