package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 小额代管经费做账更新 Request VO")
@Data
public class XedgjfSaveReqVO {

    @Schema(description = "划款信息ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long hkxxId;

    @Schema(description = "确认子表")
    private List<XedgjfQrszItemVO> qrszList;
}
