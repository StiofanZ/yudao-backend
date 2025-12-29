package cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigInteger;

@Schema(description = "管理后台 - 纳税人信息 Response VO")
@Data
public class NsrxxRespVO {

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
}
