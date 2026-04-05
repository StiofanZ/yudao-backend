package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 划拨失败已修改 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HbsbjlyxgResVO {

    @Schema(description = "hkxxId")
    private Long hkxxId;
    @Schema(description = "hkpch")
    private String hkpch;
    @Schema(description = "xh")
    private Long xh;
    @Schema(description = "lx")
    private String lx;
    @Schema(description = "zh")
    private String zh;
    @Schema(description = "hm")
    private String hm;
    @Schema(description = "hh")
    private String hh;
    @Schema(description = "xzh")
    private String xzh;
    @Schema(description = "xhm")
    private String xhm;
    @Schema(description = "xhh")
    private String xhh;
    @Schema(description = "je")
    private BigDecimal je;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "dz")
    private String dz;
    @Schema(description = "fy")
    private String fy;
    @Schema(description = "jym")
    private String jym;
    @Schema(description = "thbj")
    private String thbj;
    @Schema(description = "thrq")
    private LocalDateTime thrq;
    @Schema(description = "thyy")
    private String thyy;
    @Schema(description = "xgbj")
    private String xgbj;
    @Schema(description = "hkxxidgl")
    private String hkxxidgl;
    @Schema(description = "schkpch")
    private String schkpch;
    @Schema(description = "bz")
    private String bz;
    @Schema(description = "scbz")
    private String scbz;
    @Schema(description = "xgr")
    private String xgr;
    @Schema(description = "xgsj")
    private LocalDateTime xgsj;
}
