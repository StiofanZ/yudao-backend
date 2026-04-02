package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 省总做账导出 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SzqzjzdcResVO {

    @Schema(description = "pzbh")
    @ExcelProperty("凭证编号")
    private String pzbh;

    @Schema(description = "flbh")
    @ExcelProperty("分录编号")
    private String flbh;

    @Schema(description = "hkpch")
    private String hkpch;

    @Schema(description = "zh")
    private String zh;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "kjn")
    @ExcelProperty("会计年")
    private String kjn;

    @Schema(description = "kjqj")
    @ExcelProperty("会计期间")
    private String kjqj;

    @Schema(description = "pzlx")
    @ExcelProperty("凭证类型")
    private String pzlx;

    @Schema(description = "pzrq")
    @ExcelProperty("凭证日期")
    private String pzrq;

    @Schema(description = "kjtx")
    @ExcelProperty("会计体系")
    private String kjtx;

    @Schema(description = "zy")
    @ExcelProperty("摘要")
    private String zy;

    @Schema(description = "kjkmm")
    @ExcelProperty("会计科目")
    private String kjkmm;

    @Schema(description = "jfje")
    @ExcelProperty("借方金额")
    private String jfje;

    @Schema(description = "dfje")
    @ExcelProperty("贷方金额")
    private BigDecimal dfje;

    @Schema(description = "fjzs")
    @ExcelProperty("附件张数")
    private String fjzs;

    @Schema(description = "ysdh")
    @ExcelProperty("原始单据号")
    private String ysdh;

    @Schema(description = "zt")
    @ExcelProperty("状态")
    private String zt;

    @Schema(description = "zdr")
    @ExcelProperty("制单人")
    private String zdr;

    @Schema(description = "shr")
    @ExcelProperty("审核人")
    private String shr;

    @Schema(description = "jzr")
    @ExcelProperty("记账人")
    private String jzr;

    @Schema(description = "zfcbwz")
    @ExcelProperty("政府储备物资")
    private String zfcbwz;

    @Schema(description = "zbtx")
    @ExcelProperty("指标特性")
    private String zbtx;

    @Schema(description = "ysly")
    @ExcelProperty("预算来源")
    private String ysly;

    @Schema(description = "wldw")
    @ExcelProperty("往来单位")
    private String wldw;

    @Schema(description = "bm")
    @ExcelProperty("部门")
    private String bm;

    @Schema(description = "ry")
    @ExcelProperty("人员")
    private String ry;

    @Schema(description = "bmysjjfl")
    @ExcelProperty("部门预算经济分类")
    private String bmysjjfl;

    @Schema(description = "zfgnfl")
    @ExcelProperty("支出功能分类")
    private String zfgnfl;

    @Schema(description = "zclx")
    @ExcelProperty("支出类型")
    private String zclx;

    @Schema(description = "jfly")
    @ExcelProperty("经费来源")
    private String jfly;

    @Schema(description = "zjxz")
    @ExcelProperty("资金性质")
    private String zjxz;

    @Schema(description = "zfysjjfl")
    @ExcelProperty("政府预算经济分类")
    private String zfysjjfl;

    @Schema(description = "mxkm")
    @ExcelProperty("明细科目")
    private String mxkm;

    @Schema(description = "zffs")
    @ExcelProperty("支付方式")
    private String zffs;

    @Schema(description = "xm")
    @ExcelProperty("项目")
    private String xm;

    @Schema(description = "jsfs")
    @ExcelProperty("结算方式")
    private String jsfs;

    @Schema(description = "xjgh")
    @ExcelProperty("下级工会")
    private String xjgh;

    @Schema(description = "xmhd")
    private String xmhd;

    @Schema(description = "ggjcss")
    @ExcelProperty("公共基础设施")
    private String ggjcss;

    @Schema(description = "jfsl")
    @ExcelProperty("借方数量")
    private String jfsl;

    @Schema(description = "dfsl")
    @ExcelProperty("贷方数量")
    private String dfsl;

    @Schema(description = "dj")
    @ExcelProperty("单价")
    private String dj;
}
