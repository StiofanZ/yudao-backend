package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分月代收情况(入库)分页 Request VO")
@Data
public class SzjffydsqkPageReqVO extends PageParam {

    @Schema(description = "dwdm")
    private String dwdm;
    @Schema(description = "dwmc")
    private String dwmc;
    @Schema(description = "代收月份-开始")
    private String beginDsyf;
    @Schema(description = "代收月份-结束")
    private String endDsyf;
    @Schema(description = "rkjf")
    private BigDecimal rkjf;
    @Schema(description = "ghjf")
    private BigDecimal ghjf;
    @Schema(description = "cbj")
    private BigDecimal cbj;
    @Schema(description = "znj")
    private BigDecimal znj;
}
