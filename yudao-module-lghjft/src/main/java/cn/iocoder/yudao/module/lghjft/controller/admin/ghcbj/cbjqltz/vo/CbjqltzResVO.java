package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 筹备金清理台账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CbjqltzResVO {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "zgswjDm")
    private String zgswjDm;
    @Schema(description = "zgswskfjDm")
    private String zgswskfjDm;
    @Schema(description = "jdxzDm")
    private String jdxzDm;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "nsrztDm")
    private String nsrztDm;
    @Schema(description = "nsrztmc")
    private String nsrztmc;
    @Schema(description = "sjtbSj")
    private LocalDateTime sjtbSj;
    @Schema(description = "jsbj")
    private String jsbj;
    @Schema(description = "cbjthbj")
    private String cbjthbj;
    @Schema(description = "fbbj")
    private String fbbj;
    @Schema(description = "jfbs")
    private Long jfbs;
    @Schema(description = "jfje")
    private BigDecimal jfje;
    @Schema(description = "cbjje")
    private BigDecimal cbjje;
    @Schema(description = "fbbs")
    private Long fbbs;
    @Schema(description = "sfbje")
    private BigDecimal sfbje;
    @Schema(description = "rkrq")
    private LocalDateTime rkrq;
    @Schema(description = "yf")
    private Long yf;
    @Schema(description = "jfrkrq")
    private LocalDateTime jfrkrq;
    @Schema(description = "jfyf")
    private Long jfyf;
    @Schema(description = "subNsrztDm")
    private String subNsrztDm;
    @Schema(description = "subJfztDm")
    private String subJfztDm;
    @Schema(description = "subGzqrztDm")
    private String subGzqrztDm;
    @Schema(description = "subUpdateBy")
    private String subUpdateBy;
    @Schema(description = "subUpdateTime")
    private LocalDateTime subUpdateTime;
}
