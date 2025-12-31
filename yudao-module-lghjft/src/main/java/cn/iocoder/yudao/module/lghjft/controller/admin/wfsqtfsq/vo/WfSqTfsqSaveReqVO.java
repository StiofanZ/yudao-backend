package cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 申请退费 Request VO")
@Data
public class WfSqTfsqSaveReqVO extends WfSqTfsqKtfxxRespVO {

    @Schema(description = "退费金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal tfje;

}
