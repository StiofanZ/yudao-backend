package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 标签信息 Response VO")
@Data
@ToString(callSuper = true)
public class BqglRespVO {

    @Schema(description = "归类管理代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String id;

    @Schema(description = "标签归类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "重点关注")
    private String bqMc;

    @Schema(description = "部门ID", example = "100")
    private Long deptId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
