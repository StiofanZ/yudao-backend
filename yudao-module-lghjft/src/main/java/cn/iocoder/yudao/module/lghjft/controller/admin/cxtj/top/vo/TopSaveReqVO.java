package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 缴费排行新增/修改 Request VO")
@Data
public class TopSaveReqVO {

    private String djxh;
    private String shxydm;
    private String nsrmc;
    private String deptId;
    private String dwmc;
    private Long bsdn;
    private Long bswn;
    private Long bscy;
    private BigDecimal jfjedn;
    private BigDecimal jfjewn;
    private BigDecimal jfjecy;
    private String jfbl;
    private BigDecimal sjjedn;
    private BigDecimal sjjewn;
    private BigDecimal sjjecy;
    private String sjbl;
}
