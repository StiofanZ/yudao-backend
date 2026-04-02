package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 金融保险证券单位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JrbxzqdwResVO {

    @Schema(description = "id")
    private String id;
    @Schema(description = "dwdm")
    private String dwdm;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrsbh")
    private String nsrsbh;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "zgjrghzgdwbz")
    private String zgjrghzgdwbz;
    @Schema(description = "ghlbDm")
    private String ghlbDm;
    @Schema(description = "xtlbDm")
    private String xtlbDm;
    @Schema(description = "nsrztDm")
    private String nsrztDm;
    @Schema(description = "djzclxDm")
    private String djzclxDm;
    @Schema(description = "djzclxmc")
    private String djzclxmc;
    @Schema(description = "scjydz")
    private String scjydz;
    @Schema(description = "scjydzxzqhszDm")
    private String scjydzxzqhszDm;
    @Schema(description = "xzqhmc")
    private String xzqhmc;
    @Schema(description = "jdxzDm")
    private String jdxzDm;
    @Schema(description = "hyDm")
    private String hyDm;
    @Schema(description = "hymc")
    private String hymc;
    @Schema(description = "djjgDm")
    private String djjgDm;
    @Schema(description = "djjgmc")
    private String djjgmc;
    @Schema(description = "zgswjDm")
    private String zgswjDm;
    @Schema(description = "zgswjmc")
    private String zgswjmc;
    @Schema(description = "zgswskfjDm")
    private String zgswskfjDm;
    @Schema(description = "zgksfjmc")
    private String zgksfjmc;
    @Schema(description = "cwfzrxm")
    private String cwfzrxm;
    @Schema(description = "cwfzrgddh")
    private String cwfzrgddh;
    @Schema(description = "cwfzryddh")
    private String cwfzryddh;
    @Schema(description = "cyrs")
    private BigDecimal cyrs;
    @Schema(description = "bz")
    private String bz;
    @Schema(description = "hsjg")
    private String hsjg;
    @Schema(description = "gzze")
    private BigDecimal gzze;
    @Schema(description = "bs")
    private Long bs;
    @Schema(description = "je")
    private BigDecimal je;
    @Schema(description = "sl")
    private String sl;
    @Schema(description = "bs2024")
    private Long bs2024;
    @Schema(description = "je2024")
    private BigDecimal je2024;
    @Schema(description = "bs2023")
    private Long bs2023;
    @Schema(description = "je2023")
    private BigDecimal je2023;
    @Schema(description = "bs2022")
    private Long bs2022;
    @Schema(description = "je2022")
    private BigDecimal je2022;
    @Schema(description = "bs2021")
    private Long bs2021;
    @Schema(description = "je2021")
    private BigDecimal je2021;
    @Schema(description = "bs2020")
    private Long bs2020;
    @Schema(description = "je2020")
    private BigDecimal je2020;
}
