package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分月代收情况 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FydsqkResVO {

    @Schema(description = "dsyf")
    private String dsyf;
    @Schema(description = "rkjf")
    private BigDecimal rkjf;
    @Schema(description = "ghjf")
    private BigDecimal ghjf;
    @Schema(description = "cbj")
    private BigDecimal cbj;
    @Schema(description = "znj")
    private BigDecimal znj;
}
