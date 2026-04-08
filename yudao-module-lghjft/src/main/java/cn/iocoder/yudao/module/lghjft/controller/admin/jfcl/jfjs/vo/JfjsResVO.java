package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "管理后台 - 经费结算 Response VO")
@Data
public class JfjsResVO {

    @Schema(description = "入库日期")
    private Date rkrq;

    @Schema(description = "金额")
    private BigDecimal je;

    @Schema(description = "结算日期")
    private Date jsrq;

    @Schema(description = "划款日期")
    private Date hkrq;

    @Schema(description = "批次号")
    private String pch;
}
