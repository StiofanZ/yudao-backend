package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 经费明细新增/修改 Request VO")
@Data
public class JfmxSaveReqVO {

    @Schema(description = "spuuid (修改时必传)")
    private String spuuid;

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

    @Schema(description = "上代工会机构代码")
    private String sdghjgDm;

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

    @Schema(description = "纳税申报日期")
    private String nssbrq;

    @Schema(description = "凭证序号")
    private String pzxh;

    @Schema(description = "凭证明细序号")
    private Long pzmxxh;

    @Schema(description = "税款所属期起")
    private String skssqq;

    @Schema(description = "税款所属期止")
    private String skssqz;

    @Schema(description = "征收税务机关代码")
    private String zsswjgDm;

    @Schema(description = "税款所属税务机关代码")
    private String skssswjgDm;

    @Schema(description = "收款国库代码")
    private String skgkDm;

    @Schema(description = "电子税票号码")
    private String dzsphm;

    @Schema(description = "凭证种类代码")
    private String pzzlDm;

    @Schema(description = "凭证字轨代码")
    private String pzzgDm;

    @Schema(description = "凭证号码")
    private String pzhm;

    @Schema(description = "开票员代码")
    private String kpyDm;

    @Schema(description = "开票员姓名")
    private String kpyXm;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "征收子目代码")
    private String zszmDm;

    @Schema(description = "预算科目代码")
    private String yskmDm;

    @Schema(description = "工资总额")
    private BigDecimal gzze;

    @Schema(description = "税率")
    private BigDecimal sl;

    @Schema(description = "应纳税额")
    private BigDecimal ynse;

    @Schema(description = "减免税额")
    private BigDecimal jmse;

    @Schema(description = "已缴税额")
    private BigDecimal yjse;

    @Schema(description = "应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "缴款方式代码")
    private String jkfsDm;

    @Schema(description = "经费货币")
    private String jfhb;

    @Schema(description = "经费账号")
    private String jfzh;

    @Schema(description = "经费户名")
    private String jfhm;

    @Schema(description = "经费行号")
    private String jfhh;

    @Schema(description = "经费银行")
    private String jfyh;

    @Schema(description = "入库日期")
    private String rkrq;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "结算日期")
    private String jsrq;

    @Schema(description = "结算操作员")
    private String jsczy;

    @Schema(description = "基层工会账号")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会行号")
    private String jcghhh;

    @Schema(description = "基层工会比例")
    private BigDecimal jcghbl;

    @Schema(description = "基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "筹备金账号")
    private String cbjzh;

    @Schema(description = "筹备金户名")
    private String cbjhm;

    @Schema(description = "筹备金行号")
    private String cbjhh;

    @Schema(description = "筹备金比例")
    private BigDecimal cbjbl;

    @Schema(description = "筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "筹备金退回标记")
    private String cbjthbj;

    @Schema(description = "筹备金退回日期")
    private String cbjthrq;

    @Schema(description = "筹备金退回操作员")
    private String cbjthczy;

    @Schema(description = "校验码")
    private String jym;

    @Schema(description = "汇款批次号")
    private String hkpch;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "bluuid")
    private String bluuid;

    @Schema(description = "上代工会账号")
    private String sdghzh;

    @Schema(description = "上代工会户名")
    private String sdghhm;

    @Schema(description = "上代工会行号")
    private String sdghhh;

    @Schema(description = "上代工会比例")
    private BigDecimal sdghbl;

    @Schema(description = "上代工会金额")
    private BigDecimal sdghje;
}
