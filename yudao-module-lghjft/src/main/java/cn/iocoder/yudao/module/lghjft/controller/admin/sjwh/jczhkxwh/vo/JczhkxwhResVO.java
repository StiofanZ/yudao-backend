package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 基层账户空需维护 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JczhkxwhResVO {

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "hyghbz")
    private String hyghbz;

    @Schema(description = "djxh")
    private String djxh;

    @Schema(description = "shxydm")
    private String shxydm;

    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "nsrjc")
    private String nsrjc;

    @Schema(description = "zgswjDm")
    private String zgswjDm;

    @Schema(description = "zgswjmc")
    private String zgswjmc;

    @Schema(description = "zgswskfjDm")
    private String zgswskfjDm;

    @Schema(description = "zgswskfjmc")
    private String zgswskfjmc;

    @Schema(description = "ssglyDm")
    private String ssglyDm;

    @Schema(description = "ssglyxm")
    private String ssglyxm;

    @Schema(description = "zzjglxDm")
    private String zzjglxDm;

    @Schema(description = "zzjglxmc")
    private String zzjglxmc;

    @Schema(description = "hyDm")
    private String hyDm;

    @Schema(description = "hymc")
    private String hymc;

    @Schema(description = "djzclxDm")
    private String djzclxDm;

    @Schema(description = "djzclxmc")
    private String djzclxmc;

    @Schema(description = "dwlsgxDm")
    private String dwlsgxDm;

    @Schema(description = "dwlsgxmc")
    private String dwlsgxmc;

    @Schema(description = "zgrs")
    private BigDecimal zgrs;

    @Schema(description = "nsrztDm")
    private String nsrztDm;

    @Schema(description = "nsrztmc")
    private String nsrztmc;

    @Schema(description = "fzcrq")
    private LocalDateTime fzcrq;

    @Schema(description = "zxrq")
    private LocalDateTime zxrq;

    @Schema(description = "zcdz")
    private String zcdz;

    @Schema(description = "yzbm")
    private String yzbm;

    @Schema(description = "lxr")
    private String lxr;

    @Schema(description = "lxdh")
    private String lxdh;

    @Schema(description = "ghlbDm")
    private String ghlbDm;

    @Schema(description = "xtlbDm")
    private String xtlbDm;

    @Schema(description = "hjfl1Dm")
    private String hjfl1Dm;

    @Schema(description = "hjfl2Dm")
    private String hjfl2Dm;

    @Schema(description = "hjfl3Dm")
    private String hjfl3Dm;

    @Schema(description = "hjfl4Dm")
    private String hjfl4Dm;

    @Schema(description = "hjfl5Dm")
    private String hjfl5Dm;

    @Schema(description = "hjfl6Dm")
    private String hjfl6Dm;

    @Schema(description = "hjfl7Dm")
    private String hjfl7Dm;

    @Schema(description = "dclghbj")
    private String dclghbj;

    @Schema(description = "xwyqbj")
    private String xwyqbj;

    @Schema(description = "sdghjgDm")
    private String sdghjgDm;

    @Schema(description = "clghbj")
    private String clghbj;

    @Schema(description = "clghrq")
    private LocalDateTime clghrq;

    @Schema(description = "jcghdm")
    private String jcghdm;

    @Schema(description = "jcghmc")
    private String jcghmc;

    @Schema(description = "jcghzh")
    private String jcghzh;

    @Schema(description = "jcghhm")
    private String jcghhm;

    @Schema(description = "jcghhh")
    private String jcghhh;

    @Schema(description = "jcghyh")
    private String jcghyh;

    @Schema(description = "bz")
    private String bz;

    @Schema(description = "jym")
    private String jym;

    @Schema(description = "nsrsbh")
    private String nsrsbh;
}
