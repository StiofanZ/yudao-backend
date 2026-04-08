package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 特殊经费处理分页 Request VO")
@Data
public class TsjfclPageReqVO extends PageParam {

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "所属期起")
    private String skssqq;

    @Schema(description = "所属期止")
    private String skssqz;

    @Schema(description = "入库日期开始")
    private String rkrqStart;

    @Schema(description = "入库日期结束")
    private String rkrqEnd;

    @Schema(description = "结算日期开始")
    private String jsrqStart;

    @Schema(description = "结算日期结束")
    private String jsrqEnd;
}
