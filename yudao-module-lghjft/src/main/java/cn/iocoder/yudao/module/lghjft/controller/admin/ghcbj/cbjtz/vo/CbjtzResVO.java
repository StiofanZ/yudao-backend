package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 筹备金台账 Response VO")
@Data
public class CbjtzResVO {

    @Schema(description = "ghjfId")
    private Long ghjfId;

    /** from cxtj_cbjqrfb sub-table via JOIN */
    @Schema(description = "返拨标记")
    @ExcelProperty("返拨标记")
    private String fbbj;

    /** from cxtj_cbjqrfb sub-table via JOIN */
    @Schema(description = "实返拨金额")
    @ExcelProperty("实返拨金额")
    private BigDecimal sfbje;

    /** from cxtj_cbjqrfb sub-table via JOIN */
    @Schema(description = "返拨日期")
    @ExcelProperty("返拨日期")
    private LocalDateTime fbrq;

    /** from cxtj_cbjqrfb sub-table via JOIN */
    @Schema(description = "资金下拨日期")
    @ExcelProperty("资金下拨日期")
    private LocalDateTime zjxcrq;

    /** from cxtj_cbjqrfb sub-table via JOIN */
    @Schema(description = "汇款凭证号")
    @ExcelProperty("汇款凭证号")
    private String hkpzh;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String bz;

    @Schema(description = "spuuid")
    @ExcelProperty("税票ID")
    private String spuuid;

