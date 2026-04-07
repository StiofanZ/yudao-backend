package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分月代收情况分页 Request VO")
@Data
public class FydsqkPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "入库日期-开始（对应DSYF过滤）")
    private String beginRkrq;
    @Schema(description = "入库日期-结束（对应DSYF过滤）")
    private String endRkrq;
}
