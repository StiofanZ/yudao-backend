package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 版本发布列表 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BbfbListReqVO extends PageParam {

    @Schema(description = "标题", example = "版本更新")
    private String title;

    @Schema(description = "版本号", example = "v1.0.0")
    private String version;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
