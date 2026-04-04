package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 单户经费台账 Response VO — 映射 v1 gh_hj 表字段
 */
@Schema(description = "管理后台 - 到户经费台账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DhjftzResVO {

    @ExcelProperty("登记序号")
    @Schema(description = "登记序号")
    private String djxh;

    @ExcelProperty("工会机构代码")
    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @ExcelProperty("社会信用代码")
    @Schema(description = "社会信用代码")
    private String shxydm;

    @ExcelProperty("纳税人名称")
    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;
    @Schema(description = "主管税务局名称")
    private String zgswjmc;

    @Schema(description = "主管税务所科分局代码")
    private String zgswskfjDm;
    @Schema(description = "主管税务所科分局名称")
    private String zgswskfjmc;

    @Schema(description = "所属管理员代码")
    private String ssglyDm;
    @Schema(description = "所属管理员姓名")
    private String ssglyxm;

    @Schema(description = "组织机构类型代码")
    private String zzjglxDm;
    @Schema(description = "组织机构类型名称")
    private String zzjglxmc;

    @Schema(description = "行业代码")
    private String hyDm;
    @Schema(description = "行业名称")
    private String hymc;

    @Schema(description = "登记注册类型代码")
    private String djzclxDm;
    @Schema(description = "登记注册类型名称")
    private String djzclxmc;

    @Schema(description = "单位隶属关系代码")
    private String dwlsgxDm;
    @Schema(description = "单位隶属关系名称")
    private String dwlsgxmc;

    @ExcelProperty("职工人数")
    @Schema(description = "职工人数")
    private BigDecimal zgrs;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;
    @Schema(description = "纳税人状态名称")
    private String nsrztmc;

    @Schema(description = "发证日期")
    private LocalDateTime fzcrq;
    @Schema(description = "注销日期")
    private LocalDateTime zxrq;

    @Schema(description = "注册地址")
    private String zcdz;
    @Schema(description = "邮政编码")
    private String yzbm;
    @Schema(description = "联系人")
    private String lxr;
    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "工会类别代码")
    private String ghlbDm;
    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "上代工会机构代码")
    private String sdghjgDm;

    @Schema(description = "成立工会标记")
    private String clghbj;
    @Schema(description = "成立工会日期")
    private LocalDateTime clghrq;

    @Schema(description = "基层工会代码")
    private String jcghdm;
    @Schema(description = "基层工会名称")
    private String jcghmc;
    @Schema(description = "基层工会账户")
    private String jcghzh;
    @Schema(description = "基层工会户名")
    private String jcghhm;
    @Schema(description = "基层工会行号")
    private String jcghhh;
    @Schema(description = "基层工会银行")
    private String jcghyh;

    @Schema(description = "备注")
    private String bz;
    @Schema(description = "校验码")
    private String jym;
    @Schema(description = "纳税人识别号")
    private String nsrsbh;
}
