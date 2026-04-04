package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额台账分页 Request VO")
@Data
public class XetzPageReqVO extends PageParam {
    @Schema(description = "缴费期间")
    private String jfqj;
    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "工会机构")
    private String deptId;

    // v1 额外筛选条件
    @Schema(description = "小微类型")
    private String xwlx;
    @Schema(description = "工会类别代码")
    private String ghlbDm;
    @Schema(description = "系统类别代码")
    private String xtlbDm;
    @Schema(description = "���额类型23")
    private String[] xelx23;
    @Schema(description = "���额类型24")
    private String[] xelx24;
    @Schema(description = "小额类型25")
    private String[] xelx25;
    @Schema(description = "应补退税额起始")
    private String beginYbtse;
    @Schema(description = "应补退税额截止")
    private String endYbtse;
    @Schema(description = "实际金额起��")
    private String beginSjje;
    @Schema(description = "实际金额截止")
    private String endSjje;
}
