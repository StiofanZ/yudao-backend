package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.nsrxx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 纳税人信息 Response VO")
@Data
public class NsrxxRespVO {

    @Schema(description = "纳税人识别号")
    private String nsrsbh;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "法定代表人姓名")
    private String fddbrxm;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "是否存在于工会户籍")
    private Boolean existsInHj;

    @Schema(description = "维护部门")
    private String deptName;
}
