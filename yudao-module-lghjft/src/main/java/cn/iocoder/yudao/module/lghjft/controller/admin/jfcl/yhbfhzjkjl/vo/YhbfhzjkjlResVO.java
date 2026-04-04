package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 银行拨付汇总监控记录 Response VO")
@Data
public class YhbfhzjkjlResVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long yhbfhzJkjlId;

    @Schema(description = "银行接口名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "招行代发")
    private String jkmc;

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

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新人")
    private String updateBy;

}
