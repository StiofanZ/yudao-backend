package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 筹备金全返 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhjfcbjqfResVO {

    private Long ghjfId;
    @ExcelProperty("税票ID")
    private String spuuid;
    @ExcelProperty("登记序号")
    private String djxh;
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @ExcelProperty("纳税人名称")
    private String nsrmc;
    @ExcelProperty("纳税人简称")
    private String nsrjc;
    @ExcelProperty("工会机构代码")
    private String deptId;
    @ExcelProperty("工会机构行政级别")
    private String ghjgxzjb;
    @ExcelProperty("成立工会标记")
    private String clghbj;
    @ExcelProperty("行业工会标志")
    private String hyghbz;
    @ExcelProperty("属地工会机构代码")
    private String sdghjgDm;
    @ExcelProperty("属地工会行政级别")
    private String sdghjgxzjb;
    @ExcelProperty("主管税务局代码")
    private String zgswjDm;
    @ExcelProperty("主管税务分局代码")
    private String zgswskfjDm;
    @ExcelProperty("税管员代码")
    private String ssglyDm;
    @ExcelProperty("工会类别代码")
    private String ghlbDm;
    @ExcelProperty("系统类别代码")
    private String xtlbDm;
    @ExcelProperty("组织机构类型代码")
    private String zzjglxDm;
    @ExcelProperty("行业代码")
    private String hyDm;
    @ExcelProperty("登记注册类型代码")
    private String djzclxDm;
    @ExcelProperty("单位隶属关系代码")
    private String dwlsgxDm;
    @ExcelProperty("申报类别代码")
    private String sblbDm;
    @ExcelProperty("申报日期")
    private String nssbrq;
    @ExcelProperty("票证序号")
    private String pzxh;
    @ExcelProperty("票证明细号")
    private Long pzmxxh;
    @ExcelProperty("所属期起")
    private String skssqq;
    @ExcelProperty("所属期止")
    private String skssqz;
    @ExcelProperty("征收税务局代码")
    private String zsswjgDm;
    @ExcelProperty("税款所属税务机关")
    private String skssswjgDm;
    @ExcelProperty("税款国库代码")
    private String skgkDm;
    @ExcelProperty("电子票证号码")
    private String dzsphm;
    @ExcelProperty("票证种类")
    private String pzzlDm;
    @ExcelProperty("票证字轨")
    private String pzzgDm;
    @ExcelProperty("票证号码")
    private String pzhm;
    @ExcelProperty("开票员代码")
    private String kpyDm;
    @ExcelProperty("开票员姓名")
    private String kpyXm;
    @ExcelProperty("征收品目代码")
    private String zspmDm;
    @ExcelProperty("征收子目代码")
    private String zszmDm;
    @ExcelProperty("预算科目代码")
    private String yskmDm;
    @ExcelProperty("工资总额")
    private BigDecimal gzze;
    @ExcelProperty("税率")
    private BigDecimal sl;
    @ExcelProperty("应纳税额")
    private BigDecimal ynse;
    @ExcelProperty("全退金额")
    private BigDecimal jmse;
    @ExcelProperty("已缴税额")
    private BigDecimal yjse;
    @ExcelProperty("缴费金额")
    private BigDecimal ybtse;
    @ExcelProperty("缴款方式")
    private String jkfsDm;
    @ExcelProperty("缴费行别")
    private String jfhb;
    @ExcelProperty("缴费账号")
    private String jfzh;
    @ExcelProperty("缴费户名")
    private String jfhm;
    @ExcelProperty("缴费行号")
    private String jfhh;
    @ExcelProperty("缴费银行")
    private String jfyh;
    @ExcelProperty("入库日期")
    private String rkrq;
    @ExcelProperty("结算标记")
    private String jsbj;
    @ExcelProperty("结算日期")
    private String jsrq;
    @ExcelProperty("结算操作员")
    private String jsczy;
    @ExcelProperty("基层工会账户")
    private String jcghzh;
    @ExcelProperty("基层工会户名")
    private String jcghhm;
    @ExcelProperty("基层工会行号")
    private String jcghhh;
    @ExcelProperty("基层工会比例")
    private BigDecimal jcghbl;
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;
    @ExcelProperty("筹备金账号")
    private String cbjzh;
    @ExcelProperty("筹备金户名")
    private String cbjhm;
    @ExcelProperty("筹备金行号")
    private String cbjhh;
    @ExcelProperty("筹备金比例")
    private BigDecimal cbjbl;
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;
    @ExcelProperty("行业工会账号")
    private String hyghzh;
    @ExcelProperty("行业工会户名")
    private String hyghhm;
    @ExcelProperty("行业工会行号")
    private String hyghhh;
    @ExcelProperty("行业工会比例")
    private BigDecimal hyghbl;
    @ExcelProperty("行业工会金额")
    private BigDecimal hyghje;
    @ExcelProperty("县级工会账号")
    private String xjghzh;
    @ExcelProperty("县级工会户名")
    private String xjghhm;
    @ExcelProperty("县级工会行号")
    private String xjghhh;
    @ExcelProperty("县级工会比例")
    private BigDecimal xjghbl;
    @ExcelProperty("县级工会金额")
    private BigDecimal xjghje;
    @ExcelProperty("市级工会账号")
    private String sjghzh;
    @ExcelProperty("市级工会户名")
    private String sjghhm;
    @ExcelProperty("市级工会行号")
    private String sjghhh;
    @ExcelProperty("市级工会比例")
    private BigDecimal sjghbl;
    @ExcelProperty("市级工会金额")
    private BigDecimal sjghje;
    @ExcelProperty("省总工会账号")
    private String szghzh;
    @ExcelProperty("省总工会户名")
    private String szghhm;
    @ExcelProperty("省总工会行号")
    private String szghhh;
    @ExcelProperty("省总工会比例")
    private BigDecimal szghbl;
    @ExcelProperty("省总工会金额")
    private BigDecimal szghje;
    @ExcelProperty("全总账号")
    private String qgghzh;
    @ExcelProperty("全总号码")
    private String qgghhm;
    @ExcelProperty("全总行号")
    private String qgghhh;
    @ExcelProperty("全总比例")
    private BigDecimal qgghbl;
    @ExcelProperty("全总金额")
    private BigDecimal qgghje;
    @ExcelProperty("稽查局账号")
    private String jcjzh;
    @ExcelProperty("稽查局户名")
    private String jcjhm;
    @ExcelProperty("稽查局行号")
    private String jcjhh;
    @ExcelProperty("稽查局比例")
    private BigDecimal jcjbl;
    @ExcelProperty("稽查局金额")
    private BigDecimal jcjje;
    @ExcelProperty("稽查局文书号")
    private String jcwsh;
    @ExcelProperty("省税局账号")
    private String sdszh;
    @ExcelProperty("省税局户名")
    private String sdshm;
    @ExcelProperty("省税局行号")
    private String sdshh;
    @ExcelProperty("省税局比例")
    private BigDecimal sdsbl;
    @ExcelProperty("省税局金额")
    private BigDecimal sdsje;
    @ExcelProperty("税务机关账号")
    private String swjgzh;
    @ExcelProperty("税务机关户名")
    private String swjghm;
    @ExcelProperty("税务机关行号")
    private String swjghh;
    @ExcelProperty("税务机关比例")
    private BigDecimal swjgbl;
    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;
    @ExcelProperty("筹备金全返状态")
    private String cbjqfzt;
    @ExcelProperty("筹备金全返日期")
    private String cbjqfrq;
    @ExcelProperty("筹备金全返结果")
    private String cbjqfjg;
    @ExcelProperty("筹备金结算拨付基层状态")
    private String cbjjsbfjczt;
    @ExcelProperty("筹备金结算拨付基层日期")
    private String cbjjsbfjcrq;
    @ExcelProperty("校验码")
    private String jym;
    @ExcelProperty("划款批次号")
    private String hkpch;
    @ExcelProperty("全返批次号")
    private String qfpch;
    @ExcelProperty("备注")
    private String bz;
    @ExcelProperty("分配比例UUID")
    private String bluuid;
    @ExcelProperty("属地工会账号")
    private String sdghzh;
    @ExcelProperty("属地工会号码")
    private String sdghhm;
    @ExcelProperty("属地工会行号")
    private String sdghhh;
    @ExcelProperty("属地工会比例")
    private BigDecimal sdghbl;
    @ExcelProperty("属地工会金额")
    private BigDecimal sdghje;
    // v1 额外字段
    private String cbjthbj;
    private String cbjthrq;
    private String cbjthczy;
}
