package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 银行拨付汇总监控记录新增/修改 Request VO")
public class GhHkxxYhbfhzJkjlSaveReqVO {

    @Schema(description = "主键ID", example = "1024")
    private Long yhbfhzJkjlId;

    @NotBlank(message = "银行接口名称不能为空")
    @Schema(description = "银行接口名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "招行代发")
    private String jkmc;

    @NotNull(message = "汇总信息ID不能为空")
    @Schema(description = "汇总信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    private Long fkId;

    @Schema(description = "请求报文", example = "{}")
    private String qqbw;

    @Schema(description = "响应报文", example = "{}")
    private String xybw;

    @Schema(description = "请求结果：成功-S，异常-E", example = "S")
    private String qqzt;

    @Schema(description = "请求信息", example = "成功")
    private String qqjgxx;

}