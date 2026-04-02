package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 近一周缴费分页 Request VO")
@Data
public class DpzsjyzjfmxPageReqVO extends PageParam {

    @Schema(description = "djxh")
    private String djxh;
}
