package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "管理后台 - 户籍管理新增/修改 Request VO")
@Data
public class GhHjSaveReqVO {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "部门ID不能为空")
    private String deptId;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人识别号")
    private String nsrsbh;

    @Schema(description = "缴费单位名称")
    private String nsrmc;

    @Schema(description = "缴费单位简称")
    private String nsrjc;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

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

    @Schema(description = "组织机构代码")
    private String zzjglxDm;

    @Schema(description = "组织机构名称")
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

    @Schema(description = "职工人数")
    private BigDecimal zgrs;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "纳税人状态名称")
    private String nsrztmc;

    @Schema(description = "发证成立日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fzcrq;

    @Schema(description = "注销日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date zxrq;

    @Schema(description = "注册地址")
    private String zcdz;

    @Schema(description = "邮政编码")
    private String yzbm;

    @Schema(description = "联系人")
    private String lxr;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "工会类别")
    private String ghlbDm;

    @Schema(description = "系统类别")
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

    @Schema(description = "户籍分类6")
    private String hjfl6Dm;

    @Schema(description = "户籍分类7")
    private String hjfl7Dm;

    @Schema(description = "建会缴纳筹备金标志")
    private String hjfl8Dm;

    @Schema(description = "户籍分类9")
    private String hjfl9Dm;

    @Schema(description = "属地工会机构代码")
    private String sdghjgDm;

    @Schema(description = "成立工会标志")
    private String clghbj;

    @Schema(description = "成立工会日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date clghrq;

    @Schema(description = "基层工会代码")
    private String jcghdm;

    @Schema(description = "基层工会名称")
    private String jcghmc;

    @Schema(description = "基层工会账号")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会行号")
    private String jcghhh;

    @Schema(description = "基层工会银行")
    private String jcghyh;

    @Schema(description = "税务数据同步时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sjtbSj;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "校验码")
    private String jym;
}
