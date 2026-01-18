package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Schema(description = "管理后台 - 通知公告分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class TzggPageReqVO extends PageParam {

    @Schema(description = "通知公告名称，模糊匹配", example = "芋道")
    private String title;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

    @Schema(description = "部门ID列表，用于权限过滤")
    private List<Long> deptIds;

}
