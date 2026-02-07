package cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 税务入库 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GhJfResVO extends GhJfBaseVO {

    @Schema(description = "税票ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Integer ghjfId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
