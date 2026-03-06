package cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhzjnsq.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "APP - 汇总缴纳申请分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GhWfJfhzjnsqAppPageReqVO extends PageParam {

    @Schema(description = "状态")
    private Integer zt;
}
