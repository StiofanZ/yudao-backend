package cn.iocoder.yudao.module.lghjft.controller.app.workflow.dwdbsq.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "App - 工会隶属关系调拨申请分页 Request VO")
@Data
public class WfDbsqAppPageReqVO extends PageParam {

    @Schema(description = "状态")
    private Integer zt;
}