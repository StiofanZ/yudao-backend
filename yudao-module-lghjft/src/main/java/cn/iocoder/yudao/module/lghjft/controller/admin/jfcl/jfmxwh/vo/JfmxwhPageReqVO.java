package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 经费明细维护分页 Request VO")
@Data
public class JfmxwhPageReqVO extends PageParam {

    @Schema(description = "税票ID")
    private String spuuid;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "所属期起")
    private String skssqq;

    @Schema(description = "所属期止")
    private String skssqz;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "缴费账号")
    private String jfzh;

    @Schema(description = "缴费户名")
    private String jfhm;

    @Schema(description = "入库日期起")
    private String rkrqStart;

    @Schema(description = "入库日期止")
    private String rkrqEnd;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "经费拨付状态")
    private String cbjthbj;

    @Schema(description = "结算日期起")
    private String jsrqStart;

    @Schema(description = "结算日期止")
    private String jsrqEnd;

    @Schema(description = "结算操作员")
    private String jsczy;

    @Schema(description = "基层工会账户")
    private String jcghzh;

    @Schema(description = "基层工会行号")
    private String jcghhh;

    @Schema(description = "基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "分配比例UUID")
    private String bluuid;
}
