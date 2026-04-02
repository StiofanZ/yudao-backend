package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小微经费统计汇总 Response VO")
@Data
@ExcelIgnoreUnannotated
public class XwqyjftjAggVO {

    @Schema(description = "单位代码")
    @ExcelProperty("单位代码")
    private String dwdm;

    @Schema(description = "部门ID")
    @ExcelProperty("部门ID")
    private String deptId;

    @Schema(description = "结算标记")
    @ExcelProperty("结算标记")
    private String jsbj;

    @Schema(description = "承包金退还标记")
    @ExcelProperty("承包金退还标记")
    private String cbjthbj;

    @Schema(description = "缴费笔数")
    @ExcelProperty("缴费笔数")
    private Long jfbs;

    @Schema(description = "缴费户数")
    @ExcelProperty("缴费户数")
    private Long jfhs;

    @Schema(description = "应补退税额")
    @ExcelProperty("应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "应付金额")
    @ExcelProperty("应付金额")
    private BigDecimal yfje;

    @Schema(description = "国库支付金额")
    @ExcelProperty("国库支付金额")
    private BigDecimal gkzfje;

    @Schema(description = "国家退还金额")
    @ExcelProperty("国家退还金额")
    private BigDecimal gjthje;

    @Schema(description = "基础归户金额")
    @ExcelProperty("基础归户金额")
    private BigDecimal jcghje;

    @Schema(description = "承包金金额")
    @ExcelProperty("承包金金额")
    private BigDecimal cbjje;

    @Schema(description = "行业归户金额")
    @ExcelProperty("行业归户金额")
    private BigDecimal hyghje;

    @Schema(description = "乡镇归户金额")
    @ExcelProperty("乡镇归户金额")
    private BigDecimal xjghje;

    @Schema(description = "市局归户金额")
    @ExcelProperty("市局归户金额")
    private BigDecimal sjghje;

    @Schema(description = "省直归户金额")
    @ExcelProperty("省直归户金额")
    private BigDecimal szghje;

    @Schema(description = "全国归户金额")
    @ExcelProperty("全国归户金额")
    private BigDecimal qgghje;

    @Schema(description = "所得税金额")
    @ExcelProperty("所得税金额")
    private BigDecimal sdsje;

    @Schema(description = "税务机关金额")
    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;
}
