package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 到户经费台账分页 Request VO")
@Data
public class DhjftzPageReqVO extends PageParam {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "结算标记")
    private String jsbj;
}
