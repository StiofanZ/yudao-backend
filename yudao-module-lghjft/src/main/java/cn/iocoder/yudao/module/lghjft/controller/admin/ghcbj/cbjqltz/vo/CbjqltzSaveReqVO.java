package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 筹备金清理台账新增/修改 Request VO")
@Data
public class CbjqltzSaveReqVO {

    private String deptId;
    private String zgswjDm;
    private String zgswskfjDm;
    private String jdxzDm;
    private String shxydm;
    private String nsrmc;
    private String djxh;
    private String nsrztDm;
    private String nsrztmc;
    private LocalDateTime sjtbSj;
    private String jsbj;
    private String cbjthbj;
    private String fbbj;
    private Long jfbs;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private Long fbbs;
    private BigDecimal sfbje;
    private LocalDateTime rkrq;
    private Long yf;
    private LocalDateTime jfrkrq;
    private Long jfyf;
    private String subNsrztDm;
    private String subJfztDm;
    private String subGzqrztDm;
    private String subUpdateBy;
    private LocalDateTime subUpdateTime;
}
