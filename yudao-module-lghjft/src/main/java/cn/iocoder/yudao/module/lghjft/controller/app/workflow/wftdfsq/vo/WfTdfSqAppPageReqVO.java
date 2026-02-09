package cn.iocoder.yudao.module.lghjft.controller.app.workflow.wftdfsq.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "APP - 工会经费退抵费申请分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WfTdfSqAppPageReqVO extends PageParam {

    @Schema(description = "状态（可选）")
    private Integer status;

}