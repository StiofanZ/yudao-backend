package cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxResVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigInteger;

@Schema(description = "管理后台 - 纳税人信息 Response VO")
@Data
public class NsrxxResVO {

    @Schema(description = "纳税人识别号", requiredMode = Schema.RequiredMode.REQUIRED, example = "91110108551385082Q")
    private String nsrsbh;

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger djxh;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "某某公司")
    private String nsrmc;

    @Schema(description = "社会信用代码", example = "91110108551385082Q")
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
}
