package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 常见问题分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CjwtReqVO extends PageParam {

    @Schema(description = "标题", example = "常见问题")
    private String title;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "问题分类")
    private String wtfl;

    @Schema(description = "部门ID", example = "1024")
    private Long deptId;
}

