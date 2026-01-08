package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 收款国库分页 Request VO")
@Data
public class SkgkPageReqVO extends PageParam {

    @Schema(description = "税款国库id")
    private Integer gkId;

    @Schema(description = "税款国库代码")
    private String skgkDm;

    @Schema(description = "税款国库名称")
    private String skgkmc;

    @Schema(description = "税款国库简称")
    private String skgkjc;

    @Schema(description = "行政区划代码")
    private String xzqhDm;

}