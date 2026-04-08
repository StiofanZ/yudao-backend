package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 筹备金做账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CbjzzResVO {

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

    @Schema(description = "sjdm - 市州产业（computed）")
    @ExcelProperty("市州产业")
    private String sjdm;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "je")
    @ExcelProperty("金额")
    private BigDecimal je;

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

    @Schema(description = "qrrq - from qrsz subtable")
    private LocalDateTime qrrq;

    @Schema(description = "yhhdh - from qrsz subtable")
    private String yhhdh;

    @Schema(description = "dzbj - from qrsz subtable")
    private String dzbj;

    @Schema(description = "bz - from qrsz subtable")
    private String bz;

    @Schema(description = "scbz")
    private String scbz;

    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;

    /** 筹备金确认收账子表 (v1 cascade) */
    @Schema(description = "确认收账子表")
    private List<CbjQrszItem> cbjQrszList;

    @Data
    public static class CbjQrszItem {
        private Long hkxxId;
        private String ghHkxxJym;
        private String dzbj;
        private LocalDateTime qrrq;
        private String yhhdh;
        private String bz;
        private String createBy;
        private LocalDateTime createTime;
        private String updateBy;
        private LocalDateTime updateTime;
    }
}
