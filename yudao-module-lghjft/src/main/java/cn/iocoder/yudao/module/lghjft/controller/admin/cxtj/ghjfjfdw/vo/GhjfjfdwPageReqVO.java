package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 近三年缴费情况分页 Request VO")
@Data
public class GhjfjfdwPageReqVO extends PageParam {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构")
    private String deptId;

    @Schema(description = "税务机关代码")
    private String zsswjgDm;
}
