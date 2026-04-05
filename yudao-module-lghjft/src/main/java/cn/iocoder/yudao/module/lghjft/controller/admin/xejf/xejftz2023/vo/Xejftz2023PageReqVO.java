package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额缴费统计分页 Request VO")
@Data
public class Xejftz2023PageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "zspmDm")
    private String zspmDm;
}
