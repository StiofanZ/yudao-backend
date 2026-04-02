package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 缴费排行 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TopResVO {

    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "dwmc")
    private String dwmc;
    @Schema(description = "bsdn")
    private Long bsdn;
    @Schema(description = "bswn")
    private Long bswn;
    @Schema(description = "bscy")
    private Long bscy;
    @Schema(description = "jfjedn")
    private BigDecimal jfjedn;
    @Schema(description = "jfjewn")
    private BigDecimal jfjewn;
    @Schema(description = "jfjecy")
    private BigDecimal jfjecy;
    @Schema(description = "jfbl")
    private String jfbl;
    @Schema(description = "sjjedn")
    private BigDecimal sjjedn;
    @Schema(description = "sjjewn")
    private BigDecimal sjjewn;
    @Schema(description = "sjjecy")
    private BigDecimal sjjecy;
    @Schema(description = "sjbl")
    private String sjbl;
}
