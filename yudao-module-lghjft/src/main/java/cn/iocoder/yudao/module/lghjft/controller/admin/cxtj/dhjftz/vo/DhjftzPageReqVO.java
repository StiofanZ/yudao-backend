package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 到户经费台账分页 Request VO")
@Data
public class DhjftzPageReqVO extends PageParam {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人识别号")
    private String nsrsbh;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "上代工会机构代码")
    private String sdghjgDm;

    @Schema(description = "成立工会标记")
    private String clghbj;

    @Schema(description = "基层工会银行")
    private String jcghyh;

    @Schema(description = "成立工会开始日期")
    private String beginClghrq;

    @Schema(description = "成立工会结束日期")
    private String endClghrq;
}
