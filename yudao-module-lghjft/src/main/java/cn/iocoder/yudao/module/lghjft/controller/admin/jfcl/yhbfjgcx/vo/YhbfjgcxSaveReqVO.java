package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 银行拨付结果查询新增/修改 Request VO")
@Data
public class YhbfjgcxSaveReqVO {

    @Schema(description = "拨付批次号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bfpch;

    @Schema(description = "拨付日期")
    private String bfrq;

    @Schema(description = "拨付汇总ID", example = "7799")
    private String bfhzid;

    @Schema(description = "拨付包名称")
    private String bfbmc;

    @Schema(description = "拨付汇总批次号")
    private String bfhzpch;

    @Schema(description = "拨付状态")
    private String bfzt;

    @Schema(description = "拨付总笔数")
    private Long bfzbs;

    @Schema(description = "拨付总金额")
    private BigDecimal bfzje;

    @Schema(description = "成功结果")
    private String cgjg;

    @Schema(description = "成功笔数")
    private Long cgbs;

    @Schema(description = "成功金额")
    private BigDecimal cgje;

    @Schema(description = "失败结果")
    private String sbjg;

    @Schema(description = "失败笔数")
    private Long sbbs;

    @Schema(description = "失败金额")
    private BigDecimal sbje;

    @Schema(description = "退票结果")
    private String tpjg;

    @Schema(description = "退票笔数")
    private Long tpbs;

    @Schema(description = "退票金额")
    private BigDecimal tpje;

    @Schema(description = "否决结果")
    private String fjjg;

    @Schema(description = "否决笔数")
    private Long fjbs;

    @Schema(description = "否决金额")
    private BigDecimal fjje;

    @Schema(description = "过期结果")
    private String gqjg;

    @Schema(description = "过期笔数")
    private Long gqbs;

    @Schema(description = "过期金额")
    private BigDecimal gqje;

    @Schema(description = "撤销结果")
    private String cxjg;

    @Schema(description = "撤销笔数")
    private Long cxbs;

    @Schema(description = "撤销金额")
    private BigDecimal cxje;

}
