package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * V1 Ghjfhbjs equivalent - used for dqdssj/dqzldssj import requests
 */
@Schema(description = "管理后台 - 代收数据入库 Request VO")
@Data
public class DqdssjSaveReqVO {

    @Schema(description = "税票ID")
    private String spuuid;

    @Schema(description = "入库日期-起")
    private Date rkrqStart;

    @Schema(description = "入库日期-止")
    private Date rkrqEnd;

    @Schema(description = "增量标记")
    private String zlbj;
}
