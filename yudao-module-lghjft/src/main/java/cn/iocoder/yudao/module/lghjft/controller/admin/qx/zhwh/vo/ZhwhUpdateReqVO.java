package cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 更新账户维护申请 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ZhwhUpdateReqVO extends ZhwhBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号不能为空")
    private Long id;
}
