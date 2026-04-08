package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 基层经费到账分页 Request VO")
@Data
public class JcjfzzPageReqVO extends PageParam {

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "序号")
    private Integer xh;

    @Schema(description = "类型")
    private String lx;

    @Schema(description = "账号")
    private String zh;

    @Schema(description = "户名")
    private String hm;

    @Schema(description = "行号")
    private String hh;

    @Schema(description = "金额")
    private BigDecimal je;

    @Schema(description = "工会机构")
    private String deptId;

    @Schema(description = "地址")
    private String dz;

    @Schema(description = "附言")
    private String fy;

    @Schema(description = "校验码")
    private String jym;

    @Schema(description = "退回标志")
    private String thbj;

    @Schema(description = "退回原因")
    private String thyy;

    @Schema(description = "生成划款批次号")
    private String schkpch;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "系统生成标志")
    private String scbz;

    @Schema(description = "到账标记")
    private String dzbj;

    @Schema(description = "做账凭证号")
    private String yhhdh;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "拨付文件开始日期")
    private String beginBfwjrq;

    @Schema(description = "拨付文件结束日期")
    private String endBfwjrq;

    @Schema(description = "到账开始日期")
    private String beginQrrq;

    @Schema(description = "到账结束日期")
    private String endQrrq;

    @Schema(description = "退回日期-开始")
    private String beginThrq;

    @Schema(description = "退回日期-结束")
    private String endThrq;

    @Schema(description = "创建时间-开始")
    private String beginCreateTime;

    @Schema(description = "创建时间-结束")
    private String endCreateTime;

    @Schema(description = "更新时间-开始")
    private String beginUpdateTime;

    @Schema(description = "更新时间-结束")
    private String endUpdateTime;
}
