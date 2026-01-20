package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 政策解读列表 Request VO")
@Data
public class ZcjdListReqVO {

    @Schema(description = "标题", example = "政策解读")
    private String title;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
