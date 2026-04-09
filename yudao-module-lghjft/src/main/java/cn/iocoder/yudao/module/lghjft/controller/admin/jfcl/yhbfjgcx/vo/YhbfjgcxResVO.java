package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 银行拨付结果查询 Response VO")
@Data
@ExcelIgnoreUnannotated
public class YhbfjgcxResVO {

    @Schema(description = "拨付批次号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("拨付批次号")
    private String bfpch;

    @Schema(description = "拨付日期")
    @ExcelProperty("拨付日期")
    private String bfrq;

    @Schema(description = "拨付汇总ID", example = "7799")
    @ExcelProperty("拨付汇总ID")
    private String bfhzid;

    @Schema(description = "拨付包名称")
    @ExcelProperty("拨付包名称")
    private String bfbmc;

    @Schema(description = "拨付汇总批次号")
    @ExcelProperty("拨付汇总批次号")
    private String bfhzpch;

    @Schema(description = "拨付状态")
    @ExcelProperty(value = "拨付状态", converter = DictConvert.class)
    @DictFormat("sys_jfbfzt")
    private String bfzt;

    @Schema(description = "拨付总笔数")
    @ExcelProperty("拨付总笔数")
    private Long bfzbs;

    @Schema(description = "拨付总金额")
    @ExcelProperty("拨付总金额")
    private BigDecimal bfzje;

    @Schema(description = "成功结果")
    @ExcelProperty(value = "成功结果", converter = DictConvert.class)
    @DictFormat("sys_yhbfjg")
    private String cgjg;

    @Schema(description = "成功笔数")
    @ExcelProperty("成功笔数")
    private Long cgbs;

    @Schema(description = "成功金额")
    @ExcelProperty("成功金额")
    private BigDecimal cgje;

    @Schema(description = "失败结果")
    @ExcelProperty(value = "失败结果", converter = DictConvert.class)
    @DictFormat("sys_yhbfjg")
    private String sbjg;

    @Schema(description = "失败笔数")
    @ExcelProperty("失败笔数")
    private Long sbbs;

    @Schema(description = "失败金额")
    @ExcelProperty("失败金额")
    private BigDecimal sbje;

    @Schema(description = "退票结果")
    @ExcelProperty(value = "退票结果", converter = DictConvert.class)
    @DictFormat("sys_yhbfjg")
    private String tpjg;

    @Schema(description = "退票笔数")
    @ExcelProperty("退票笔数")
    private Long tpbs;

    @Schema(description = "退票金额")
    @ExcelProperty("退票金额")
    private BigDecimal tpje;

    @Schema(description = "否决结果")
    @ExcelProperty(value = "否决结果", converter = DictConvert.class)
    @DictFormat("sys_yhbfjg")
    private String fjjg;

    @Schema(description = "否决笔数")
    @ExcelProperty("否决笔数")
    private Long fjbs;

    @Schema(description = "否决金额")
    @ExcelProperty("否决金额")
    private BigDecimal fjje;

    @Schema(description = "过期结果")
    @ExcelProperty(value = "过期结果", converter = DictConvert.class)
    @DictFormat("sys_yhbfjg")
    private String gqjg;

    @Schema(description = "过期笔数")
    @ExcelProperty("过期笔数")
    private Long gqbs;

    @Schema(description = "过期金额")
    @ExcelProperty("过期金额")
    private BigDecimal gqje;

    @Schema(description = "撤销结果")
    @ExcelProperty(value = "撤销结果", converter = DictConvert.class)
    @DictFormat("sys_yhbfjg")
    private String cxjg;

    @Schema(description = "撤销笔数")
    @ExcelProperty("撤销笔数")
    private Long cxbs;

    @Schema(description = "撤销金额")
    @ExcelProperty("撤销金额")
    private BigDecimal cxje;

}
