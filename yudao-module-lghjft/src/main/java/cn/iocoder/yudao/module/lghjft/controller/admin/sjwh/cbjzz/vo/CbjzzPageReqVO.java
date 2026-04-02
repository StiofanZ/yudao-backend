package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjzz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 筹备金做账分页 Request VO")
@Data
public class CbjzzPageReqVO extends PageParam {

    @Schema(description = "hkpch")
    private String hkpch;

    @Schema(description = "sjdm")
    private String sjdm;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "dzbj")
    private String dzbj;
}
