package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 基层账户空需维护 Response VO")
@Data
public class JczhkxwhResVO {

    @Schema(description = "deptId")
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "hyghbz")
    private String hyghbz;

    @Schema(description = "djxh")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "shxydm")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "nsrmc")
    @ExcelProperty("缴费单位名称")
    private String nsrmc;

    @Schema(description = "nsrjc")
    @ExcelProperty("纳税人简称")
    private String nsrjc;

    @Schema(description = "zgswjDm")
    @ExcelProperty(value = "主管税务局", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zgswjDm;

    @Schema(description = "zgswjmc")
    @ExcelProperty("主管税务机关名称")
    private String zgswjmc;

    @Schema(description = "zgswskfjDm")
    private String zgswskfjDm;

    @Schema(description = "zgswskfjmc")
    @ExcelProperty("科所分局名称")
    private String zgswskfjmc;

    @Schema(description = "ssglyDm")
    private String ssglyDm;

    @Schema(description = "ssglyxm")
    @ExcelProperty("税管员姓名")
    private String ssglyxm;

    @Schema(description = "zzjglxDm")
    private String zzjglxDm;

    @Schema(description = "zzjglxmc")
    @ExcelProperty("组织机构类型名称")
    private String zzjglxmc;

    @Schema(description = "hyDm")
    private String hyDm;

    @Schema(description = "hymc")
    @ExcelProperty("行业名称")
    private String hymc;

    @Schema(description = "djzclxDm")
    private String djzclxDm;

    @Schema(description = "djzclxmc")
    @ExcelProperty("登记注册类型名称")
    private String djzclxmc;

    @Schema(description = "dwlsgxDm")
    private String dwlsgxDm;

    @Schema(description = "dwlsgxmc")
    @ExcelProperty("单位隶属关系名称")
    private String dwlsgxmc;

    @Schema(description = "zgrs")
    @ExcelProperty("总共人数")
    private BigDecimal zgrs;

    @Schema(description = "nsrztDm")
    private String nsrztDm;

    @Schema(description = "nsrztmc")
    @ExcelProperty("纳税人状态名称")
    private String nsrztmc;

    @Schema(description = "fzcrq")
    private LocalDateTime fzcrq;

    @Schema(description = "zxrq")
    private LocalDateTime zxrq;

    @Schema(description = "zcdz")
    @ExcelProperty("注册地址")
    private String zcdz;

    @Schema(description = "yzbm")
    @ExcelProperty("邮政编码")
    private String yzbm;

    @Schema(description = "lxr")
    @ExcelProperty("联系人")
    private String lxr;

    @Schema(description = "lxdh")
    @ExcelProperty("联系电话")
    private String lxdh;

    @Schema(description = "ghlbDm")
    @ExcelProperty(value = "工会类别", converter = DictConvert.class)
    @DictFormat("sys_ghlb_type")
    private String ghlbDm;

    @Schema(description = "xtlbDm")
    @ExcelProperty(value = "系统类别", converter = DictConvert.class)
    @DictFormat("sys_xtlb_type")
    private String xtlbDm;

    @Schema(description = "hjfl1Dm")
    @ExcelProperty("户籍分类")
    private String hjfl1Dm;

    @Schema(description = "hjfl2Dm")
    private String hjfl2Dm;

    @Schema(description = "hjfl3Dm")
    private String hjfl3Dm;

    @Schema(description = "hjfl4Dm")
    private String hjfl4Dm;

    @Schema(description = "hjfl5Dm")
    private String hjfl5Dm;

    @Schema(description = "hjfl6Dm")
    private String hjfl6Dm;

    @Schema(description = "hjfl7Dm")
    private String hjfl7Dm;

    @Schema(description = "dclghbj")
    @ExcelProperty("待成立工会标记")
    private String dclghbj;

    @Schema(description = "xwyqbj")
    @ExcelProperty("小微企业标记")
    private String xwyqbj;

    @Schema(description = "sdghjgDm")
    private String sdghjgDm;

    @Schema(description = "clghbj")
    @ExcelProperty("成立工会标志")
    private String clghbj;

    @Schema(description = "clghrq")
    @ExcelProperty("成立工会日期")
    private LocalDateTime clghrq;

    @Schema(description = "jcghdm")
    @ExcelProperty("基层工会代码")
    private String jcghdm;

    @Schema(description = "jcghmc")
    @ExcelProperty("基层工会名称")
    private String jcghmc;

    @Schema(description = "jcghzh")
    @ExcelProperty("基层工会账户")
    private String jcghzh;

    @Schema(description = "jcghhm")
    @ExcelProperty("基层工会户名")
    private String jcghhm;

    @Schema(description = "jcghhh")
    @ExcelProperty("基层工会行号")
    private String jcghhh;

    @Schema(description = "jcghyh")
    @ExcelProperty("基层工会银行")
    private String jcghyh;

    @Schema(description = "bz")
    @ExcelProperty("备注")
    private String bz;

    @Schema(description = "jym")
    @ExcelProperty("校验码")
    private String jym;

    @Schema(description = "nsrsbh")
    @ExcelProperty("纳税人识别号")
    private String nsrsbh;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人")
    @ExcelProperty("更新人")
    private String updateBy;

    @Schema(description = "修改时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}
