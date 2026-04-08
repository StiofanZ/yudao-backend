package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 筹备金统计分页 Request VO")
@Data
public class CbjmxPageReqVO extends PageParam {

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "年度")
    private String nd;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;
}
