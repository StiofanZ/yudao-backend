package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "在线咨询 FAQ 建议 Response VO")
@Data
public class ZxzxFaqResVO {

    @Schema(description = "常见问题 ID")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String nr;
}
