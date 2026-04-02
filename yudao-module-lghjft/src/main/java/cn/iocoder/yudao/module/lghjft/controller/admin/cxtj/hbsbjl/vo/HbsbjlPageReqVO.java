package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 划拨失败记录分页 Request VO")
@Data
public class HbsbjlPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;
}
