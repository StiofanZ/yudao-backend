package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 24年小额确认分页 Request VO")
@Data
public class GhHjXejf24PageReqVO extends PageParam {
    @Schema(description = "工会机构代码")
    private String deptId;
    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "24小额确认情况")
    private String hjfl5Dm;
    @Schema(description = "24小额类型")
    private String hjfl6Dm;
}
