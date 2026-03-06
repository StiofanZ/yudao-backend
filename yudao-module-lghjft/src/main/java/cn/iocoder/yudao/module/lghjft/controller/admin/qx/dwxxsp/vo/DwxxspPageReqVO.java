package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 单位信息审批分页 Request VO")
@Data
public class DwxxspPageReqVO extends PageParam {

    @Schema(description = "业务类型 ACCOUNT_CHANGE / IDENTITY")
    private String businessType;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "关键字")
    private String keyword;
}
