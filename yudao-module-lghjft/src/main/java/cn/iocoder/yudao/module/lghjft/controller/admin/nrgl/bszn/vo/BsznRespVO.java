package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 办事指南 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BsznRespVO extends BsznBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "部门编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Long deptId;

    @Schema(description = "部门名称", example = "研发部")
    private String deptName;

    @Schema(description = "阅读量", example = "100")
    private Integer readCount;

    @Schema(description = "热度排名", example = "1")
    private Integer rank;

}
