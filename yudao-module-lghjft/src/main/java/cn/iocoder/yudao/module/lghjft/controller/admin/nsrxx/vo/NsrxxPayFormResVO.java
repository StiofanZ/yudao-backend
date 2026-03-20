package cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigInteger;

@Data
@Schema(description = "工会汇总缴费申请表单查询返回数据")
public class NsrxxPayFormResVO {

    @Schema(description = "社会信用代码", example = "91620104712797011F", requiredMode = Schema.RequiredMode.REQUIRED)
    private String shxydm;

    @Schema(description = "登记序号")
    private BigInteger djxh;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "科所分局税务局代码")
    private String zgswskfjDm;

    @Schema(description = "缴费单位全称（与公章一致）", example = "华能兰州西固热电有限公司", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nsrmc;

    @Schema(description = "主管税务部门全称", example = "国家税务总局兰州市西固区税务局")
    private String zgswjmc;

    @Schema(description = "缴费单位地址（注册地址/实际经营地址）", example = "甘肃省兰州市西固区古浪路78号")
    private String zcdz;

    @Schema(description = "工会法人登记证件号码（工会法人资格登记证编号）", example = "GSHY6201040012345")
    private String jcghdm;

    @Schema(description = "缴费单位工会全称（与工会公章一致）", example = "华能兰州西固热电有限公司工会委员会")
    private String jcghmc;

    @Schema(description = "职工总人数", example = "100")
    private Integer zgrs;

    @Schema(description = "工会会员数", example = "85")
    private Integer ghhyts;

    @Schema(description = "工会负责人（工会主席/负责人姓名）", example = "张三")
    private String ghfzr;
    @Schema(description = "财务负责人", example = "张三")
    private  String lxr;
    @Schema(description = "联系电话（手机/座机）", example = "13919376655")
    private String lxdh;

    @Schema(description = "工会账户账号（工会专用银行账户账号）", example = "62001380013050359262")
    private String jcghzh;
    @Schema(description = "开户银行名称（工会账户开户银行全称）", example = "中国工商银行股份有限公司兰州西固支行")
    private String jcghyh;

    @Schema(description = "工会账户户名（与开户行一致）", example = "华能兰州西固热电有限公司工会委员会")
    private String jcghhm;

    @Schema(description = "开户银行网点代码/行号（联行号）", example = "102100002010")
    private String jcghhh;
    @Schema(description = "纳税人识别号")
    private String nsrsbh;
}