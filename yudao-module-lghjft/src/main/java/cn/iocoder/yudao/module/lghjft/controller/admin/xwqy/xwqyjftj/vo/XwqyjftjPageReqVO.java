package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小微经费统计查询 Request VO")
@Data
public class XwqyjftjPageReqVO extends PageParam {

    @Schema(description = "单位代码")
    private String dwdm;

    @Schema(description = "部门ID")
    private String deptId;

    @Schema(description = "纳税人名称")
    private String nsrmc;
}
