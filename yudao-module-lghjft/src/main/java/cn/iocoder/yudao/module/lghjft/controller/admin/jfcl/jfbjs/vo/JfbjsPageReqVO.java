package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 经费补结算分页 Request VO")
@Data
public class JfbjsPageReqVO extends PageParam {

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "征收品目代码")
    private String zspmDm;
    @Schema(description = "入库日期-起")
    private LocalDateTime rkrqStart;
    @Schema(description = "入库日期-止")
    private LocalDateTime rkrqEnd;
}
