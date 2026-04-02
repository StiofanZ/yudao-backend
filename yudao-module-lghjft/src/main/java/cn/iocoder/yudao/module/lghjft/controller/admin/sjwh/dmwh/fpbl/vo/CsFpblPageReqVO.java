package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分配比例分页 Request VO")
@Data
public class CsFpblPageReqVO extends PageParam {

    @Schema(description = "描述")
    private String ms;

    @Schema(description = "条件")
    private String tj;

    @Schema(description = "默认标志")
    private String mrbz;

}
