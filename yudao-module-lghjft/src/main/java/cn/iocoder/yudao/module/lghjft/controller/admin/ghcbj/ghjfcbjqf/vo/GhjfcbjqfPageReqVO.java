package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 筹备金全返分页 Request VO")
@Data
public class GhjfcbjqfPageReqVO extends PageParam {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "缴费账号")
    private String jfzh;

    @Schema(description = "缴费户名")
    private String jfhm;

    @Schema(description = "入库日期")
    private String rkrq;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "结算日期开始")
    private String beginJsrq;

    @Schema(description = "结算日期结束")
    private String endJsrq;

    @Schema(description = "筹备金全返状态")
    private String cbjqfzt;

    @Schema(description = "筹备金全返日期开始")
    private String beginCbjqfrq;

    @Schema(description = "筹备金全返日期结束")
    private String endCbjqfrq;

    @Schema(description = "筹备金结算拨付基层状态")
    private String cbjjsbfjczt;

    @Schema(description = "筹备金结算拨付基层日期开始")
    private String beginCbjjsbfjcrq;

    @Schema(description = "筹备金结算拨付基层日期结束")
    private String endCbjjsbfjcrq;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "全返批次号")
    private String qfpch;
}
