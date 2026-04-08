package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 退回凭证重发分页 Request VO")
@Data
public class ThpzcfPageReqVO extends PageParam {

    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "账号")
    private String zh;
    @Schema(description = "户名")
    private String hm;
    @Schema(description = "新账号")
    private String xzh;
    @Schema(description = "新户名")
    private String xhm;
    @Schema(description = "新行号")
    private String xhh;
    @Schema(description = "行号")
    private String hh;
    @Schema(description = "金额")
    private BigDecimal je;
    @Schema(description = "地址")
    private String dz;
    @Schema(description = "附言")
    private String fy;
    @Schema(description = "退回原因")
    private String thyy;
    @Schema(description = "修改标志")
    private String xgbj;
    @Schema(description = "生成划款批次号")
    private String schkpch;
    @Schema(description = "系统生成标志")
    private String scbz;
    @Schema(description = "工会机构")
    private String deptId;
    @Schema(description = "退回日期-开始")
    private String beginThrq;
    @Schema(description = "退回日期-结束")
    private String endThrq;
    @Schema(description = "修改时间-开始")
    private String beginUpdateTime;
    @Schema(description = "修改时间-结束")
    private String endUpdateTime;
}
