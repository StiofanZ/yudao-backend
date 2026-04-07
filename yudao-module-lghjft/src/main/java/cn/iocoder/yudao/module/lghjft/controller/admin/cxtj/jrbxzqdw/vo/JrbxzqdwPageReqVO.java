package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 金融保险证券单位分页 Request VO")
@Data
public class JrbxzqdwPageReqVO extends PageParam {

    @Schema(description = "单位代码")
    private String dwdm;

    @Schema(description = "部门 ID（V1: deptId -> DWDM / gh_hj.DEPT_ID）")
    private String deptId;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人识别号")
    private String nsrsbh;

    @Schema(description = "纳税人名称（LIKE）")
    private String nsrmc;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "中国金融工会在甘单位标志")
    private String zgjrghzgdwbz;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "登记注册类型代码")
    private String djzclxDm;

    @Schema(description = "登记注册类型名称")
    private String djzclxmc;

    @Schema(description = "生产经营地址")
    private String scjydz;

    @Schema(description = "行政区划代码")
    private String scjydzxzqhszDm;

    @Schema(description = "行政区划名称")
    private String xzqhmc;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "行业代码")
    private String hyDm;

    @Schema(description = "行业名称")
    private String hymc;

    @Schema(description = "登记机关代码")
    private String djjgDm;

    @Schema(description = "登记机关名称")
    private String djjgmc;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "主管税务局名称")
    private String zgswjmc;

    @Schema(description = "主管税务所代码")
    private String zgswskfjDm;

    @Schema(description = "科所分局名称")
    private String zgksfjmc;

    @Schema(description = "财务负责人姓名")
    private String cwfzrxm;

    @Schema(description = "财务负责人固定电话")
    private String cwfzrgddh;

    @Schema(description = "财务负责人移动电话")
    private String cwfzryddh;

    @Schema(description = "从业人数")
    private BigDecimal cyrs;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "核实结果")
    private String hsjg;

    @Schema(description = "税率")
    private String sl;
}
