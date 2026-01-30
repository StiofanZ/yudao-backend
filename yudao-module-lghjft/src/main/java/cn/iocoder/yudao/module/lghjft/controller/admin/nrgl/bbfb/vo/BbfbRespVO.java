package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 版本发布 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BbfbRespVO extends BbfbBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "发布部门编号", example = "100")
    private Long deptId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
