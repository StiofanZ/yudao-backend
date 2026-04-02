package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 近三年缴费情况新增/修改 Request VO")
@Data
public class GhjfjfdwSaveReqVO {

    private String djxh;
    private String shxydm;
    private String nsrmc;
    private String deptId;
    private String zsswjgDm;
    private String djxhbn;
    private Long bsbn;
    private Long ysbn;
    private BigDecimal jfjebn;
    private String djxhsn;
    private Long bssn;
    private Long yssn;
    private BigDecimal jfjesn;
    private String djxhqn;
    private Long bsqn;
    private Long ysqn;
    private BigDecimal jfjeqn;
}
