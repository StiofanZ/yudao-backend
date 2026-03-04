package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 应代收单位新增/修改 Request VO")
@Data
public class JhdwydsSaveReqVO {
    @Schema(description = "建会单位代收ID")
    private Integer jhdwId;
    @Schema(description = "工会机构", requiredMode = Schema.RequiredMode.REQUIRED, example = "31326")
    @NotEmpty(message = "工会机构不能为空")
    private String deptId;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "纳税人名称不能为空")
    private String nsrmc;

    @Schema(description = "工会法人统一社会信用代码")
    private String ghshxydm;

    @Schema(description = "工会组织名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "工会组织名称不能为空")
    private String ghmc;

    @Schema(description = "工会联系人")
    private String ghlxr;

    @Schema(description = "工会联系电话")
    private String ghlxdh;

    @Schema(description = "成立工会标志（0 筹备建会 1 已建会）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "成立工会标志（0 筹备建会 1 已建会）不能为空")
    private String clghbj;

    @Schema(description = "成立工会日期（筹备金建会日期）")
    private String clghrq;

    @Schema(description = "工会状态代码")
    private String ghztDm;

}