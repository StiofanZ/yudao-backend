package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 首页汇总信息分月 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SyhzxxfyResVO {

    @Schema(description = "nd")
    private String nd;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "month1")
    private BigDecimal month1;
    @Schema(description = "month2")
    private BigDecimal month2;
    @Schema(description = "month3")
    private BigDecimal month3;
    @Schema(description = "month4")
    private BigDecimal month4;
    @Schema(description = "month5")
    private BigDecimal month5;
    @Schema(description = "month6")
    private BigDecimal month6;
    @Schema(description = "month7")
    private BigDecimal month7;
    @Schema(description = "month8")
    private BigDecimal month8;
    @Schema(description = "month9")
    private BigDecimal month9;
    @Schema(description = "month10")
    private BigDecimal month10;
    @Schema(description = "month11")
    private BigDecimal month11;
    @Schema(description = "month12")
    private BigDecimal month12;
    @Schema(description = "total")
    private BigDecimal total;
    @Schema(description = "totalsjszje")
    private BigDecimal totalsjszje;
    @Schema(description = "totalbjfcje")
    private BigDecimal totalbjfcje;
}
