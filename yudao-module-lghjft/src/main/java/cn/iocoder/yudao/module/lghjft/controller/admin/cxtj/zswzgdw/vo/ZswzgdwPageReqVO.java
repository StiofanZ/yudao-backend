package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 征收未主管单位分页 Request VO")
@Data
public class ZswzgdwPageReqVO extends PageParam {

    @Schema(description = "dwdm")
    private String dwdm;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "djxh")
    private String djxh;
}
