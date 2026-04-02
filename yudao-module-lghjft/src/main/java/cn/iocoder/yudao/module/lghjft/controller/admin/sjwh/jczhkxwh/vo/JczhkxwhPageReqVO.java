package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 基层账户空需维护分页 Request VO")
@Data
public class JczhkxwhPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "shxydm")
    private String shxydm;

    @Schema(description = "clghbj")
    private String clghbj;
}
