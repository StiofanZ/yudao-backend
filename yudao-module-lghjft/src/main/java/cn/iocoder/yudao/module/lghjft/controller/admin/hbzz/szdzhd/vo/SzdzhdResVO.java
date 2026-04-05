package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 省总到账核对 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SzdzhdResVO {

    @ExcelProperty("省/市/行业")
    @Schema(description = "市级代码")
    private String sjdm;

    @Schema(description = "县级代码")
    private String xjdm;

    @Schema(description = "工会机构代码")
    private String deptId;

    @ExcelProperty("笔数")
    @Schema(description = "笔数")
    private BigDecimal jfbs;

    @ExcelProperty("缴费金额")
    @Schema(description = "缴费金额")
    private BigDecimal jfje;

    @ExcelProperty("筹备金金额")
    @Schema(description = "筹备金金额")
    private BigDecimal cbjje;

    @ExcelProperty("省总金额")
    @Schema(description = "省总金额")
    private BigDecimal szje;

    @ExcelProperty("全总金额")
    @Schema(description = "全总金额")
    private BigDecimal qzje;

    @ExcelProperty("省总全总合计")
    @Schema(description = "省总全总合计")
    private BigDecimal szqzhj;

    @ExcelProperty("合计金额")
    @Schema(description = "合计金额")
    private BigDecimal hjje;
}
