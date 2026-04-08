package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 经费结算分页 Request VO")
@Data
public class JfjsPageReqVO extends PageParam {

    @Schema(description = "税票ID")
    private String spuuid;

    @Schema(description = "入库日期-起")
    private String rkrqStart;

    @Schema(description = "入库日期-止")
    private String rkrqEnd;

    @Schema(description = "结算日期")
    private String jsrq;
}
