package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 小额缴费拨付台账 Response VO")
@Data
public class XebfResVO {
    private Long ghjfId;
    @ExcelProperty("实返拨金额")
    private BigDecimal sfbje;
    @ExcelProperty("返拨标记")
    private String fbbj;
    @ExcelProperty("返拨日期")
    private LocalDateTime fbrq;
    @ExcelProperty("资金下拨日期")
    private LocalDateTime zjxcrq;
    @ExcelProperty("划款批次号")
    private String hkpch;
    @ExcelProperty("汇款凭证号")
    private String hkpzh;
    @ExcelProperty("备注")
    private String bz;
    private String spuuid;
    @ExcelProperty("入库日期")
    private LocalDateTime rkrq;
    @ExcelProperty("结算日期")
    private LocalDateTime jsrq;
    @ExcelProperty(value = "缴费期间", converter = DictConvert.class)
    @DictFormat("sys_xejfqj")
    private String jfqj;
    @ExcelProperty("所属期起")
    private LocalDateTime skssqq;
    @ExcelProperty("所属期止")
    private LocalDateTime skssqz;
    @ExcelProperty(value = "征收品目", converter = DictConvert.class)
    @DictFormat("sys_zspm_type")
    private String zspmDm;
    @ExcelProperty("工资总额")
    private BigDecimal gzze;
    private BigDecimal jmse;
    @ExcelProperty("应返拨金额")
    private BigDecimal yfbje;
    @ExcelProperty("缴费金额")
    private BigDecimal ybtse;
    private String[] jsbj;
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;
    @ExcelProperty("登记序号")
    private String djxh;
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @ExcelProperty("纳税人名称")
    private String nsrmc;
    private String[] xelx25;
    private String[] xelx24;
    private String[] xelx23;
    @ExcelProperty(value = "小微类型", converter = DictConvert.class)
    @DictFormat("sys_xwlx")
    private String xwlx;
    @ExcelProperty("登记注册类型名称")
    private String djzclxmc;
    @ExcelProperty("基层工会比例")
    private BigDecimal jcghbl;
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;
    @ExcelProperty("基层工会行号")
    private String jcghhh;
    @ExcelProperty("基层工会账户")
    private String jcghzh;
    @ExcelProperty("基层工会户名")
    private String jcghhm;
    @ExcelProperty("筹备金比例")
    private BigDecimal cbjbl;
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;
    @ExcelProperty("筹备金账号")
    private String cbjzh;
    @ExcelProperty("筹备金户名")
    private String cbjhm;
    @ExcelProperty("筹备金行号")
    private String cbjhh;
    @ExcelProperty("县级工会比例")
    private BigDecimal xjghbl;
    @ExcelProperty("县级工会金额")
    private BigDecimal xjghje;
    @ExcelProperty("县级工会账号")
    private String xjghzh;
    @ExcelProperty("县级工会户名")
    private String xjghhm;
    @ExcelProperty("县级工会行号")
    private String xjghhh;
    @ExcelProperty("市级工会比例")
    private BigDecimal sjghbl;
    @ExcelProperty("市级工会金额")
    private BigDecimal sjghje;
    @ExcelProperty("市级工会账号")
    private String sjghzh;
    @ExcelProperty("市级工会户名")
    private String sjghhm;
    @ExcelProperty("市级工会行号")
    private String sjghhh;
    @ExcelProperty("行业工会比例")
    private BigDecimal hyghbl;
    @ExcelProperty("行业工会金额")
    private BigDecimal hyghje;
    @ExcelProperty("行业工会账号")
    private String hyghzh;
    @ExcelProperty("行业工会户名")
    private String hyghhm;
    @ExcelProperty("行业工会行号")
    private String hyghhh;
    @ExcelProperty("属地工会比例")
    private BigDecimal sdghbl;
    @ExcelProperty("属地工会金额")
    private BigDecimal sdghje;
    @ExcelProperty("属地工会账号")
    private String sdghzh;
    @ExcelProperty("属地工会号码")
    private String sdghhm;
    @ExcelProperty("属地工会行号")
    private String sdghhh;
    @ExcelProperty("省总工会比例")
    private BigDecimal szghbl;
    @ExcelProperty("省总工会金额")
    private BigDecimal szghje;
    @ExcelProperty("省总工会账号")
    private String szghzh;
    @ExcelProperty("省总工会户名")
    private String szghhm;
    @ExcelProperty("省总工会行号")
    private String szghhh;
    @ExcelProperty("全总比例")
    private BigDecimal qgghbl;
    @ExcelProperty("全总金额")
    private BigDecimal qgghje;
    @ExcelProperty("全总工会账号")
    private String qgghzh;
    @ExcelProperty("全总工会户名")
    private String qgghhm;
    @ExcelProperty("全总工会行号")
    private String qgghhh;
}
