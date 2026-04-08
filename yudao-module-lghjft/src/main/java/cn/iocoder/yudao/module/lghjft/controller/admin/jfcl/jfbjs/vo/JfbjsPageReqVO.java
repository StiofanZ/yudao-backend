package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 经费补结算分页 Request VO")
@Data
public class JfbjsPageReqVO extends PageParam {

    @Schema(description = "税票ID")
    private String spuuid;

    @Schema(description = "入库日期-起")
    private String rkrqStart;

    @Schema(description = "入库日期-止")
    private String rkrqEnd;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "所属期起")
    private String skssqq;

    @Schema(description = "所属期止")
    private String skssqz;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "结算日期")
    private String jsrq;
}
