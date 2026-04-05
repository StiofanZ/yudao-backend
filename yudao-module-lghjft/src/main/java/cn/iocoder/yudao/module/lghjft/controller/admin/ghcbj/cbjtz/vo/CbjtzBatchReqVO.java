package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 筹备金批量确认返拨 Request VO")
@Data
public class CbjtzBatchReqVO {

    @Schema(description = "工会经费ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工会经费ID不能为空")
    private Long ghjfId;

    @Schema(description = "审批UUID")
    private String spuuid;

    @Schema(description = "实返拨金额")
    private BigDecimal sfbje;

    @Schema(description = "划款凭证号")
    private String hkpzh;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "返拨日期")
    private LocalDateTime fbrq;
}
