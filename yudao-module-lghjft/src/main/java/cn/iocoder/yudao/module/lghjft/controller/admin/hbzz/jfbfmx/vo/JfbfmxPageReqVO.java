package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfbfmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 经费拨付明细分页 Request VO")
@Data
public class JfbfmxPageReqVO extends PageParam {

    @Schema(description = "hkpch")
    private String hkpch;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "zgswjDm")
    private String zgswjDm;

    @Schema(description = "jsbj")
    private String jsbj;

    @Schema(description = "zspmDm")
    private String zspmDm;

    @Schema(description = "zh")
    private String zh;
}
