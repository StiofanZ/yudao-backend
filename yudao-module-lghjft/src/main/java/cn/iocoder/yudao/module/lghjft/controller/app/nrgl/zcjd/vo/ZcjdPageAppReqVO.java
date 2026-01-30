package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.zcjd.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户 App - 政策解读列表 Request VO")
@Data
public class ZcjdPageAppReqVO extends ZcjdReqVO {

    @Schema(description = "发布部门", example = "1")
    private Integer fbbm;

    @Schema(description = "标题", example = "1")
    private String title;
}
