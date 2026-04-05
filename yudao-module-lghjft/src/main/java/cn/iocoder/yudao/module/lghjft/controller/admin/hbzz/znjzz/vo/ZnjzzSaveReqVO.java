package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 滞纳金做账新增/修改 Request VO")
@Data
public class ZnjzzSaveReqVO {

    private Long hkxxId;
    private String hkpch;
    private Long xh;
    private String lx;
    private String zh;
    private String hm;
    private String hh;
    private BigDecimal je;
    private String deptId;
    private String dz;
    private String fy;
    private String jym;
    private String thbj;
    private LocalDateTime thrq;
    private String thyy;
    private String schkpch;
    private LocalDateTime qrrq;
    private String yhhdh;
    private String dzbj;
    private String bz;
    private String scbz;
}
