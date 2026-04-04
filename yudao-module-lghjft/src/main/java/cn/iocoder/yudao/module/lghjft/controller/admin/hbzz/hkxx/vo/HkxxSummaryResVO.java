package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 基层返拨概况 Response VO")
@Data
public class HkxxSummaryResVO {

    @Schema(description = "总笔数", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    private Long totalCount;

    @Schema(description = "总金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "356800.12")
    private BigDecimal totalAmount;

    @Schema(description = "到账确认笔数", requiredMode = Schema.RequiredMode.REQUIRED, example = "96")
    private Long confirmedCount;

    @Schema(description = "到账确认金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "280000.00")
    private BigDecimal confirmedAmount;

    @Schema(description = "退回笔数", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    private Long returnedCount;

    @Schema(description = "退回金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "18000.00")
    private BigDecimal returnedAmount;
}
