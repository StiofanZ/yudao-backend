package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 征收未主管信息确认 VO")
@Data
public class ZswzgdwQrVO {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "确认结果代码")
    private String qrjgDm;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
