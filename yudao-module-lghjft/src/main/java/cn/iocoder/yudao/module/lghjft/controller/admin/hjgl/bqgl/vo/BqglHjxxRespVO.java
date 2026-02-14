package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Schema(description = "管理后台 - 标签关联户籍信息 Response VO")
@Data
@ToString(callSuper = true)
public class BqglHjxxRespVO {

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String djxh;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道源码")
    private String nsrmc;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "91110101123456789X")
    private String shxydm;

    @Schema(description = "法定代表人姓名", example = "张三")
    private String fddbrxm;

    @Schema(description = "标签ID", example = "BQ001")
    private String bqId;

    @Schema(description = "标签有效起期")
    private LocalDate yxqq;

    @Schema(description = "标签有效止期")
    private LocalDate yxqz;

}
