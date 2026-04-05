package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfbfmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 经费拨付省总汇总 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JfbfmxSummaryResVO {

    @Schema(description = "单位代码")
    @ExcelProperty("单位代码")
    private String deptId;

    @Schema(description = "单位名称")
    @ExcelProperty("单位名称")
    private String dwmc;

    @Schema(description = "缴费笔数")
    @ExcelProperty("缴费笔数")
    private BigDecimal jfbs;

    @Schema(description = "缴费金额")
    @ExcelProperty("缴费金额")
    private BigDecimal jfje;

    @Schema(description = "省总金额")
    @ExcelProperty("省总金额")
    private BigDecimal szje;

    @Schema(description = "全总金额")
    @ExcelProperty("全总金额")
    private BigDecimal qzje;

    @Schema(description = "合计金额")
    @ExcelProperty("合计金额")
    private BigDecimal hjje;

    @Schema(description = "划款批次号")
    private String hkpch;
}
