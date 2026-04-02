package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 经费明细对象 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JfResVO {

    @Schema(description = "搜索值")
    private String searchValue;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人")
    @ExcelProperty("修改人")
    private String updateBy;

    @Schema(description = "修改时间")
    @ExcelProperty("修改时间")
    private LocalDateTime updateTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "参数")
    private Object params;

    // ===================== 核心业务字段 =====================
    @Schema(description = "税票ID")
    @ExcelProperty("税票ID")
    private String spuuid;

    @Schema(description = "登记序号")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "税务机关名称")
    private String swjgmc;

    @Schema(description = "工会机构名称")
    private String ghjgmc;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "纳税人简称")
    @ExcelProperty("纳税人简称")
    private String nsrjc;

    @Schema(description = "工会机构代码")
    @ExcelProperty("工会机构代码")
    private String deptId;

    @Schema(description = "工会机构行政级别")
    @ExcelProperty("工会机构行政级别")
    private String ghjgxzjb;

    @Schema(description = "成立工会标记")
    @ExcelProperty("成立工会标记")
    private String clghbj;

    @Schema(description = "行业工会标志")
    @ExcelProperty("行业工会标志")
    private String hyghbz;

    @Schema(description = "属地工会机构代码")
    private String sdghjgDm;

    @Schema(description = "属地工会行政级别")
    private String sdghjgxzjb;

    @Schema(description = "主管税务局代码")
    @ExcelProperty("主管税务局代码")
    private String zgswjDm;

    @Schema(description = "主管税务分局代码")
    @ExcelProperty("主管税务分局代码")
    private String zgswskfjDm;

    @Schema(description = "税管员代码")
    @ExcelProperty("税管员代码")
    private String ssglyDm;

    @Schema(description = "工会类别代码")
    @ExcelProperty("工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    @ExcelProperty("系统类别代码")
    private String xtlbDm;

    @Schema(description = "组织机构类型代码")
    @ExcelProperty("组织机构类型代码")
    private String zzjglxDm;

    @Schema(description = "行业代码")
    @ExcelProperty("行业代码")
    private String hyDm;

    @Schema(description = "登记注册类型代码")
    @ExcelProperty("登记注册类型代码")
    private String djzclxDm;

    @Schema(description = "单位隶属关系代码")
    @ExcelProperty("单位隶属关系代码")
    private String dwlsgxDm;

    @Schema(description = "申报类别代码")
    @ExcelProperty("申报类别代码")
    private String sblbDm;

    @Schema(description = "申报日期")
    @ExcelProperty("申报日期")
    private LocalDateTime nssbrq;

    @Schema(description = "票证序号")
    @ExcelProperty("票证序号")
    private String pzxh;

    @Schema(description = "票证明细号")
    @ExcelProperty("票证明细号")
    private Integer pzmxxh;

    @Schema(description = "所属期起")
    @ExcelProperty("所属期起")
    private LocalDateTime skssqq;

    @Schema(description = "所属期止")
    @ExcelProperty("所属期止")
    private LocalDateTime skssqz;

    @Schema(description = "征收税务局代码")
    @ExcelProperty("征收税务局代码")
    private String zsswjgDm;

    @Schema(description = "税款所属税务机关")
    @ExcelProperty("税款所属税务机关")
    private String skssswjgDm;

    @Schema(description = "税款国库代码")
    @ExcelProperty("税款国库代码")
    private String skgkDm;

    @Schema(description = "电子票证号码")
    @ExcelProperty("电子票证号码")
    private String dzsphm;

    @Schema(description = "票证种类")
    @ExcelProperty("票证种类")
    private String pzzlDm;

    @Schema(description = "票证字轨")
    private String pzzgDm;

    @Schema(description = "票证号码")
    private String pzhm;

    @Schema(description = "开票员代码")
    @ExcelProperty("开票员代码")
    private String kpyDm;

    @Schema(description = "开票员姓名")
    @ExcelProperty("开票员姓名")
    private String kpyXm;

    @Schema(description = "征收品目代码")
    @ExcelProperty("征收品目代码")
    private String zspmDm;

    @Schema(description = "征收子目代码")
    private String zszmDm;

    @Schema(description = "预算科目代码")
    @ExcelProperty("预算科目代码")
    private String yskmDm;

    @Schema(description = "工资总额")
    @ExcelProperty("工资总额")
    private BigDecimal gzze;

    @Schema(description = "税率")
    @ExcelProperty("税率")
    private BigDecimal sl;

    @Schema(description = "应纳税额")
    @ExcelProperty("应纳税额")
    private BigDecimal ynse;

    @Schema(description = "减免税额")
    @ExcelProperty("减免税额")
    private BigDecimal jmse;

    @Schema(description = "已缴税额")
    @ExcelProperty("已缴税额")
    private BigDecimal yjse;

    @Schema(description = "应补退税额")
    @ExcelProperty("应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "缴款方式")
    @ExcelProperty("缴款方式")
    private String jkfsDm;

    @Schema(description = "缴费行别")
    private String jfhb;

    @Schema(description = "缴费账号")
    private String jfzh;

    @Schema(description = "缴费户名")
    private String jfhm;

    @Schema(description = "缴费行号")
    private String jfhh;

    @Schema(description = "缴费银行")
    private String jfyh;

    @Schema(description = "入库日期")
    @ExcelProperty("入库日期")
    private LocalDateTime rkrq;

    @Schema(description = "结算标记")
    @ExcelProperty("结算标记")
    private List<String> jsbj;

    @Schema(description = "结算日期")
    @ExcelProperty("结算日期")
    private LocalDateTime jsrq;

    @Schema(description = "结算操作员")
    private String jsczy;

    // ===================== 核心返回字段 =====================
    @Schema(description = "基层工会账户")
    @ExcelProperty("基层工会账户")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    @ExcelProperty("基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会行号")
    @ExcelProperty("基层工会行号")
    private String jcghhh;

    @Schema(description = "基层工会比例")
    @ExcelProperty("基层工会比例")
    private BigDecimal jcghbl;

    @Schema(description = "基层工会金额")
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "筹备金退回标记")
    @ExcelProperty("筹备金退回标记")
    private String cbjthbj;

    @Schema(description = "校验码")
    @ExcelProperty("校验码")
    private String jym;

    @Schema(description = "划款批次号")
    @ExcelProperty("划款批次号")
    private String hkpch;

    @Schema(description = "分配比例UUID")
    @ExcelProperty("分配比例UUID")
    private String bluuid;
}