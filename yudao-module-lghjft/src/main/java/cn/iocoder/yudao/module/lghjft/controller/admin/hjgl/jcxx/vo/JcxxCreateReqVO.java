package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 户籍管理/基础信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JcxxCreateReqVO extends JcxxBaseVO {

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "登记序号不能为空")
    private String djxh;

}
