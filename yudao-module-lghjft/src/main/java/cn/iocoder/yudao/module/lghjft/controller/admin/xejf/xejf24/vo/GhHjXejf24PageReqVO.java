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
    @Schema(description = "财务联系人")
    private String lxr;
    @Schema(description = "联系电话")
    private String lxdh;
    @Schema(description = "工会类别")
    private String ghlbDm;
    @Schema(description = "系统类别")
    private String xtlbDm;
    @Schema(description = "24年小额原始情况")
    private String hjfl4Dm;
    @Schema(description = "24小额确认情况")
    private String hjfl5Dm;
    @Schema(description = "24小额类型")
    private String hjfl6Dm;
    @Schema(description = "基层工会账户")
    private String jcghzh;
    @Schema(description = "基层工会行号")
    private String jcghhh;
}
