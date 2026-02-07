package cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 身份信息分页 Request VO")
@Data
public class SfxxPageReqVO extends PageParam {

    @Schema(description = "登录账号ID", example = "1024")
    private Long dlzhId;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "身份类型（01:法定代表人,02:财务负责人）", example = "01")
    private String sflx;

    @Schema(description = "工会类型（01-08）", example = "01")
    private String ghlx;

    @Schema(description = "权限类型（01:管理员,02:一般人）", example = "01")
    private String qxlx;

    @Schema(description = "部门编号", example = "100")
    private Long deptId;

    @Schema(description = "状态 0:待审核 1:已审核", example = "0")
    private Integer status;

}
