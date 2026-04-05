package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 基层经费到账分页 Request VO")
@Data
public class JcjfzzPageReqVO extends PageParam {

    @Schema(description = "hkpch")
    private String hkpch;

    @Schema(description = "lx")
    private String lx;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "dzbj")
    private String dzbj;
}
