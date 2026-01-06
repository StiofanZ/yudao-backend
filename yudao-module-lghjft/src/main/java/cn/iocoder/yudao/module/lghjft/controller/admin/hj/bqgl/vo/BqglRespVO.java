package cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 标签管理 Response VO")
@Data
public class BqglRespVO {

    @Schema(description = "标签代码", example = "BQ001")
    private String bqDm;

    @Schema(description = "标签名称", example = "重要标签")
    private String bqMc;

    @Schema(description = "部门ID", example = "100")
    private Long deptId;

    @Schema(description = "部门名称", example = "研发部")
    private String deptMc;

    @Schema(description = "层级", example = "1")
    private Integer level;
}
