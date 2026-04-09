package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额缴费明细分页 Request VO")
@Data
public class Xejf2023PageReqVO extends PageParam {
    @Schema(description = "工会经费ID")
    private Long ghjfId;
    @Schema(description = "缴费期间")
    private String jfqj;
    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "工会机构代码")
    private String deptId;
    @Schema(description = "征收品目代码")
    private String zspmDm;
    @Schema(description = "结算标记")
    private String[] jsbj;
    @Schema(description = "23小额类型")
    private String[] xelx23;
    @Schema(description = "24小额类型")
    private String[] xelx24;
    @Schema(description = "25小额类型")
    private String[] xelx25;
    @Schema(description = "工会类别代码")
    private String ghlbDm;
    @Schema(description = "系统类别代码")
    private String xtlbDm;
    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "入库日期-开始")
    private String beginRkrq;
    @Schema(description = "入库日期-结束")
    private String endRkrq;
    @Schema(description = "结算日期-开始")
    private String beginJsrq;
    @Schema(description = "结算日期-结束")
    private String endJsrq;
    @Schema(description = "基层工会账户")
    private String jcghzh;
    @Schema(description = "基层工会行号")
    private String jcghhh;
    @Schema(description = "基层经费拨付状态")
    private String cbjthbj;
    @Schema(description = "基层经费拨付处理日期-开始")
    private String beginCbjthrq;
    @Schema(description = "基层经费拨付处理日期-结束")
    private String endCbjthrq;
}
