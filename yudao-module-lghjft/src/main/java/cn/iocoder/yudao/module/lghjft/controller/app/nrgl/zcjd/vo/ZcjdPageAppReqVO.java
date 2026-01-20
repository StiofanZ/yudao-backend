package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.zcjd.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdListReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户 App - 政策解读列表 Request VO")
@Data
public class ZcjdPageAppReqVO extends ZcjdListReqVO {

    @Schema(description = "发布部门", example = "1")
    private Integer fbbm;


}
