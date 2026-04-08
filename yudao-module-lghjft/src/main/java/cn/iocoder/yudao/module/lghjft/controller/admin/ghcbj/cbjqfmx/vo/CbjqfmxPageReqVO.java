package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqfmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 筹备金全返明细分页 Request VO")
@Data
public class CbjqfmxPageReqVO extends PageParam {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @Schema(description = "所属期起")
    private String skssqq;

    @Schema(description = "所属期止")
    private String skssqz;

    @Schema(description = "入库日期-开始")
    private String beginRkrq;

    @Schema(description = "入库日期-结束")
    private String endRkrq;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "基层经费拨付状态")
    private String cbjthbj;

    @Schema(description = "处理日期-开始")
    private String beginCbjthrq;

    @Schema(description = "处理日期-结束")
    private String endCbjthrq;
}
