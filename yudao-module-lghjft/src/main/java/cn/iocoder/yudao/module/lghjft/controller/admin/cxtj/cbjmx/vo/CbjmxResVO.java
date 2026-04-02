package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 筹备金统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CbjmxResVO {

    @Schema(description = "ghjfId")
    private Long ghjfId;
    @Schema(description = "spuuid")
    private String spuuid;
    @Schema(description = "zspmDm")
    private String zspmDm;
    @Schema(description = "nd")
    private String nd;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "rkrq")
    private LocalDateTime rkrq;
    @Schema(description = "jsrq")
    private LocalDateTime jsrq;
    @Schema(description = "skssqq")
    private LocalDateTime skssqq;
    @Schema(description = "skssqz")
    private LocalDateTime skssqz;
    @Schema(description = "jsbj")
    private String jsbj;
    @Schema(description = "cbjthbj")
    private String cbjthbj;
    @Schema(description = "qtje")
    private BigDecimal qtje;
    @Schema(description = "jfje")
    private BigDecimal jfje;
    @Schema(description = "cbjje")
    private BigDecimal cbjje;
    @Schema(description = "zjxcrq")
    private LocalDateTime zjxcrq;
    @Schema(description = "fbbj")
    private String fbbj;
    @Schema(description = "fbrq")
    private LocalDateTime fbrq;
    @Schema(description = "sfbje")
    private BigDecimal sfbje;
}
