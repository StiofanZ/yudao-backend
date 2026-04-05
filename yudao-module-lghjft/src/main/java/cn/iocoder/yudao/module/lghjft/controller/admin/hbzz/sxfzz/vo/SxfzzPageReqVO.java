package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 手续费做账分页 Request VO")
@Data
public class SxfzzPageReqVO extends PageParam {

    @Schema(description = "hkpch")
    private String hkpch;

    @Schema(description = "lx")
    private String lx;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "dzbj")
    private String dzbj;
}
