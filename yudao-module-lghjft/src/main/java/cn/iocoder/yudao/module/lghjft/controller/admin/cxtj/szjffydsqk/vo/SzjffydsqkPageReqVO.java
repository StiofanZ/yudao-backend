package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分月代收情况(入库)分页 Request VO")
@Data
public class SzjffydsqkPageReqVO extends PageParam {

    @Schema(description = "dwdm")
    private String dwdm;
    @Schema(description = "dwmc")
    private String dwmc;
}
