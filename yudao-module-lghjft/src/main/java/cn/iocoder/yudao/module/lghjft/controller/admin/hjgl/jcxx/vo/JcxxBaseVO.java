package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 户籍管理/基础信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class JcxxBaseVO {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @Schema(description = "主管税务机关代码")
    private String zgswjDm;

    @Schema(description = "主管税务机关名称")
    private String zgswjmc;

    @Schema(description = "科所分局代码")
    private String zgswskfjDm;

    @Schema(description = "科所分局名称")
    private String zgswskfjmc;

    @Schema(description = "税管员代码")
    private String ssglyDm;

    @Schema(description = "税管员姓名")
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

    @Schema(description = "总共人数")
    private BigDecimal zgrs;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "纳税人状态名称")
    private String nsrztmc;

    @Schema(description = "日期 (FZCRQ)")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime fzcrq;

    @Schema(description = "日期 (ZXRQ)")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
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

    @Schema(description = "户籍分类1")
    private String hjfl1Dm;

    @Schema(description = "户籍分类2")
    private String hjfl2Dm;

    @Schema(description = "户籍分类3")
    private String hjfl3Dm;

    @Schema(description = "户籍分类4")
    private String hjfl4Dm;

    @Schema(description = "户籍分类5")
    private String hjfl5Dm;

    @Schema(description = "2023小额缴费工会组织标志")
    private String hjfl6Dm;

    @Schema(description = "小微上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime hjfl7Dm;

    @Schema(description = "是否已建会缴纳筹备金")
    private String hjfl8Dm;

    @Schema(description = "小微企业标志")
    private String hjfl9Dm;

    @Schema(description = "户籍分类10")
    private String hjfl10Dm;

    @Schema(description = "属地工会机构代码")
    private String sdghjgDm;

    @Schema(description = "成立工会标志")
    private String clghbj;

    @Schema(description = "成立工会日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
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

    @Schema(description = "文件地址")
    private String fileUrl;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "税务数据同步时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime sjtbSj;

}
