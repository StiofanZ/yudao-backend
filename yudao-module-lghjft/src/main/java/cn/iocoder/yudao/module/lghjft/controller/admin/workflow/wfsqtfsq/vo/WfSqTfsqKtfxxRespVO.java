package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqtfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 可退费信息 Response VO")
@Data
public class WfSqTfsqKtfxxRespVO {

    @Schema(description = "税票UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "uuid-1234")
    private String spuuid;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "91110108551385082Q")
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "某某公司")
    private String nsrmc;

    @Schema(description = "税款所属期起", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-12-01")
    private LocalDate skssqq;

    @Schema(description = "税款所属期止", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-12-31")
    private LocalDate skssqz;

    @Schema(description = "可退费金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal ktfje;

}
