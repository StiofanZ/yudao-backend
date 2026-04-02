package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 首页汇总信息新增/修改 Request VO")
@Data
public class SyhzxxSaveReqVO {

    private String deptId;
    private Long bzhs;
    private BigDecimal bzje;
    private Long byhs;
    private BigDecimal byje;
    private Long bnhs;
    private BigDecimal bnje;
    private Long rkwwhhs;
    private BigDecimal rkwwhje;
    private Long jczhkhs;
    private BigDecimal jczhkje;
    private Long bfsbbs;
    private BigDecimal bfsbje;
}
