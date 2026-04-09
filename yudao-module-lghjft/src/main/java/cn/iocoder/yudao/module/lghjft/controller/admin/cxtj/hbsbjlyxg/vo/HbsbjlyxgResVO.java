package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 划拨失败已修改 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HbsbjlyxgResVO {

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
    private String thrq;
    @Schema(description = "thyy")
    @ExcelProperty("退回原因")
    private String thyy;
    @Schema(description = "xgbj")
    @ExcelProperty("修改标志")
    private String xgbj;
    @Schema(description = "hkxxidgl")
    @ExcelProperty("划款信息关联")
    private String hkxxidgl;
    @Schema(description = "schkpch")
    @ExcelProperty("生成划款批次号")
    private String schkpch;
    @Schema(description = "bz")
    @ExcelProperty("备注信息")
    private String bz;
    @Schema(description = "scbz")
    @ExcelProperty("系统生成标志")
    private String scbz;
    @Schema(description = "xgr")
    @ExcelProperty("修改人")
    private String xgr;
    @Schema(description = "xgsj")
    @ExcelProperty("修改时间")
    private String xgsj;
}
