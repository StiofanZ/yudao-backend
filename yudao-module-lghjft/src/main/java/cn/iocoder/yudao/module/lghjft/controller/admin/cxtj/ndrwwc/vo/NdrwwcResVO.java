package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分上缴周期统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class NdrwwcResVO {

    @Schema(description = "结算周期")
    private String nd;

    @Schema(description = "单位代码")
    private String dwdm;

    @Schema(description = "单位名称")
    @ExcelProperty("单位名称")
    private String dwmc;

    @Schema(description = "缴费笔数")
    @ExcelProperty("缴费笔数")
    private Long bs;

    @Schema(description = "缴费户数")
    @ExcelProperty("缴费户数")
    private Long hs;

    @Schema(description = "缴费金额")
    @ExcelProperty("缴费金额")
    private BigDecimal jfje;

    @Schema(description = "筹备金金额")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "基层金额")
    @ExcelProperty("基层金额")
    private BigDecimal jcghje;

    @Schema(description = "行业金额")
    @ExcelProperty("行业金额")
    private BigDecimal hyghje;

    @Schema(description = "属地金额")
    @ExcelProperty("属地金额")
    private BigDecimal sdghje;

    @Schema(description = "县级金额")
    @ExcelProperty("县级金额")
    private BigDecimal xjghje;

    @Schema(description = "市级金额")
    @ExcelProperty("市级金额")
    private BigDecimal sjghje;

    @Schema(description = "省总金额")
    @ExcelProperty("省总金额")
    private BigDecimal szghje;

    @Schema(description = "全总金额")
    @ExcelProperty("全总金额")
    private BigDecimal qgghje;

    @Schema(description = "省总全总合计")
    @ExcelProperty("省总全总合计")
    private BigDecimal szqzhj;

    @Schema(description = "省税局金额")
    @ExcelProperty("省税局金额")
    private BigDecimal sdsje;

    @Schema(description = "市县税局金额")
    @ExcelProperty("市县税局金额")
    private BigDecimal swjgje;
}
