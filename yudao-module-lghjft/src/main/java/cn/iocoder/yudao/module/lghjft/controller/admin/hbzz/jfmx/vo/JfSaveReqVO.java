package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 -  经费明细对象新增/修改 Request VO")
@Data
public class JfSaveReqVO {

    @Schema(description = "工会经费ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19844")
    private Integer ghjfId;

    @Schema(description = "税票ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "321")
    @NotEmpty(message = "税票ID不能为空")
    private String spuuid;

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "登记序号不能为空")
    private String djxh;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "纳税人名称不能为空")
    private String nsrmc;

    @Schema(description = "纳税人简称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "纳税人简称不能为空")
    private String nsrjc;

    @Schema(description = "工会机构代码", example = "7022")
    private String deptId;

    @Schema(description = "工会机构行政级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "工会机构行政级别不能为空")
    private String ghjgxzjb;

    @Schema(description = "成立工会标记")
    private String clghbj;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "属地工会机构代码")
    private String sdghjgDm;

    @Schema(description = "属地工会行政级别")
    private String sdghjgxzjb;

    @Schema(description = "主管税务局代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主管税务局代码不能为空")
    private String zgswjDm;

    @Schema(description = "主管税务分局代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主管税务分局代码不能为空")
    private String zgswskfjDm;

    @Schema(description = "税管员代码")
    private String ssglyDm;

    @Schema(description = "工会类别代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "工会类别代码不能为空")
    private String ghlbDm;

    @Schema(description = "系统类别代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "系统类别代码不能为空")
    private String xtlbDm;

    @Schema(description = "组织机构类型代码")
    private String zzjglxDm;

    @Schema(description = "行业代码")
    private String hyDm;

    @Schema(description = "登记注册类型代码")
    private String djzclxDm;

    @Schema(description = "单位隶属关系代码")
    private String dwlsgxDm;

    @Schema(description = "申报类别代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申报类别代码不能为空")
    private String sblbDm;

    @Schema(description = "申报日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申报日期不能为空")
    private LocalDateTime nssbrq;

    @Schema(description = "票证序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "票证序号不能为空")
    private String pzxh;

    @Schema(description = "票证明细号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "票证明细号不能为空")
    private Integer pzmxxh;

    @Schema(description = "所属期起", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所属期起不能为空")
    private LocalDateTime skssqq;

    @Schema(description = "所属期止", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所属期止不能为空")
    private LocalDateTime skssqz;

    @Schema(description = "征收税务局代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "征收税务局代码不能为空")
    private String zsswjgDm;

    @Schema(description = "税款所属税务机关", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "税款所属税务机关不能为空")
    private String skssswjgDm;

    @Schema(description = "税款国库代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "税款国库代码不能为空")
    private String skgkDm;

    @Schema(description = "电子票证号码")
    private String dzsphm;

    @Schema(description = "票证种类")
    private String pzzlDm;

    @Schema(description = "票证字轨")
    private String pzzgDm;

    @Schema(description = "票证号码")
    private String pzhm;

    @Schema(description = "开票员代码")
    private String kpyDm;

    @Schema(description = "开票员姓名")
    private String kpyXm;

    @Schema(description = "征收品目代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "征收品目代码不能为空")
    private String zspmDm;

    @Schema(description = "征收子目代码")
    private String zszmDm;

    @Schema(description = "预算科目代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预算科目代码不能为空")
    private String yskmDm;

    @Schema(description = "工资总额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工资总额不能为空")
    private BigDecimal gzze;

    @Schema(description = "税率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税率不能为空")
    private BigDecimal sl;

    @Schema(description = "应纳税额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应纳税额不能为空")
    private BigDecimal ynse;

    @Schema(description = "减免税额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "减免税额不能为空")
    private BigDecimal jmse;

    @Schema(description = "已缴税额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "已缴税额不能为空")
    private BigDecimal yjse;

    @Schema(description = "应补退税额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应补退税额不能为空")
    private BigDecimal ybtse;

    @Schema(description = "缴款方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "缴款方式不能为空")
    private String jkfsDm;

    @Schema(description = "缴费行别")
    private String jfhb;

    @Schema(description = "缴费正好")
    private String jfzh;

    @Schema(description = "缴费户名")
    private String jfhm;

    @Schema(description = "缴费行号")
    private String jfhh;

    @Schema(description = "缴费银行")
    private String jfyh;

    @Schema(description = "入库日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入库日期不能为空")
    private LocalDateTime rkrq;

    @Schema(description = "结算标记", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "结算标记不能为空")
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

    @Schema(description = "基层工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "基层工会比例不能为空")
    private BigDecimal jcghbl;

    @Schema(description = "基层工会金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "基层工会金额不能为空")
    private BigDecimal jcghje;

    @Schema(description = "筹备金账号")
    private String cbjzh;

    @Schema(description = "筹备金户名")
    private String cbjhm;

    @Schema(description = "筹备金行号")
    private String cbjhh;

    @Schema(description = "筹备金比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "筹备金比例不能为空")
    private BigDecimal cbjbl;

    @Schema(description = "筹备金金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "筹备金金额不能为空")
    private BigDecimal cbjje;

    @Schema(description = "行业工会账号")
    private String hyghzh;

    @Schema(description = "行业工会户名")
    private String hyghhm;

    @Schema(description = "行业工会行号")
    private String hyghhh;

    @Schema(description = "行业工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "行业工会比例不能为空")
    private BigDecimal hyghbl;

    @Schema(description = "行业工会金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "行业工会金额不能为空")
    private BigDecimal hyghje;

    @Schema(description = "县级工会账号")
    private String xjghzh;

    @Schema(description = "县级工会户名")
    private String xjghhm;

    @Schema(description = "县级工会行号")
    private String xjghhh;

    @Schema(description = "县级工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "县级工会比例不能为空")
    private BigDecimal xjghbl;

    @Schema(description = "县级工会金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "县级工会金额不能为空")
    private BigDecimal xjghje;

    @Schema(description = "市级工会账号")
    private String sjghzh;

    @Schema(description = "市级工会户名")
    private String sjghhm;

    @Schema(description = "市级工会行号")
    private String sjghhh;

    @Schema(description = "市级工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "市级工会比例不能为空")
    private BigDecimal sjghbl;

    @Schema(description = "市级工会金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "市级工会金额不能为空")
    private BigDecimal sjghje;

    @Schema(description = "省总工会账号")
    private String szghzh;

    @Schema(description = "省总工会户名")
    private String szghhm;

    @Schema(description = "省总工会行号")
    private String szghhh;

    @Schema(description = "省总工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "省总工会比例不能为空")
    private BigDecimal szghbl;

    @Schema(description = "省总工会金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "省总工会金额不能为空")
    private BigDecimal szghje;

    @Schema(description = "全总账号")
    private String qgghzh;

    @Schema(description = "全总号码")
    private String qgghhm;

    @Schema(description = "全总行号")
    private String qgghhh;

    @Schema(description = "全总比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "全总比例不能为空")
    private BigDecimal qgghbl;

    @Schema(description = "全总金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "全总金额不能为空")
    private BigDecimal qgghje;

    @Schema(description = "稽查局账号")
    private String jcjzh;

    @Schema(description = "稽查局户名")
    private String jcjhm;

    @Schema(description = "稽查局行号")
    private String jcjhh;

    @Schema(description = "稽查局比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "稽查局比例不能为空")
    private BigDecimal jcjbl;

    @Schema(description = "稽查局金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "稽查局金额不能为空")
    private BigDecimal jcjje;

    @Schema(description = "稽查局文书号")
    private String jcwsh;

    @Schema(description = "省税局账号")
    private String sdszh;

    @Schema(description = "省税局户名")
    private String sdshm;

    @Schema(description = "省税局行号")
    private String sdshh;

    @Schema(description = "省税局比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "省税局比例不能为空")
    private BigDecimal sdsbl;

    @Schema(description = "省税局金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "省税局金额不能为空")
    private BigDecimal sdsje;

    @Schema(description = "税务机关账号")
    private String swjgzh;

    @Schema(description = "税务机关户名")
    private String swjghm;

    @Schema(description = "税务机关行号")
    private String swjghh;

    @Schema(description = "税务机关比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税务机关比例不能为空")
    private BigDecimal swjgbl;

    @Schema(description = "税务机关金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税务机关金额不能为空")
    private BigDecimal swjgje;

    @Schema(description = "筹备金退回标记", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "筹备金退回标记不能为空")
    private String cbjthbj;

    @Schema(description = "筹备金退回日期")
    private LocalDateTime cbjthrq;

    @Schema(description = "筹备金退回操作员")
    private String cbjthczy;

    @Schema(description = "校验码")
    private String jym;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "分配比例UUID", example = "9993")
    private String bluuid;

    @Schema(description = "属地工会账号")
    private String sdghzh;

    @Schema(description = "属地工会号码")
    private String sdghhm;

    @Schema(description = "属地工会行号")
    private String sdghhh;

    @Schema(description = "属地工会比例")
    private BigDecimal sdghbl;

    @Schema(description = "属地工会金额")
    private BigDecimal sdghje;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "修改人")
    private String updateBy;

}