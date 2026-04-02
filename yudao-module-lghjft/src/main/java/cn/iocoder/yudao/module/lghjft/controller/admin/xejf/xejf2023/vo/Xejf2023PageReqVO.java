package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额缴费明细分页 Request VO")
@Data
public class Xejf2023PageReqVO extends PageParam {
    @Schema(description = "工会经费ID")
    private Long ghjfId;
    @Schema(description = "缴费期间")
    private String jfqj;
    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "工会机构代码")
    private String deptId;
    @Schema(description = "征收品目代码")
    private String zspmDm;
    @Schema(description = "结算标记")
    private String[] jsbj;
}
