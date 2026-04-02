package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 首页汇总信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SyhzxxResVO {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "bzhs")
    private Long bzhs;
    @Schema(description = "bzje")
    private BigDecimal bzje;
    @Schema(description = "byhs")
    private Long byhs;
    @Schema(description = "byje")
    private BigDecimal byje;
    @Schema(description = "bnhs")
    private Long bnhs;
    @Schema(description = "bnje")
    private BigDecimal bnje;
    @Schema(description = "rkwwhhs")
    private Long rkwwhhs;
    @Schema(description = "rkwwhje")
    private BigDecimal rkwwhje;
    @Schema(description = "jczhkhs")
    private Long jczhkhs;
    @Schema(description = "jczhkje")
    private BigDecimal jczhkje;
    @Schema(description = "bfsbbs")
    private Long bfsbbs;
    @Schema(description = "bfsbje")
    private BigDecimal bfsbje;
}
