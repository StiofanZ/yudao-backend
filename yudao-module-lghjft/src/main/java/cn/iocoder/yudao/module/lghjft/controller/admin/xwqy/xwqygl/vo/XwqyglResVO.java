package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ExcelIgnoreUnannotated
@Schema(description = "管理后台 - 小微企业管理 Response VO")
@Data
public class XwqyglResVO {

    @ExcelProperty("已建会缴筹备金户标记")
    @Schema(description = "已建会缴筹备金户标记")
    private String dclghbj;

    @Schema(description = "部门ID")
    private String deptId;

    @ExcelProperty("行业工会标志")
    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "登记序号")
    private String djxh;

    @ExcelProperty("社会信用代码")
    @Schema(description = "社会信用代码")
    private String shxydm;

    @ExcelProperty("纳税人名称")
    @Schema(description = "纳税人名称")
    private String nsrmc;

    @ExcelProperty("纳税人简称")
    @Schema(description = "纳税人简称")
    private String nsrjc;

    @ExcelProperty("主管税务机关代码")
    @Schema(description = "主管税务机关代码")
    private String zgswjDm;

    @ExcelProperty("主管税务机关名称")
    @Schema(description = "主管税务机关名称")
    private String zgswjmc;

    @ExcelProperty("主管税务科分局代码")
    @Schema(description = "主管税务科分局代码")
    private String zgswskfjDm;

    @ExcelProperty("主管税务科分局名称")
    @Schema(description = "主管税务科分局名称")
    private String zgswskfjmc;

    @ExcelProperty("所属管理员代码")
    @Schema(description = "所属管理员代码")
    private String ssglyDm;

    @ExcelProperty("所属管理员姓名")
    @Schema(description = "所属管理员姓名")
    private String ssglyxm;

    @ExcelProperty("组织机构类型代码")
    @Schema(description = "组织机构类型代码")
    private String zzjglxDm;

    @ExcelProperty("组织机构类型名称")
    @Schema(description = "组织机构类型名称")
    private String zzjglxmc;

    @ExcelProperty("行业代码")
    @Schema(description = "行业代码")
    private String hyDm;

    @ExcelProperty("行业名称")
    @Schema(description = "行业名称")
    private String hymc;

    @ExcelProperty("登记注册类型代码")
    @Schema(description = "登记注册类型代码")
    private String djzclxDm;

    @ExcelProperty("登记注册类型名称")
    @Schema(description = "登记注册类型名称")
    private String djzclxmc;

    @ExcelProperty("单位隶属关系代码")
    @Schema(description = "单位隶属关系代码")
    private String dwlsgxDm;

    @ExcelProperty("单位隶属关系名称")
    @Schema(description = "单位隶属关系名称")
    private String dwlsgxmc;

    @ExcelProperty("职工人数")
    @Schema(description = "职工人数")
    private BigDecimal zgrs;

    @ExcelProperty("纳税人状态代码")
    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @ExcelProperty("纳税人状态名称")
    @Schema(description = "纳税人状态名称")
    private String nsrztmc;

    @Schema(description = "发证日期")
    private Date fzcrq;

    @Schema(description = "注销日期")
    private Date zxrq;

    @ExcelProperty("注册地址")
    @Schema(description = "注册地址")
    private String zcdz;

    @ExcelProperty("邮政编码")
    @Schema(description = "邮政编码")
    private String yzbm;

    @ExcelProperty("联系人")
    @Schema(description = "联系人")
    private String lxr;

    @ExcelProperty("联系电话")
    @Schema(description = "联系电话")
    private String lxdh;

    @ExcelProperty("工会类别代码")
    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "会计分类1代码")
    private String hjfl1Dm;

    @Schema(description = "会计分类2代码")
    private String hjfl2Dm;

    @Schema(description = "会计分类3代码")
    private String hjfl3Dm;

    @Schema(description = "会计分类4代码")
    private String hjfl4Dm;

    @Schema(description = "会计分类5代码")
    private String hjfl5Dm;

    @Schema(description = "会计分类6代码")
    private String hjfl6Dm;

    @Schema(description = "小微上报时间")
    private Date hjfl7Dm;

    @Schema(description = "是否已建会缴纳筹备金")
    private String hjfl8Dm;

    @Schema(description = "小微企业标志")
    private String hjfl9Dm;

    @Schema(description = "属地工会机构代码")
    private String sdghjgDm;

    @ExcelProperty("成立工会标志")
    @Schema(description = "成立工会标志")
    private String clghbj;

    @ExcelProperty("成立工会日期")
    @Schema(description = "成立工会日期")
    private Date clghrq;

    @ExcelProperty("基层工会代码")
    @Schema(description = "基层工会代码")
    private String jcghdm;

    @ExcelProperty("基层工会名称")
    @Schema(description = "基层工会名称")
    private String jcghmc;

    @ExcelProperty("基层工会账户")
    @Schema(description = "基层工会账户")
    private String jcghzh;

    @ExcelProperty("基层工会户名")
    @Schema(description = "基层工会户名")
    private String jcghhm;

    @ExcelProperty("基层工会行号")
    @Schema(description = "基层工会行号")
    private String jcghhh;

    @ExcelProperty("基层工会银行")
    @Schema(description = "基层工会银行")
    private String jcghyh;

    @ExcelProperty("备注")
    @Schema(description = "备注")
    private String bz;

    @Schema(description = "校验码")
    private String jym;

    @ExcelProperty("纳税人识别号")
    @Schema(description = "纳税人识别号")
    private String nsrsbh;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "文件地址")
    private String fileUrl;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "税务数据同步时间")
    private Date sjtbSj;
}