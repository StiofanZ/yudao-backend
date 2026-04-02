package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 缴费排行分页 Request VO")
@Data
public class TopPageReqVO extends PageParam {

    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "dwmc")
    private String dwmc;
}
