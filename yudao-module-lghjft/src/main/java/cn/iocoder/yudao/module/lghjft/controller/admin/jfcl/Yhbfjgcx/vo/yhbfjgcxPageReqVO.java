package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 银行拨付结果查询分页 Request VO")
@Data
public class YhbfjgcxPageReqVO extends PageParam {

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
    private Integer bfzbs;

    @Schema(description = "拨付总金额")
    private BigDecimal bfzje;

    @Schema(description = "成功结果")
    private String cgjg;

    @Schema(description = "成功笔数")
    private Integer cgbs;

    @Schema(description = "成功金额")
    private BigDecimal cgje;

    @Schema(description = "失败结果")
    private String sbjg;

    @Schema(description = "失败笔数")
    private Integer sbbs;

    @Schema(description = "失败金额")
    private BigDecimal sbje;

    @Schema(description = "退票结果")
    private String tpjg;

    @Schema(description = "退票笔数")
    private Integer tpbs;

    @Schema(description = "退票金额")
    private BigDecimal tpje;

    @Schema(description = "否决结果")
    private String fjjg;

    @Schema(description = "否决笔数")
    private Integer fjbs;

    @Schema(description = "否决金额")
    private BigDecimal fjje;

    @Schema(description = "过期结果")
    private String gqjg;

    @Schema(description = "过期笔数")
    private Integer gqbs;

    @Schema(description = "过期金额")
    private BigDecimal gqje;

    @Schema(description = "撤销结果")
    private String cxjg;

    @Schema(description = "撤销笔数")
    private Integer cxbs;

    @Schema(description = "撤销金额")
    private BigDecimal cxje;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
