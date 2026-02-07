package cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(description = "管理后台 - 身份信息新增/修改 Request VO")
@Data
public class SfxxSaveReqVO {

    @Schema(description = "ID", example = "1024")
    private Long id;

    @Schema(description = "登录账号ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "登录账号ID不能为空")
    private Long dlzhId;

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "登记序号不能为空")
    @Length(max = 20, message = "登记序号长度不能超过20个字符")
    private String djxh;

    @Schema(description = "身份类型（01:法定代表人,02:财务负责人）", requiredMode = Schema.RequiredMode.REQUIRED, example = "01")
    @NotEmpty(message = "身份类型不能为空")
    @Length(max = 2, message = "身份类型长度不能超过2个字符")
    private String sflx;

    @Schema(description = "工会类型（01-08）", example = "01")
    @Length(max = 2, message = "工会类型长度不能超过2个字符")
    private String ghlx;

    @Schema(description = "权限类型（01:管理员,02:一般人）", requiredMode = Schema.RequiredMode.REQUIRED, example = "01")
    @NotEmpty(message = "权限类型不能为空")
    @Length(max = 2, message = "权限类型长度不能超过2个字符")
    private String qxlx;

    @Schema(description = "授权原因", example = "0")
    private String sqyy;

    @Schema(description = "解绑原因", example = "0")
    private String jbyy;

    @Schema(description = "部门编号", example = "100")
    private Long deptId;

    @Schema(description = "状态 0:待审核 1:已审核", example = "0")
    private Integer status;

}
