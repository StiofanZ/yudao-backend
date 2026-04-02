package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分月代收情况(入库)新增/修改 Request VO")
@Data
public class SzjffydsqkSaveReqVO {

    private String dwdm;
    private String dwmc;
    private String dsyf;
    private BigDecimal rkjf;
    private BigDecimal ghjf;
    private BigDecimal cbj;
    private BigDecimal znj;
}
