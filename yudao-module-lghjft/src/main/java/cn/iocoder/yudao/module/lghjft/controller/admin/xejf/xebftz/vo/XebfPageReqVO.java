package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额缴费拨付台账分页 Request VO")
@Data
public class XebfPageReqVO extends PageParam {
    @Schema(description = "工会经费ID")
    private Long ghjfId;
    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "缴费期间")
    private String jfqj;
    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "工会机构")
    private String deptId;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "返拨标记")
    private String fbbj;
    @Schema(description = "征收品目代码")
    private String zspmDm;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "小微类型")
    private String xwlx;
    @Schema(description = "基层工会账号")
    private String jcghzh;
    @Schema(description = "行业工会账号")
    private String hyghzh;
    @Schema(description = "县级工会账号")
    private String xjghzh;
    @Schema(description = "市级工会账号")
    private String sjghzh;
    @Schema(description = "属地工会账号")
    private String sdghzh;

    // v1: jsbj IN array
    @Schema(description = "结算标记")
    private String[] jsbj;
    // v1: xelx23/24/25 IN array
    @Schema(description = "小额类型23")
    private String[] xelx23;
    @Schema(description = "小额类型24")
    private String[] xelx24;
    @Schema(description = "小额类型25")
    private String[] xelx25;

    // v1 date ranges
    @Schema(description = "入库日期起始")
    private String beginRkrq;
    @Schema(description = "入库日期截止")
    private String endRkrq;
    @Schema(description = "结算日期起始")
    private String beginJsrq;
    @Schema(description = "结算日期截止")
    private String endJsrq;
    @Schema(description = "返拨日期起始")
    private String beginFbrq;
    @Schema(description = "返拨日期截止")
    private String endFbrq;
}
