package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额缴费拨付台账分页 Request VO")
@Data
public class XebfPageReqVO extends PageParam {
    @Schema(description = "工会经费ID")
    private Long ghjfId;
    @Schema(description = "缴费期间")
    private String jfqj;
    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "工会机构")
    private String deptId;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "返拨标记")
    private String fbbj;
}
