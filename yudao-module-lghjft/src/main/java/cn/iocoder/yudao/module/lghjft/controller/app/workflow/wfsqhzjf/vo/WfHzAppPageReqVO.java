package cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfsqhzjf.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "APP - 工会经费汇总缴纳申请分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WfHzAppPageReqVO extends PageParam {

    @Schema(description = "状态（可选）")
    private Integer status; // 支持按状态筛选（如：审批中、已完成）

}