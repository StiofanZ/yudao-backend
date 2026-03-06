package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "在线咨询分页 Request VO")
@Data
public class ZxzxPageReqVO extends PageParam {

    @Schema(description = "咨询单号")
    private String consultNo;

    @Schema(description = "咨询人")
    private String yhmc;

    @Schema(description = "咨询内容")
    private String nr;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "状态")
    private Integer zt;

    @Schema(description = "是否管理端视图")
    private Boolean isAdminView;
}
