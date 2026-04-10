package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 读取代收数据分页 Request VO")
@Data
public class DqdssjPageReqVO extends PageParam {

    @Schema(description = "税票ID")
    private String spuuid;

    @Schema(description = "增量标记")
    private String zlbj;

    @Schema(description = "入库日期-起")
    private String rkrqStart;

    @Schema(description = "入库日期-止")
    private String rkrqEnd;
}
