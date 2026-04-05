package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 筹备金汇总统计新增/修改 Request VO")
@Data
public class CbjhztjSaveReqVO {

    private Long ghjfId;
    private String spuuid;
    private String zspmDm;
    private String nd;
    private String deptId;
    private String shxydm;
    private String djxh;
    private String nsrmc;
    private LocalDateTime rkrq;
    private LocalDateTime jsrq;
    private LocalDateTime skssqq;
    private LocalDateTime skssqz;
    private String jsbj;
    private String cbjthbj;
    private BigDecimal qtje;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private LocalDateTime zjxcrq;
    private String fbbj;
    private LocalDateTime fbrq;
    private BigDecimal sfbje;
}
