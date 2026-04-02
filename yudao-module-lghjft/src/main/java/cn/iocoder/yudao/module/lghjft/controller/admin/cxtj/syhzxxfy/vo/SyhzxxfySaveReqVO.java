package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 首页汇总信息分月新增/修改 Request VO")
@Data
public class SyhzxxfySaveReqVO {

    private String nd;
    private String deptId;
    private BigDecimal month1;
    private BigDecimal month2;
    private BigDecimal month3;
    private BigDecimal month4;
    private BigDecimal month5;
    private BigDecimal month6;
    private BigDecimal month7;
    private BigDecimal month8;
    private BigDecimal month9;
    private BigDecimal month10;
    private BigDecimal month11;
    private BigDecimal month12;
    private BigDecimal total;
    private BigDecimal totalsjszje;
    private BigDecimal totalbjfcje;
}
