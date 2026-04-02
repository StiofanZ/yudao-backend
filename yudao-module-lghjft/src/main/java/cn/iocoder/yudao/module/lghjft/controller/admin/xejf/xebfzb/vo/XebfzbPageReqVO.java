package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额拨付占比分页 Request VO")
@Data
public class XebfzbPageReqVO extends PageParam {
    @Schema(description = "小额年度")
    private String xend;
    @Schema(description = "市级/产业工会")
    private String sjdm;
    @Schema(description = "主管工会")
    private String deptId;
}
