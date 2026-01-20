package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 办事指南列表 Request VO")
@Data
public class BsznListReqVO extends PageParam {

    @Schema(description = "事项名称", example = "办事指南")
    private String sxmc;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
