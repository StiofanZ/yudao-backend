package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 分税务机关统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JftzfswjgResVO {

    @Schema(description = "dwdm")
    @ExcelProperty(value = "默认归属工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String dwdm;

    @Schema(description = "deptId")
    @ExcelProperty(value = "主管工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "zgswjDm")
    @ExcelProperty(value = "主管税务局", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zgswjDm;

    @Schema(description = "nd")
    @ExcelProperty("缴费年度")
    private String nd;

    @Schema(description = "bs")
    @ExcelProperty("笔数")
    private BigDecimal bs;

    @Schema(description = "hs")
    @ExcelProperty("户数")
    private BigDecimal hs;

    @Schema(description = "ybtse")
    @ExcelProperty("应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "jcghje")
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "hyghje")
    @ExcelProperty("行业工会金额")
    private BigDecimal hyghje;

    @Schema(description = "sdghje")
    @ExcelProperty("属地工会金额")
    private BigDecimal sdghje;

    @Schema(description = "cbjje")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "xjghje")
    @ExcelProperty("县级工会金额")
    private BigDecimal xjghje;

    @Schema(description = "sjghje")
    @ExcelProperty("市级工会金额")
    private BigDecimal sjghje;

    @Schema(description = "szghje")
    @ExcelProperty("省级工会金额")
    private BigDecimal szghje;

    @Schema(description = "qgghje")
    @ExcelProperty("全国工会金额")
    private BigDecimal qgghje;

    @Schema(description = "swjgje")
    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;

    @Schema(description = "sdsje")
    @ExcelProperty("所得税金额")
    private BigDecimal sdsje;
}
