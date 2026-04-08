package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 手续费做账分页 Request VO")
@Data
public class SxfzzPageReqVO extends PageParam {

    private String hkpch;
    private String lx;
    private String deptId;
    private String dzbj;
    private Long xh;
    private String zh;
    private String hm;
    private String hh;
    private BigDecimal je;
    private String dz;
    private String fy;
    private String jym;
    private String thbj;
    private String thyy;
    private String schkpch;
    private String bz;
    private String scbz;
    private String createBy;
    private String updateBy;
    // 拨付文件日期范围
    private String beginBfwjrq;
    private String endBfwjrq;
    // 到账日期范围
    private String beginQrrq;
    private String endQrrq;
    // 退回日期范围
    private String beginThrq;
    private String endThrq;
    // 创建时间范围
    private String beginCreateTime;
    private String endCreateTime;
    // 修改时间范围
    private String beginUpdateTime;
    private String endUpdateTime;
}
