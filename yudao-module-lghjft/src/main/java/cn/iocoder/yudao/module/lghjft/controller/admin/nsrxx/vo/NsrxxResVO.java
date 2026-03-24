package cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxResVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Schema(description = "管理后台 - 纳税人信息 Response VO")
@Data
public class NsrxxResVO {

    @Schema(description = "纳税人识别号", requiredMode = Schema.RequiredMode.REQUIRED, example = "91110108551385082Q")
    private String nsrsbh;

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @JsonSerialize(using = ToStringSerializer.class)
    private String djxh;

   @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "某某公司")
    private String nsrmc;

    @Schema(description = "社会信用 代码", example = "91110108551385082Q")
    private String shxydm;

    @Schema(description = "法人代表", example = "张三")
    private String frdb;

    @Schema(description = "注册地址", example = "北京市海淀区")
    private String zcdz;

    @Schema(description = "联系电话", example = "010-12345678")
    private String lxdh;

    @Schema(description = "法定代表人姓名", example = "张三")
    private String fddbrxm;

    @Schema(description = "法定代表人移动电话", example = "13800000000")
    private String fddbryddh;

    @Schema(description = "财务负责人姓名", example = "李四")
    private String cwfzrxm;

    @Schema(description = "财务负责人移动电话", example = "13900000000")
    private String cwfzryddh;

    @Schema(description = "已存在的身份信息")
    private SfxxResVO sfxx;

    @Schema(description = "部门编号")
    private Long deptId;
    // ========== 补充表单所需的新字段（核心） ==========
    @Schema(description = "主管税务部门全称", example = "国家税务总局北京市海淀区税务局")
    private String zgswjmc;

    @Schema(description = "生产经营地址（备选缴费地址）", example = "北京市海淀区XX产业园")
    private String scjydz;

    @Schema(description = "职工总人数（从业人数）", example = "50")
    private BigDecimal zgrs;

    @Schema(description = "工会/退款账户户名", example = "某某公司工会")
    private String zhhm;

    @Schema(description = "工会/退款银行账号", example = "6222080200012345678")
    private String yhzh;

    @Schema(description = "开户银行全称", example = "中国工商银行股份有限公司北京海淀支行")
    private String khfymc;

    @Schema(description = "开户银行网点代码/行号（联行号）", example = "102100002010")
    private String khyhhh;


}
