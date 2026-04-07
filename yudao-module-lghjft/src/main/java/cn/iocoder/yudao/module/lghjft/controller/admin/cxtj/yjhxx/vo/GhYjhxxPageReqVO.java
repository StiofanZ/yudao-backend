package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 已建会信息分页 Request VO")
@Data
public class GhYjhxxPageReqVO extends PageParam {

    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "纳税人识别号")
    private String nsrsbh;
    @Schema(description = "有效标记")
    private String yxbj;
    @Schema(description = "创建人")
    private String createBy;
    @Schema(description = "创建时间")
    private String createTime;
    @Schema(description = "修改人")
    private String updateBy;
    @Schema(description = "修改时间")
    private String updateTime;
}
