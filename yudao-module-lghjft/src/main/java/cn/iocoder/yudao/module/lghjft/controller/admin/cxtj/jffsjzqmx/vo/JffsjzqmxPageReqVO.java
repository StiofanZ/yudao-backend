package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分年各级分成情况分页 Request VO")
@Data
public class JffsjzqmxPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrsbh")
    private String nsrsbh;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "nsrjc")
    private String nsrjc;
    @Schema(description = "skssqq")
    private String skssqq;
    @Schema(description = "skssqz")
    private String skssqz;
    @Schema(description = "zgswjDm")
    private String zgswjDm;
    @Schema(description = "beginRkrq")
    private String beginRkrq;
    @Schema(description = "endRkrq")
    private String endRkrq;

    @Schema(description = "征收品目代码")
    private String zspmDm;
    @Schema(description = "工会类别代码")
    private String ghlbDm;
    @Schema(description = "系统类别代码")
    private String xtlbDm;
    @Schema(description = "结算标记")
    private String jsbj;
    @Schema(description = "基层经费拨付状态")
    private String cbjthbj;
    @Schema(description = "小微类型")
    private String xwlx;
    @Schema(description = "结算日期起")
    private String beginJsrq;
    @Schema(description = "结算日期止")
    private String endJsrq;
    @Schema(description = "工会机构行政级别")
    private String ghjgxzjb;
    @Schema(description = "成立工会标记")
    private String clghbj;
    @Schema(description = "行业工会标志")
    private String hyghbz;
    @Schema(description = "属地工会行政级别")
    private String sdghjgxzjb;
    @Schema(description = "主管税务分局代码")
    private String zgswskfjDm;
    @Schema(description = "税管员代码")
    private String ssglyDm;
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
}
