package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 首页汇总信息分月分页 Request VO")
@Data
public class SyhzxxfyPageReqVO extends PageParam {

    @Schema(description = "nd")
    private String nd;
    @Schema(description = "deptId")
    private String deptId;
}
