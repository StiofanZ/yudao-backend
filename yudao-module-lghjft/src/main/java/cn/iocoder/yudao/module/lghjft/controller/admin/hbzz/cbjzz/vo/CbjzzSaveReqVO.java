package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 筹备金做账新增/修改 Request VO")
@Data
public class CbjzzSaveReqVO {

    private Long hkxxId;
    private String hkpch;
    private Long xh;
    private String lx;
    private String zh;
    private String hm;
    private String hh;
    private String sjdm;
    private String deptId;
    private BigDecimal je;
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

    /** 筹备金确认收账子表 (v1 cascade) */
    private List<CbjQrszItem> cbjQrszList;

    @Data
    public static class CbjQrszItem {
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
