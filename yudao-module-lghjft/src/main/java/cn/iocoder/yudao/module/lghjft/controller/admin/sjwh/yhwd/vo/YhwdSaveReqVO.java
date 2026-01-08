package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 银行网点新增/修改 Request VO")
@Data
public class YhwdSaveReqVO {

    @Schema(description = "银行行别代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "银行行别代码不能为空")
    private String yhhbDm;

    @Schema(description = "网点代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网点代码不能为空")
    private String yhwdDm;

    @Schema(description = "网点名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网点名称不能为空")
    private String yhwdmc;

    @Schema(description = "网点简称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网点简称不能为空")
    private String yhwdjc;

    @Schema(description = "网点行号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网点行号不能为空")
    private String wdhh;

    @Schema(description = "清算行号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "清算行号不能为空")
    private String qshh;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String xzqhDm;

    @Schema(description = "顺序号")
    private Integer sxh;

    @Schema(description = "有效期止")
    private LocalDateTime yxqz;

    @Schema(description = "网点地址")
    private String wddz;

    @Schema(description = "网点电话")
    private String wddh;

    @Schema(description = "银行行别ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11421")
    private Long yhhbId;

}