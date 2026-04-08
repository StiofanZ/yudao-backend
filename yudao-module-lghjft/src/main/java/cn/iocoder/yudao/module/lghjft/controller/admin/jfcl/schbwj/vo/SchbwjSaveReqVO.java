package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * v1 JfclSchbwj request for POST / (updateGhjfhb):
 * jsrqStart, jsrqEnd, hkrq, bclbj
 */
@Schema(description = "管理后台 - 生成划拨数据 Request VO")
@Data
public class SchbwjSaveReqVO {

    @Schema(description = "结算日期-起")
    private String jsrqStart;

    @Schema(description = "结算日期-止")
    private String jsrqEnd;

    @Schema(description = "划拨委托日期")
    private String hkrq;

    @Schema(description = "补处理标记")
    private String bclbj;
}
