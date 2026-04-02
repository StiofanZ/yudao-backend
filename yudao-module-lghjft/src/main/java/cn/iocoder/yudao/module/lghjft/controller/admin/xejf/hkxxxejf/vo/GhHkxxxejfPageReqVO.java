package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额拨付记账凭证分页 Request VO")
@Data
public class GhHkxxxejfPageReqVO extends PageParam {
    @Schema(description = "划款信息ID")
    private Long hkxxId;
    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "缴费期间")
    private Long jfqj;
    @Schema(description = "类型")
    private String lx;
    @Schema(description = "工会机构")
    private String deptId;
    @Schema(description = "退回标记")
    private String thbj;
}
