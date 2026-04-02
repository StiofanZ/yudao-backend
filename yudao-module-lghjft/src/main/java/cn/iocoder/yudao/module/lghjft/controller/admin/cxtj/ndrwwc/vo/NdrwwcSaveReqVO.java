package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分年各级分成情况新增/修改 Request VO")
@Data
public class NdrwwcSaveReqVO {

    private String nd;
    private String dwdm;
    private String dwmc;
    private Long bs;
    private Long hs;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private BigDecimal jcghje;
    private BigDecimal hyghje;
    private BigDecimal sdghje;
    private BigDecimal xjghje;
    private BigDecimal sjghje;
    private BigDecimal szghje;
    private BigDecimal qgghje;
    private BigDecimal szqzhj;
    private BigDecimal sdsje;
    private BigDecimal swjgje;
}
