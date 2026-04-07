package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 经费台账明细分页 Request VO")
@Data
public class JftzmxPageReqVO extends PageParam {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "工会机构行政级别")
    private String ghjgxzjb;

    @Schema(description = "成立工会标记")
    private String clghbj;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "属地工会行政级别")
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

    @Schema(description = "税款概况代码")
    private String skgkDm;

    @Schema(description = "电子审批号码")
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

    @Schema(description = "经费划拨")
    private String jfhb;

    @Schema(description = "经费账号")
    private String jfzh;

    @Schema(description = "经费户名")
    private String jfhm;

    @Schema(description = "经费行号")
    private String jfhh;

    @Schema(description = "经费银行")
    private String jfyh;

    @Schema(description = "入库日期(年月)")
    private String rkrq;

    @Schema(description = "入库日期-开始")
    private String beginRkrq;

    @Schema(description = "入库日期-结束")
    private String endRkrq;

    @Schema(description = "年度")
    private String nd;

    @Schema(description = "结算标记")
    private List<String> jsbj;

    @Schema(description = "结算日期")
    private String jsrq;

    @Schema(description = "结算日期-开始")
    private String beginJsrq;

    @Schema(description = "结算日期-结束")
    private String endJsrq;

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

    @Schema(description = "行业工会账号")
    private String hyghzh;

    @Schema(description = "行业工会户名")
    private String hyghhm;

    @Schema(description = "行业工会行号")
    private String hyghhh;

    @Schema(description = "行业工会比例")
    private BigDecimal hyghbl;

    @Schema(description = "行业工会金额")
    private BigDecimal hyghje;

    @Schema(description = "县级工会账号")
    private String xjghzh;

    @Schema(description = "县级工会户名")
    private String xjghhm;

    @Schema(description = "县级工会行号")
    private String xjghhh;

    @Schema(description = "县级工会比例")
    private BigDecimal xjghbl;

    @Schema(description = "县级工会金额")
    private BigDecimal xjghje;

    @Schema(description = "市级工会账号")
    private String sjghzh;

    @Schema(description = "市级工会户名")
    private String sjghhm;

    @Schema(description = "市级工会行号")
    private String sjghhh;

    @Schema(description = "市级工会比例")
    private BigDecimal sjghbl;

    @Schema(description = "市级工会金额")
    private BigDecimal sjghje;

    @Schema(description = "省级工会账号")
    private String szghzh;

    @Schema(description = "省级工会户名")
    private String szghhm;

    @Schema(description = "省级工会行号")
    private String szghhh;

    @Schema(description = "省级工会比例")
    private BigDecimal szghbl;

    @Schema(description = "省级工会金额")
    private BigDecimal szghje;

    @Schema(description = "全国工会账号")
    private String qgghzh;

    @Schema(description = "全国工会户名")
    private String qgghhm;

    @Schema(description = "全国工会行号")
    private String qgghhh;

    @Schema(description = "全国工会比例")
    private BigDecimal qgghbl;

    @Schema(description = "全国工会金额")
    private BigDecimal qgghje;

    @Schema(description = "稽查局账号")
    private String jcjzh;

    @Schema(description = "稽查局户名")
    private String jcjhm;

    @Schema(description = "稽查局行号")
    private String jcjhh;

    @Schema(description = "稽查局比例")
    private BigDecimal jcjbl;

    @Schema(description = "稽查局金额")
    private BigDecimal jcjje;

    @Schema(description = "稽查局文书号")
    private String jcwsh;

    @Schema(description = "省税局账号")
    private String sdszh;

    @Schema(description = "省税局户名")
    private String sdshm;

    @Schema(description = "省税局行号")
    private String sdshh;

    @Schema(description = "省税局比例")
    private BigDecimal sdsbl;

    @Schema(description = "省税局金额")
    private BigDecimal sdsje;

    @Schema(description = "税务机关账号")
    private String swjgzh;

    @Schema(description = "税务机关户名")
    private String swjghm;

    @Schema(description = "税务机关行号")
    private String swjghh;

    @Schema(description = "税务机关比例")
    private BigDecimal swjgbl;

    @Schema(description = "税务机关金额")
    private BigDecimal swjgje;

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

    @Schema(description = "补录UUID")
    private String bluuid;

    @Schema(description = "属地工会账号")
    private String sdghzh;

    @Schema(description = "属地工会户名")
    private String sdghhm;

    @Schema(description = "属地工会行号")
    private String sdghhh;

    @Schema(description = "属地工会比例")
    private BigDecimal sdghbl;

    @Schema(description = "属地工会金额")
    private BigDecimal sdghje;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "更新时间")
    private String updateTime;

    @Schema(description = "小微类型")
    private String xwlx;

    @Schema(description = "小额类型23")
    private List<String> xelx23;

    @Schema(description = "小额类型24")
    private List<String> xelx24;

    @Schema(description = "小额类型25")
    private List<String> xelx25;
}
