package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分月代收情况分页 Request VO")
@Data
public class FydsqkPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;
}
