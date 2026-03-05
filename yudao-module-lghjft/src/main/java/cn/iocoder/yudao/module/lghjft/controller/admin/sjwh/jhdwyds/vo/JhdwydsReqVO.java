package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 应代收单位 Request VO")
@Data
public class JhdwydsReqVO {

    @Schema(description = "工会机构", example = "31326")
    private String deptId;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "工会法人统一社会信用代码")
    private String ghshxydm;

    @Schema(description = "工会组织名称")
    private String ghmc;

    @Schema(description = "工会联系人")
    private String ghlxr;

    @Schema(description = "工会联系电话")
    private String ghlxdh;

    @Schema(description = "成立工会标志（0 筹备建会 1 已建会）")
    private String clghbj;

    @Schema(description = "成立工会日期（筹备金建会日期）")
    private String clghrq;

    @Schema(description = "工会状态代码")
    private String ghztDm;

}