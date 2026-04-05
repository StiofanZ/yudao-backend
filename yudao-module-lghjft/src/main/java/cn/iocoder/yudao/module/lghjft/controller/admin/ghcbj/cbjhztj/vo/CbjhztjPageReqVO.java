package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 筹备金汇总统计分页 Request VO")
@Data
public class CbjhztjPageReqVO extends PageParam {

    @Schema(description = "nd")
    private String nd;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "nsrmc")
    private String nsrmc;
}
