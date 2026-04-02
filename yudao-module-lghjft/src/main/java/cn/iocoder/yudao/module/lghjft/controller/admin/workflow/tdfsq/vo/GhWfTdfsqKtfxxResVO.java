package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 退抵费可退费明细响应VO")
@Data
public class GhWfTdfsqKtfxxResVO {

    @Schema(description = "税票UUID（唯一标识每笔缴费记录）", requiredMode = Schema.RequiredMode.REQUIRED, example = "uuid-20260301")
    private String spuuid;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "91330100MA2XXXXX01")
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "杭州未来科技有限公司")
    private String nsrmc;

    @Schema(description = "税款所属期起", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-01")
    private LocalDate skssqq;

    @Schema(description = "税款所属期止", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-31")
    private LocalDate skssqz;

    @Schema(description = "可退费金额（=入库金额-已退金额）", requiredMode = Schema.RequiredMode.REQUIRED, example = "5000.00")
    private BigDecimal ktfje;
//
//    @Schema(description = "入库金额（应缴纳的金额）", requiredMode = Schema.RequiredMode.REQUIRED, example = "5000.00")
//    private BigDecimal rkje;

}