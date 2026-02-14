package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 户籍管理/基础信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JcxxRespVO extends JcxxBaseVO {

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String djxh;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
