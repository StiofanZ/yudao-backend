package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小微经费统计查询 Request VO")
@Data
public class XwqyjftjPageReqVO extends PageParam {

    @Schema(description = "单位代码")
    private String dwdm;
    @Schema(description = "年度")
    private String nd;
    @Schema(description = "部门ID")
    private String deptId;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "小微上报单位")
    private String xwsbdwdm;
    @Schema(description = "小微类型")
    private String xwlx;
    @Schema(description = "行业工会标志")
    private String hyghbz;
    @Schema(description = "工会类别")
    private String ghlbDm;
    @Schema(description = "系统类别")
    private String xtlbDm;
    @Schema(description = "征收品目")
    private String zspmDm;
    @Schema(description = "结算标记")
    private String jsbj;
    @Schema(description = "筹备金退还标记")
    private String cbjthbj;
    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "所属期起")
    private String skssqq;
    @Schema(description = "所属期止")
    private String skssqz;

    // date ranges
    @Schema(description = "小微上报日期起")
    private String beginXwsbrq;
    @Schema(description = "小微上报日期止")
    private String endXwsbrq;
    @Schema(description = "入库日期起")
    private String beginRkrq;
    @Schema(description = "入库日期止")
    private String endRkrq;
    @Schema(description = "结算日期起")
    private String beginJsrq;
    @Schema(description = "结算日期止")
    private String endJsrq;
    @Schema(description = "筹备金退还日期起")
    private String beginCbjthrq;
    @Schema(description = "筹备金退还日期止")
    private String endCbjthrq;

    @Schema(description = "分页偏移量", hidden = true)
    private Integer offset;
}
