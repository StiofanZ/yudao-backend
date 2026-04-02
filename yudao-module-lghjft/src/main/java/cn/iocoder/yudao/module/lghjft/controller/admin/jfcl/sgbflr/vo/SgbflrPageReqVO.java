package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 手工拨付录入分页 Request VO")
@Data
public class SgbflrPageReqVO extends PageParam {

    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "账号")
    private String zh;
    @Schema(description = "户名")
    private String hm;
    @Schema(description = "工会机构")
    private String deptId;
}
