package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 返基层账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FjcjfzzResVO {

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

    @Schema(description = "zh1")
    private String zh1;

    @Schema(description = "zh2")
    private String zh2;

    @Schema(description = "zh3")
    private String zh3;

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

    @Schema(description = "schkpch")
    private String schkpch;

    @Schema(description = "qrrq")
    private LocalDateTime qrrq;

    @Schema(description = "yhhdh")
    private String yhhdh;

    @Schema(description = "dzbj")
    private String dzbj;

    @Schema(description = "bz")
    private String bz;

    @Schema(description = "scbz")
    private String scbz;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
