package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 首页汇总信息分页 Request VO")
@Data
public class SyhzxxPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;
}
