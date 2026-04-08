package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 工会经费做账新增/修改 Request VO")
@Data
public class GhjfzzSaveReqVO {

    @Schema(description = "划款信息ID")
    private Long hkxxId;

    private String hkpch;
    private Long xh;
    private String lx;
    private String zh;
    private String zh1;
    private String zh2;
    private String zh3;
    private String yhzh;
    private String hm;
    private String hh;
    private BigDecimal je;
    private String deptId;
    private String dz;
    private String fy;
    private String jym;
    private String thbj;
    private String thrq;
    private String thyy;
    private String schkpch;
    private LocalDateTime qrrq;
    private String yhhdh;
    private String dzbj;
    private String bz;

    /** 经费确认收账子表 (v1 cascade) */
    private List<GhjfQrszItem> ghjfQrszList;

    @Data
    public static class GhjfQrszItem {
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
