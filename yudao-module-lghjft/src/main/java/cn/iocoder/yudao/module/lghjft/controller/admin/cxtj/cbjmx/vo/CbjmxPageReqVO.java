package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 筹备金统计分页 Request VO")
@Data
public class CbjmxPageReqVO extends PageParam {

    @Schema(description = "征收品目代码")
    private String zspmDm;
    @Schema(description = "年度")
    private String nd;
    @Schema(description = "工会机构代码")
    private String deptId;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "入库日期开始")
    private String beginRkrq;
    @Schema(description = "入库日期结束")
    private String endRkrq;
    @Schema(description = "结算日期开始")
    private String beginJsrq;
    @Schema(description = "结算日期结束")
    private String endJsrq;
    @Schema(description = "所属期起")
    private String skssqq;
    @Schema(description = "所属期止")
    private String skssqz;
    @Schema(description = "结算标记")
    private String jsbj;
    @Schema(description = "筹备金退回标记")
    private String cbjthbj;
    @Schema(description = "缴费金额")
    private String jfje;
    @Schema(description = "筹备金金额")
    private String cbjje;
    @Schema(description = "返拨标记")
    private String fbbj;
    @Schema(description = "返拨日期开始")
    private String beginFbrq;
    @Schema(description = "返拨日期结束")
    private String endFbrq;
    @Schema(description = "实返拨金额")
    private String sfbje;
}
