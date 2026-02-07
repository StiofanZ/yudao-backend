package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 基层账户空需维护对象更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GhHjUpdateReqVO extends GhHjBaseVO {

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "登记序号不能为空")
    private String djxh;

}
