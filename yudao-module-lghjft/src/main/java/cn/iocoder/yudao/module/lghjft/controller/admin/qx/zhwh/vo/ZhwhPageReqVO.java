package cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 账户维护申请分页 Request VO")
@Data
public class ZhwhPageReqVO extends PageParam {

    @Schema(description = "申请编号")
    private String applyNo;

    @Schema(description = "登录账号 ID")
    private Long dlzhId;

    @Schema(description = "部门 ID")
    private Long deptId;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "单位名称")
    private String dwmc;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "同步状态")
    private Integer syncStatus;
}
