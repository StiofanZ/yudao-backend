package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
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

    @ExcelProperty("划款批次号")
    @Schema(description = "hkpch")
    private String hkpch;

    @Schema(description = "xh")
    private Long xh;

    @ExcelProperty(value = "分成类型", converter = DictConvert.class)
    @DictFormat("sys_fclx_type")
    @Schema(description = "lx")
    private String lx;

    @ExcelProperty("账号")
    @Schema(description = "zh")
    private String zh;

    @ExcelProperty("户名")
    @Schema(description = "hm")
    private String hm;

    @ExcelProperty("行号")
    @Schema(description = "hh")
    private String hh;

    @ExcelProperty(value = "市州产业", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    @Schema(description = "sjdm - 市州产业（computed）")
    private String sjdm;

    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    @Schema(description = "deptId")
    private String deptId;

    @ExcelProperty("金额")
    @Schema(description = "je")
    private BigDecimal je;

    @ExcelProperty("地址")
    @Schema(description = "dz")
    private String dz;

    @ExcelProperty("附言")
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

    @ExcelProperty("到账日期")
    @Schema(description = "qrrq - from qrsz subtable")
    private LocalDateTime qrrq;

    @ExcelProperty("做账凭证号")
    @Schema(description = "yhhdh - from qrsz subtable")
    private String yhhdh;

    @Schema(description = "dzbj - from qrsz subtable")
    private String dzbj;

    @ExcelProperty("备注")
    @Schema(description = "bz - from qrsz subtable")
    private String bz;

    @Schema(description = "scbz")
    private String scbz;

    @ExcelProperty("操作人员")
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
