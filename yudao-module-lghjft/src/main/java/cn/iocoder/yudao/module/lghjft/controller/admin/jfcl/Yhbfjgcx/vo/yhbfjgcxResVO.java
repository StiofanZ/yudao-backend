package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 银行拨付结果查询 Response VO")
@Data
@ExcelIgnoreUnannotated
public class yhbfjgcxResVO {

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
    @ExcelProperty("拨付状态")
    private String bfzt;

    @Schema(description = "拨付总笔数")
    @ExcelProperty("拨付总笔数")
    private Integer bfzbs;

    @Schema(description = "拨付总金额")
    @ExcelProperty("拨付总金额")
    private BigDecimal bfzje;

    @Schema(description = "成功结果")
    @ExcelProperty("成功结果")
    private String cgjg;

    @Schema(description = "成功笔数")
    @ExcelProperty("成功笔数")
    private Integer cgbs;

    @Schema(description = "成功金额")
    @ExcelProperty("成功金额")
    private BigDecimal cgje;

    @Schema(description = "失败结果")
    @ExcelProperty("失败结果")
    private String sbjg;

    @Schema(description = "失败笔数")
    @ExcelProperty("失败笔数")
    private Integer sbbs;

    @Schema(description = "失败金额")
    @ExcelProperty("失败金额")
    private BigDecimal sbje;

    @Schema(description = "退票结果")
    @ExcelProperty("退票结果")
    private String tpjg;

    @Schema(description = "退票笔数")
    @ExcelProperty("退票笔数")
    private Integer tpbs;

    @Schema(description = "退票金额")
    @ExcelProperty("退票金额")
    private BigDecimal tpje;

    @Schema(description = "否决结果")
    @ExcelProperty("否决结果")
    private String fjjg;

    @Schema(description = "否决笔数")
    @ExcelProperty("否决笔数")
    private Integer fjbs;

    @Schema(description = "否决金额")
    @ExcelProperty("否决金额")
    private BigDecimal fjje;

    @Schema(description = "过期结果")
    @ExcelProperty("过期结果")
    private String gqjg;

    @Schema(description = "过期笔数")
    @ExcelProperty("过期笔数")
    private Integer gqbs;

    @Schema(description = "过期金额")
    @ExcelProperty("过期金额")
    private BigDecimal gqje;

    @Schema(description = "撤销结果")
    @ExcelProperty("撤销结果")
    private String cxjg;

    @Schema(description = "撤销笔数")
    @ExcelProperty("撤销笔数")
    private Integer cxbs;

    @Schema(description = "撤销金额")
    @ExcelProperty("撤销金额")
    private BigDecimal cxje;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String createBy;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updateBy;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

}