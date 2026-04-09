package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 首页汇总信息分月 Response VO")
@Data
public class SyhzxxfyResVO {

    @Schema(description = "nd")
    @ExcelProperty("年度类型")
    private String nd;
    @Schema(description = "deptId")
    @ExcelProperty("工会机构")
    private String deptId;
    @Schema(description = "month1")
    @ExcelProperty("1月")
    private BigDecimal month1;
    @Schema(description = "month2")
    @ExcelProperty("2月")
    private BigDecimal month2;
    @Schema(description = "month3")
    @ExcelProperty("3月")
    private BigDecimal month3;
    @Schema(description = "month4")
    @ExcelProperty("4月")
    private BigDecimal month4;
    @Schema(description = "month5")
    @ExcelProperty("5月")
    private BigDecimal month5;
    @Schema(description = "month6")
    @ExcelProperty("6月")
    private BigDecimal month6;
    @Schema(description = "month7")
    @ExcelProperty("7月")
    private BigDecimal month7;
    @Schema(description = "month8")
    @ExcelProperty("8月")
    private BigDecimal month8;
    @Schema(description = "month9")
    @ExcelProperty("9月")
    private BigDecimal month9;
    @Schema(description = "month10")
    @ExcelProperty("10月")
    private BigDecimal month10;
    @Schema(description = "month11")
    @ExcelProperty("11月")
    private BigDecimal month11;
    @Schema(description = "month12")
    @ExcelProperty("12月")
    private BigDecimal month12;
    @Schema(description = "total")
    @ExcelProperty("当年截止当月")
    private BigDecimal total;
    @Schema(description = "totalsjszje")
    @ExcelProperty("当年截止当月")
    private BigDecimal totalsjszje;
    @Schema(description = "totalbjfcje")
    @ExcelProperty("截止当月分成")
    private BigDecimal totalbjfcje;
}
