package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 滞纳金做账 Response VO")
@Data
public class ZnjzzResVO {

    @Schema(description = "hkxxId")
    private Long hkxxId;

    @Schema(description = "hkpch")
    @ExcelProperty("划款批次号")
    private String hkpch;

    @Schema(description = "xh")
    @ExcelProperty("序号")
    private Long xh;

    @Schema(description = "lx")
    @ExcelProperty("类型")
    private String lx;

    @Schema(description = "zh")
    @ExcelProperty("账号")
    private String zh;

    @Schema(description = "hm")
    @ExcelProperty("户名")
    private String hm;

    @Schema(description = "hh")
    @ExcelProperty("行号")
    private String hh;

    @Schema(description = "je")
    @ExcelProperty("金额")
    private BigDecimal je;

    @Schema(description = "deptId")
    @ExcelProperty("工会机构")
    private String deptId;

    @Schema(description = "dz")
    @ExcelProperty("地址")
    private String dz;

    @Schema(description = "fy")
    @ExcelProperty("附言")
    private String fy;

    @Schema(description = "jym")
    @ExcelProperty("校验码")
    private String jym;

    @Schema(description = "thbj")
    @ExcelProperty("退回标志")
    private String thbj;

    @Schema(description = "thrq")
    @ExcelProperty("退回日期")
    private LocalDateTime thrq;

    @Schema(description = "thyy")
    @ExcelProperty("退回原因")
    private String thyy;

    @Schema(description = "schkpch")
    @ExcelProperty("生成划款批次号")
    private String schkpch;

    @Schema(description = "qrrq")
    @ExcelProperty("确认日期")
    private LocalDateTime qrrq;

    @Schema(description = "yhhdh")
    @ExcelProperty("银行回单号")
    private String yhhdh;

    @Schema(description = "dzbj")
    @ExcelProperty("到账标记")
    private String dzbj;

    @Schema(description = "bz")
    @ExcelProperty("备注")
    private String bz;

    @Schema(description = "scbz")
    @ExcelProperty("系统生成标志")
    private String scbz;

    @ExcelProperty("创建人")
    private String createBy;
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
    @ExcelProperty("更新人")
    private String updateBy;
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    /** 经费确认收账子表 (v1 cascade) */
    @Schema(description = "确认收账子表")
    private List<GhjfQrszItem> ghjfQrszList;

    @Data
    public static class GhjfQrszItem {
        private Long hkxxId;
        private String ghHkxxJym;
        @ExcelProperty("到账标记")
        private String dzbj;
        @ExcelProperty("确认日期")
        private LocalDateTime qrrq;
        @ExcelProperty("银行回单号")
        private String yhhdh;
        @ExcelProperty("备注")
        private String bz;
        @ExcelProperty("创建人")
        private String createBy;
        @ExcelProperty("创建时间")
        private LocalDateTime createTime;
        @ExcelProperty("更新人")
        private String updateBy;
        @ExcelProperty("更新时间")
        private LocalDateTime updateTime;
    }
}
