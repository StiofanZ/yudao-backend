package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分月代收情况(入库) Response VO")
@Data
@ExcelIgnoreUnannotated
public class SzjffydsqkResVO {

    @Schema(description = "单位代码")
    @ExcelProperty("单位代码")
    private String dwdm;

    @Schema(description = "单位名称")
    @ExcelProperty("单位名称")
    private String dwmc;

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
