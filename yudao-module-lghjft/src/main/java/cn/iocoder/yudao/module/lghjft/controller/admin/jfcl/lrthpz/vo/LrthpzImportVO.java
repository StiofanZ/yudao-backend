package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 退回凭证 Excel 导入 VO
 */
@Data
public class LrthpzImportVO {

    @ExcelProperty("划款批次号")
    private String hkpch;

    @ExcelProperty("序号")
    private Long xh;

    @ExcelProperty("类型")
    private String lx;

    @ExcelProperty("账号")
    private String zh;

    @ExcelProperty("户名")
    private String hm;

    @ExcelProperty("行号")
    private String hh;

    @ExcelProperty("金额")
    private BigDecimal je;

    @ExcelProperty("地址")
    private String dz;

    @ExcelProperty("附言")
    private String fy;

    @ExcelProperty("新账号")
    private String xzh;

    @ExcelProperty("新户名")
    private String xhm;

    @ExcelProperty("新行号")
    private String xhh;

    @ExcelProperty("备注")
    private String bz;
}
