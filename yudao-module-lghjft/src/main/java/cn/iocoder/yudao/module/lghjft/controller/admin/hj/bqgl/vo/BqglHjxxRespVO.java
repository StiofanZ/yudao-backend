package cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 标签户籍信息 Response VO")
@Data
public class BqglHjxxRespVO {

    @Schema(description = "登记序号", example = "10001")
    private String djxh;

    @Schema(description = "社会信用代码", example = "91110101123456789X")
    private String shxydm;

    @Schema(description = "纳税人名称", example = "某某公司")
    private String nsrmc;

    @Schema(description = "标签代码", example = "BQ001")
    private String bqDm;

    @Schema(description = "标签名称", example = "重点企业")
    private String bqMc;
}
