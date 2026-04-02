package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分年各级分成情况分页 Request VO")
@Data
public class NdrwwcPageReqVO extends PageParam {

    @Schema(description = "nd")
    private String nd;
    @Schema(description = "dwdm")
    private String dwdm;
    @Schema(description = "dwmc")
    private String dwmc;
}
