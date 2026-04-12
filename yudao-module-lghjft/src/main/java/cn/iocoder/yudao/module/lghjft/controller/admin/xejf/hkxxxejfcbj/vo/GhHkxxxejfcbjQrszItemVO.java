package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 小额筹备金做账确认子项 VO")
@Data
public class GhHkxxxejfcbjQrszItemVO {

    @Schema(description = "划款信息ID")
    private Long hkxxId;

    @Schema(description = "划款信息校验码")
    private String ghHkxxJym;

    @Schema(description = "到账标记")
    private String dzbj;

    @Schema(description = "到账日期")
    private LocalDateTime qrrq;

    @Schema(description = "做账凭证号")
    private String yhhdh;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
