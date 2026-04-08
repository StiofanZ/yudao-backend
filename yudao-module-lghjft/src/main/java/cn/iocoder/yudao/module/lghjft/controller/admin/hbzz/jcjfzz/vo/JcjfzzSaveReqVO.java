package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 基层经费到账新增/修改 Request VO")
@Data
public class JcjfzzSaveReqVO {

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
    private String schkpch;
    private LocalDateTime qrrq;
    private String yhhdh;
    private String dzbj;
    private String bz;
    private String xgr;
    private LocalDateTime xgsj;
    private String scbz;

    /** 基层经费到账子表 (v1 cascade jcjfdzList) */
    private List<JcjfdzItem> jcjfdzList;

    @Data
    public static class JcjfdzItem {
        private Long hkxxId;
        private String ghHkxxJym;
        private String dzbj;
        private String qrrq;
        private String yhhdh;
        private String bz;
        private String createBy;
        private String updateBy;
    }
}
