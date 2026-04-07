package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分月代收情况 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FydsqkResVO {

    @Schema(description = "代收月份")
    @ExcelProperty("代收月份")
    private String dsyf;

    @Schema(description = "入库经费")
    @ExcelProperty("入库经费")
    private BigDecimal rkjf;

    @Schema(description = "工会经费")
    @ExcelProperty("工会经费")
    private BigDecimal ghjf;

    @Schema(description = "筹备金")
    @ExcelProperty("筹备金")
    private BigDecimal cbj;

    @Schema(description = "滞纳金")
    @ExcelProperty("滞纳金")
    private BigDecimal znj;
}
