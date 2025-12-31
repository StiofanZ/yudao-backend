package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqtfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 申请-退费申请明细 Response VO")
@Data
public class WfSqTfsqmxRespVO {

    @Schema(description = "退费申请明细ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "税票UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "uuid-1234")
    private String spuuid;

    @Schema(description = "入库金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal rkje;

    @Schema(description = "退费审批金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal tfsqJe;

    @Schema(description = "社会信用代码", example = "91110105MA00BQ4C3L")
    private String shxydm;

    @Schema(description = "纳税人名称", example = "某某公司")
    private String nsrmc;

    @Schema(description = "税款所属期起", example = "2023-01-01")
    private LocalDate skssqq;

    @Schema(description = "税款所属期止", example = "2023-01-31")
    private LocalDate skssqz;

}
