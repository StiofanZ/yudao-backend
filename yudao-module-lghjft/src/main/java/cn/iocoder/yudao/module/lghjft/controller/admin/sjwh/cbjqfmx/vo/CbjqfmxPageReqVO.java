package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqfmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 筹备金全返明细分页 Request VO")
@Data
public class CbjqfmxPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "zgswjDm")
    private String zgswjDm;

    @Schema(description = "zspmDm")
    private String zspmDm;
}
