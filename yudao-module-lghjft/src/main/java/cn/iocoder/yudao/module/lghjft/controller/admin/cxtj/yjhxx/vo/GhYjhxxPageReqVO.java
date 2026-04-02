package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 已建会信息分页 Request VO")
@Data
public class GhYjhxxPageReqVO extends PageParam {

    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "shxydm")
    private String shxydm;
}
