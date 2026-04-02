package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 录入退回凭证分页 Request VO")
@Data
public class LrthpzPageReqVO extends PageParam {

    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "账号")
    private String zh;
    @Schema(description = "户名")
    private String hm;
    @Schema(description = "工会机构")
    private String deptId;
}
