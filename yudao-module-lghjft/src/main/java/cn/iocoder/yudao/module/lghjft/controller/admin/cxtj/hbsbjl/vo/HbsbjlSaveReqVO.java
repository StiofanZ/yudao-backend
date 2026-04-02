package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 划拨失败记录新增/修改 Request VO")
@Data
public class HbsbjlSaveReqVO {

    private Long hkxxId;
    private String hkpch;
    private Long xh;
    private String lx;
    private String zh;
    private String hm;
    private String hh;
    private String xzh;
    private String xhm;
    private String xhh;
    private BigDecimal je;
    private String deptId;
    private String dz;
    private String fy;
    private String jym;
    private String thbj;
    private LocalDateTime thrq;
    private String thyy;
    private String xgbj;
    private String hkxxidgl;
    private String schkpch;
    private String bz;
    private String scbz;
    private String xgr;
    private LocalDateTime xgsj;
}
