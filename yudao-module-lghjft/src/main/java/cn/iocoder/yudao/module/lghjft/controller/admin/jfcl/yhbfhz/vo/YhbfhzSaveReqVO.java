package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 银行拨付汇总新增/修改 Request VO")
@Data
public class YhbfhzSaveReqVO {

    @Schema(description = "拨付汇总ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31361")
    private Integer bfhzid;

    @Schema(description = "拨付汇总批次号")
    private String bfhzpch;

    @Schema(description = "业务参考号起", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "业务参考号起不能为空")
    private Long bfidq;

    @Schema(description = "业务参考号止", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "业务参考号止不能为空")
    private Long bfidz;

    @Schema(description = "拨付包名称")
    private String bfbmc;

    @Schema(description = "拨付笔数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "拨付笔数不能为空")
    private Long bs;

    @Schema(description = "拨付合计金额")
    private BigDecimal hzje;

    @Schema(description = "退回笔数")
    private String thbs;

    @Schema(description = "退回金额")
    private BigDecimal thje;

    @Schema(description = "失败笔数")
    private String sbbs;

    @Schema(description = "失败金额")
    private BigDecimal sbje;

    @Schema(description = "成功笔数")
    private String cgbs;

    @Schema(description = "成功金额")
    private BigDecimal cgje;

    @Schema(description = "唯一识别号", example = "4194")
    private String uuid;

    @Schema(description = "拨付状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "拨付状态不能为空")
    private String bfzt;

    @Schema(description = "处理结果")
    private String bfjg;

    @Schema(description = "退回日期")
    private String thrq;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createBy;

    @Schema(description = "修改人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "修改人不能为空")
    private String updateBy;

    @Schema(description = "全部业务参考号")
    private String bfidStr;

}