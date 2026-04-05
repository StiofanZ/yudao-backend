package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 经费明细(查询统计)分页 Request VO")
@Data
public class JfmxPageReqVO extends PageParam {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "工会机构行政级别")
    private String ghjgxzjb;

    @Schema(description = "成立工会标记")
    private String clghbj;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "上代工会机构行政级别")
    private String sdghjgxzjb;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "主管税务所科分局代码")
    private String zgswskfjDm;

    @Schema(description = "所属管理员代码")
    private String ssglyDm;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "组织机构类型代码")
    private String zzjglxDm;

    @Schema(description = "行业代码")
    private String hyDm;

    @Schema(description = "登记注册类型代码")
    private String djzclxDm;

    @Schema(description = "单位隶属关系代码")
    private String dwlsgxDm;

    @Schema(description = "申报类别代码")
    private String sblbDm;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "入库日期(年份)")
    private String rkrq;

    @Schema(description = "入库日期-开始")
    private String beginRkrq;

    @Schema(description = "入库日期-结束")
    private String endRkrq;

    @Schema(description = "结算日期-开始")
    private String beginJsrq;

    @Schema(description = "结算日期-结束")
    private String endJsrq;

    @Schema(description = "小微类型")
    private String xwlx;

    @Schema(description = "小额类型23")
    private String xelx23;

    @Schema(description = "小额类型24")
    private String xelx24;

    @Schema(description = "小额类型25")
    private String xelx25;
}
