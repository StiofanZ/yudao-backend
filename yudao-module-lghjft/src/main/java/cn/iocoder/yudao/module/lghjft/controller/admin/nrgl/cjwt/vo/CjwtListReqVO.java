package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 常见问题列表 Request VO")
@Data
public class CjwtListReqVO {

    @Schema(description = "标题", example = "常见问题")
    private String title;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
