package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分月分成情况 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FyfcqkResVO {

    @Schema(description = "dsyf")
    private String dsyf;
    @Schema(description = "rkjf")
    private BigDecimal rkjf;
    @Schema(description = "tkjf")
    private BigDecimal tkjf;
    @Schema(description = "ygfcjf")
    private BigDecimal ygfcjf;
    @Schema(description = "yfcjf")
    private BigDecimal yfcjf;
    @Schema(description = "wfcjf")
    private BigDecimal wfcjf;
    @Schema(description = "szfc")
    private BigDecimal szfc;
    @Schema(description = "znj")
    private BigDecimal znj;
    @Schema(description = "qzfc")
    private BigDecimal qzfc;
    @Schema(description = "hj")
    private BigDecimal hj;
}
