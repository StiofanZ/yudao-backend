package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 退回凭证 Excel 导入 VO
 * V1: hkxxId maps to bfid in gh_hkxx_yhbfmx, used to find the parent gh_hkxx row
 */
@Data
public class LrthpzImportVO {

    @ExcelProperty("hkxxId")
    private Long hkxxId;

    @ExcelProperty("收款人账号(必填)")
    private String zh;

    @ExcelProperty("收款人名称(必填)")
    private String hm;

    @ExcelProperty("收款人开户行行号(必填)")
    private String hh;

    @ExcelProperty("金额(必填)")
    private String je;

    @ExcelProperty("收款人地址(可选)")
    private String dz;

    @ExcelProperty("附言(可选)")
    private String fy;

    @ExcelProperty("预算单位代码(可选)")
    private String ysdwDm;

    @ExcelProperty("退库原因")
    private String thyy;

    @ExcelProperty(value = "退回日期")
    private Date thrq;

    @ExcelProperty("备注")
    private String bz;
}
