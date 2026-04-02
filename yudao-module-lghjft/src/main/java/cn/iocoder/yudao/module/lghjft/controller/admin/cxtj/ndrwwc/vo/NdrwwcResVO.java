package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分年各级分成情况 Response VO")
@Data
@ExcelIgnoreUnannotated
public class NdrwwcResVO {

    @Schema(description = "nd")
    private String nd;
    @Schema(description = "dwdm")
    private String dwdm;
    @Schema(description = "dwmc")
    private String dwmc;
    @Schema(description = "bs")
    private Long bs;
    @Schema(description = "hs")
    private Long hs;
    @Schema(description = "jfje")
    private BigDecimal jfje;
    @Schema(description = "cbjje")
    private BigDecimal cbjje;
    @Schema(description = "jcghje")
    private BigDecimal jcghje;
    @Schema(description = "hyghje")
    private BigDecimal hyghje;
    @Schema(description = "sdghje")
    private BigDecimal sdghje;
    @Schema(description = "xjghje")
    private BigDecimal xjghje;
    @Schema(description = "sjghje")
    private BigDecimal sjghje;
    @Schema(description = "szghje")
    private BigDecimal szghje;
    @Schema(description = "qgghje")
    private BigDecimal qgghje;
    @Schema(description = "szqzhj")
    private BigDecimal szqzhj;
    @Schema(description = "sdsje")
    private BigDecimal sdsje;
    @Schema(description = "swjgje")
    private BigDecimal swjgje;
}
