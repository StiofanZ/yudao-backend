package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "经费分年明细聚合 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JffnmxSummaryResVO {

    @Schema(description = "上级代码")
    @ExcelProperty(value = "上级代码", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String sjdm;

    @Schema(description = "下级代码")
    @ExcelProperty(value = "下级代码", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String xjdm;

    @Schema(description = "工会机构代码")
    @ExcelProperty(value = "工会机构代码", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "月份 (yyyy-MM)")
    private String dsyf;

    @Schema(description = "年度 (yyyy)")
    @ExcelProperty("年度")
    private String nd;

    @Schema(description = "征收品目")
    private String zspmDm;

    @Schema(description = "税务机关")
    private String swjgDm;

    @Schema(description = "税款计征期")
    private String sjzq;

    @Schema(description = "笔数")
    @ExcelProperty("笔数")
    private Long bs;

    @Schema(description = "户数")
    @ExcelProperty("户数")
    private Long hs;

    @Schema(description = "应补退税额")
    @ExcelProperty("应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "基层工会金额")
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "行业工会金额")
    @ExcelProperty("行业工会金额")
    private BigDecimal hyghje;

    @Schema(description = "属地工会金额")
    @ExcelProperty("属地工会金额")
    private BigDecimal sdghje;

    @Schema(description = "筹备金金额")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "县级工会金额")
    @ExcelProperty("县级工会金额")
    private BigDecimal xjghje;

    @Schema(description = "市级工会金额")
    @ExcelProperty("市级工会金额")
    private BigDecimal sjghje;

    @Schema(description = "省总工会金额")
    @ExcelProperty("省总工会金额")
    private BigDecimal szghje;

    @Schema(description = "全总金额")
    @ExcelProperty("全总金额")
    private BigDecimal qgghje;

    @Schema(description = "税务机关金额")
    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;

    @Schema(description = "省地税金额")
    @ExcelProperty("省地税金额")
    private BigDecimal sdsje;
}