    @Schema(description = "djxh")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "shxydm")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "nsrmc")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "nsrjc")
    @ExcelProperty("纳税人简称")
    private String nsrjc;

    @Schema(description = "deptId")
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "ghjgxzjb")
    private String ghjgxzjb;

    @Schema(description = "clghbj")
    private String clghbj;

    @Schema(description = "hyghbz")
    private String hyghbz;

    @Schema(description = "sdghjgDm")
    private String sdghjgDm;

    @Schema(description = "sdghjgxzjb")
    private String sdghjgxzjb;

    @Schema(description = "zgswjDm")
    @ExcelProperty(value = "主管税务局", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zgswjDm;

    @Schema(description = "zgswskfjDm")
    private String zgswskfjDm;

    @Schema(description = "ssglyDm")
    private String ssglyDm;

    @Schema(description = "ghlbDm")
    @ExcelProperty(value = "工会类别", converter = DictConvert.class)
    @DictFormat("sys_ghlb_type")
    private String ghlbDm;

    @Schema(description = "xtlbDm")
    @ExcelProperty(value = "系统类别", converter = DictConvert.class)
    @DictFormat("sys_xtlb_type")
    private String xtlbDm;

    @Schema(description = "zzjglxDm")
    private String zzjglxDm;

    @Schema(description = "hyDm")
    private String hyDm;

    @Schema(description = "djzclxDm")
    private String djzclxDm;

    @Schema(description = "dwlsgxDm")
    private String dwlsgxDm;

    @Schema(description = "sblbDm")
    private String sblbDm;

    @Schema(description = "nssbrq")
    @ExcelProperty("申报日期")
    private LocalDateTime nssbrq;

    @Schema(description = "pzxh")
    private String pzxh;

    @Schema(description = "pzmxxh")
    private Long pzmxxh;

    @Schema(description = "skssqq")
    @ExcelProperty("所属期起")
    private LocalDateTime skssqq;

    @Schema(description = "skssqz")
    @ExcelProperty("所属期止")
    private LocalDateTime skssqz;

    @Schema(description = "zsswjgDm")
    private String zsswjgDm;

    @Schema(description = "skssswjgDm")
    private String skssswjgDm;

    @Schema(description = "skgkDm")
    private String skgkDm;

    @Schema(description = "dzsphm")
    private String dzsphm;

    @Schema(description = "pzzlDm")
    private String pzzlDm;

    @Schema(description = "pzzgDm")
    private String pzzgDm;

    @Schema(description = "pzhm")
    private String pzhm;

    @Schema(description = "kpyDm")
    private String kpyDm;

    @Schema(description = "kpyXm")
    private String kpyXm;

    @Schema(description = "zspmDm")
    @ExcelProperty(value = "征收品目代码", converter = DictConvert.class)
    @DictFormat("sys_zspm_type")
    private String zspmDm;

    @Schema(description = "zszmDm")
    private String zszmDm;

    @Schema(description = "yskmDm")
    private String yskmDm;

    @Schema(description = "gzze")
    @ExcelProperty("工资总额")
    private BigDecimal gzze;

    @Schema(description = "sl")
    @ExcelProperty("税率")
    private BigDecimal sl;

    @Schema(description = "ynse")
    @ExcelProperty("应纳税额")
    private BigDecimal ynse;

    @Schema(description = "jmse")
    @ExcelProperty("减免税额")
    private BigDecimal jmse;

    @Schema(description = "yjse")
    @ExcelProperty("已缴税额")
    private BigDecimal yjse;

    @Schema(description = "ybtse")
    @ExcelProperty("缴费金额")
    private BigDecimal ybtse;

    @Schema(description = "jkfsDm")
    private String jkfsDm;

    @Schema(description = "jfhb")
    private String jfhb;

    @Schema(description = "jfzh")
    private String jfzh;

    @Schema(description = "jfhm")
    private String jfhm;

    @Schema(description = "jfhh")
    private String jfhh;

    @Schema(description = "jfyh")
    private String jfyh;

    @Schema(description = "rkrq")
    @ExcelProperty("入库日期")
    private LocalDateTime rkrq;

    @Schema(description = "jsbj")
    @ExcelProperty(value = "结算标记", converter = DictConvert.class)
    @DictFormat("sys_jsbj_type")
    private String jsbj;

    @Schema(description = "jsrq")
    @ExcelProperty("结算日期")
    private LocalDateTime jsrq;

    @Schema(description = "jsczy")
    private String jsczy;

    @Schema(description = "jcghzh")
    @ExcelProperty("基层工会账户")
    private String jcghzh;

    @Schema(description = "jcghhm")
    @ExcelProperty("基层工会户名")
    private String jcghhm;

    @Schema(description = "jcghhh")
    @ExcelProperty("基层工会行号")
    private String jcghhh;

    @Schema(description = "jcghbl")
    @ExcelProperty("基层工会比例")
    private BigDecimal jcghbl;

    @Schema(description = "jcghje")
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "cbjzh")
    @ExcelProperty("筹备金账号")
    private String cbjzh;

    @Schema(description = "cbjhm")
    @ExcelProperty("筹备金户名")
    private String cbjhm;

    @Schema(description = "cbjhh")
    @ExcelProperty("筹备金行号")
    private String cbjhh;

    @Schema(description = "cbjbl")
    @ExcelProperty("筹备金比例")
    private BigDecimal cbjbl;

    @Schema(description = "cbjje")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "hyghzh")
    @ExcelProperty("行业工会账号")
    private String hyghzh;

    @Schema(description = "hyghhm")
    @ExcelProperty("行业工会户名")
    private String hyghhm;

    @Schema(description = "hyghhh")
    @ExcelProperty("行业工会行号")
    private String hyghhh;

    @Schema(description = "hyghbl")
    @ExcelProperty("行业工会比例")
    private BigDecimal hyghbl;

    @Schema(description = "hyghje")
    @ExcelProperty("行业工会金额")
    private BigDecimal hyghje;

    @Schema(description = "xjghzh")
    @ExcelProperty("县级工会账号")
    private String xjghzh;

    @Schema(description = "xjghhm")
    @ExcelProperty("县级工会户名")
    private String xjghhm;

    @Schema(description = "xjghhh")
    @ExcelProperty("县级工会行号")
    private String xjghhh;

    @Schema(description = "xjghbl")
    @ExcelProperty("县级工会比例")
    private BigDecimal xjghbl;

    @Schema(description = "xjghje")
    @ExcelProperty("县级工会金额")
    private BigDecimal xjghje;

    @Schema(description = "sjghzh")
    @ExcelProperty("市级工会账号")
    private String sjghzh;

    @Schema(description = "sjghhm")
    @ExcelProperty("市级工会户名")
    private String sjghhm;

    @Schema(description = "sjghhh")
    @ExcelProperty("市级工会行号")
    private String sjghhh;

    @Schema(description = "sjghbl")
    @ExcelProperty("市级工会比例")
    private BigDecimal sjghbl;

    @Schema(description = "sjghje")
    @ExcelProperty("市级工会金额")
    private BigDecimal sjghje;

    @Schema(description = "szghzh")
    @ExcelProperty("省总工会账号")
    private String szghzh;

    @Schema(description = "szghhm")
    @ExcelProperty("省总工会户名")
    private String szghhm;

    @Schema(description = "szghhh")
    @ExcelProperty("省总工会行号")
    private String szghhh;

    @Schema(description = "szghbl")
    @ExcelProperty("省总工会比例")
    private BigDecimal szghbl;

    @Schema(description = "szghje")
    @ExcelProperty("省总工会金额")
    private BigDecimal szghje;

    @Schema(description = "qgghzh")
    @ExcelProperty("全总账号")
    private String qgghzh;

    @Schema(description = "qgghhm")
    @ExcelProperty("全总户名")
    private String qgghhm;

    @Schema(description = "qgghhh")
    @ExcelProperty("全总行号")
    private String qgghhh;

    @Schema(description = "qgghbl")
    @ExcelProperty("全总比例")
    private BigDecimal qgghbl;

    @Schema(description = "qgghje")
    @ExcelProperty("全总金额")
    private BigDecimal qgghje;

    @Schema(description = "jcjzh")
    private String jcjzh;

    @Schema(description = "jcjhm")
    private String jcjhm;

    @Schema(description = "jcjhh")
    private String jcjhh;

    @Schema(description = "jcjbl")
    private BigDecimal jcjbl;

    @Schema(description = "jcjje")
    private BigDecimal jcjje;

    @Schema(description = "jcwsh")
    private String jcwsh;

    @Schema(description = "sdszh")
    @ExcelProperty("省税局账号")
    private String sdszh;

    @Schema(description = "sdshm")
    @ExcelProperty("省税局户名")
    private String sdshm;

    @Schema(description = "sdshh")
    @ExcelProperty("省税局行号")
    private String sdshh;

    @Schema(description = "sdsbl")
    @ExcelProperty("省税局比例")
    private BigDecimal sdsbl;

    @Schema(description = "sdsje")
    @ExcelProperty("省税局金额")
    private BigDecimal sdsje;

    @Schema(description = "swjgzh")
    @ExcelProperty("税务机关账号")
    private String swjgzh;

    @Schema(description = "swjghm")
    @ExcelProperty("税务机关户名")
    private String swjghm;

    @Schema(description = "swjghh")
    @ExcelProperty("税务机关行号")
    private String swjghh;

    @Schema(description = "swjgbl")
    @ExcelProperty("税务机关比例")
    private BigDecimal swjgbl;

    @Schema(description = "swjgje")
    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;

    @Schema(description = "cbjthbj")
    @ExcelProperty(value = "基层经费拨付状态", converter = DictConvert.class)
    @DictFormat("sys_cbjcl_type")
    private String cbjthbj;

    @Schema(description = "cbjthrq")
    @ExcelProperty("处理日期")
    private LocalDateTime cbjthrq;

    @Schema(description = "cbjthczy")
    @ExcelProperty("操作员")
    private String cbjthczy;

    @Schema(description = "jym")
    @ExcelProperty("校验码")
    private String jym;

    @Schema(description = "hkpch")
    @ExcelProperty("划款批次号")
    private String hkpch;

    @Schema(description = "bluuid")
    private String bluuid;

    @Schema(description = "sdghzh")
    @ExcelProperty("属地工会账号")
    private String sdghzh;

    @Schema(description = "sdghhm")
    @ExcelProperty("属地工会户名")
    private String sdghhm;

    @Schema(description = "sdghhh")
    @ExcelProperty("属地工会行号")
    private String sdghhh;

    @Schema(description = "sdghbl")
    @ExcelProperty("属地工会比例")
    private BigDecimal sdghbl;

    @Schema(description = "sdghje")
    @ExcelProperty("属地工会金额")
    private BigDecimal sdghje;
}
