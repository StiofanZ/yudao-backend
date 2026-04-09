package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 返基层账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FjcjfzzResVO {

    @Schema(description = "hkxxId")
    private Long hkxxId;

    @Schema(description = "hkpch")
    @ExcelProperty("划款批次号")
    private String hkpch;

    @Schema(description = "xh")
    @ExcelProperty("序号")
    private Long xh;

    @Schema(description = "lx")
    @ExcelProperty(value = "类型", converter = DictConvert.class)
    @DictFormat("sys_fclx_type")
    private String lx;

    @Schema(description = "zh")
    @ExcelProperty("账号")
    private String zh;

    @Schema(description = "zh1")
    private String zh1;

    @Schema(description = "zh2")
    private String zh2;

    @Schema(description = "zh3")
    private String zh3;

    @Schema(description = "hm")
    @ExcelProperty("户名")
    private String hm;

    @Schema(description = "hh")
    @ExcelProperty("行号")
    private String hh;

    @Schema(description = "xzh")
    @ExcelProperty("新账号")
    private String xzh;

    @Schema(description = "xhm")
    @ExcelProperty("新户名")
    private String xhm;

    @Schema(description = "xhh")
    @ExcelProperty("新行号")
    private String xhh;

    @Schema(description = "je")
    @ExcelProperty("金额")
    private BigDecimal je;

    @Schema(description = "deptId")
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "dz")
    @ExcelProperty("地址")
    private String dz;

    @Schema(description = "fy")
    @ExcelProperty("附言")
    private String fy;

    @Schema(description = "jym")
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
    private String scbz;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "筹备金确认收账子表")
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
