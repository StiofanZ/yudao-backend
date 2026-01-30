package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 办事指南分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BsznReqVO extends PageParam {

    @Schema(description = "事项名称", example = "办事指南")
    private String sxmc;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "业务分类(1:缴费管理,2:返拨管理,3:退费管理,4:缓交管理)", example = "1")
    private Integer ywfl;

    @Schema(description = "部门ID", example = "100")
    private Long deptId;

}

