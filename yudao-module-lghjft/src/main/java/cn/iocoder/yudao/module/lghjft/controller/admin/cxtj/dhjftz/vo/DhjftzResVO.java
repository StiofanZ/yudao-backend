package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 到户经费台账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DhjftzResVO {

    @Schema(description = "工会经费ID")
    private Long ghjfId;

    @Schema(description = "审批UUID")
    private String spuuid;

    @ExcelProperty("登记序号")
    @Schema(description = "登记序号")
    private String djxh;

    @ExcelProperty("社会信用代码")
    @Schema(description = "社会信用代码")
    private String shxydm;

    @ExcelProperty("纳税人名称")
    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @ExcelProperty("工会机构代码")
    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "工会机构行政级别")
    private String ghjgxzjb;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "征收子目代码")
    private String zszmDm;

    @Schema(description = "税款所属期起")
    private LocalDateTime skssqq;

    @Schema(description = "税款所属期止")
    private LocalDateTime skssqz;

    @ExcelProperty("应补退税额")
    @Schema(description = "应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "入库日期")
    private LocalDateTime rkrq;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "结算日期")
    private LocalDateTime jsrq;

    @Schema(description = "结算操作员")
    private String jsczy;

    @Schema(description = "基层工会账户")
    private String jcghzh;
    @Schema(description = "基层工会户名")
    private String jcghhm;
    @Schema(description = "基层工会行号")
    private String jcghhh;
    @Schema(description = "基层工会比例")
    private BigDecimal jcghbl;
    @ExcelProperty("基层工会金额")
    @Schema(description = "基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "筹备金账户")
    private String cbjzh;
    @Schema(description = "筹备金户名")
    private String cbjhm;
    @Schema(description = "筹备金行号")
    private String cbjhh;
    @Schema(description = "筹备金比例")
    private BigDecimal cbjbl;
    @ExcelProperty("筹备金金额")
    @Schema(description = "筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "筹备金退回标记")
    private String cbjthbj;

    @Schema(description = "校验码")
    private String jym;

    @Schema(description = "汇款批次号")
    private String hkpch;
}
