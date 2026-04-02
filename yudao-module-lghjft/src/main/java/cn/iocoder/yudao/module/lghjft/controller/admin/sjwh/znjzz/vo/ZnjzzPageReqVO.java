package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.znjzz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 滞纳金做账分页 Request VO")
@Data
public class ZnjzzPageReqVO extends PageParam {
    private String hkpch;
    private String lx;
    private String deptId;
    private String dzbj;
    private String hm;
    private BigDecimal je;
    private String dz;
    private String fy;
    private String bz;
    private String yhhdh;
    private String zh;
    private Integer xh;
    private String hh;
    private String jym;
    private String thbj;
    private String thyy;
    private String schkpch;
    private String createBy;
    private String updateBy;
    private String beginBfwjrq;
    private String endBfwjrq;
    private String beginQrrq;
    private String endQrrq;
    private String beginThrq;
    private String endThrq;
}
