package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 划拨失败已修改分页 Request VO")
@Data
public class HbsbjlyxgPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "hkpch")
    private String hkpch;
    @Schema(description = "hm")
    private String hm;
    @Schema(description = "xhm")
    private String xhm;
}
