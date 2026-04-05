package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 代管经费台账分页 Request VO")
@Data
public class DgjftzPageReqVO extends PageParam {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "筹备金退回标记")
    private String cbjthbj;

    @Schema(description = "入库日期-开始")
    private String beginRkrq;

    @Schema(description = "入库日期-结束")
    private String endRkrq;

    @Schema(description = "结算日期-开始")
    private String beginJsrq;

    @Schema(description = "结算日期-结束")
    private String endJsrq;

    @Schema(description = "返拨标记")
    private String fbbj;

    @Schema(description = "返拨日期-开始")
    private String beginFbrq;

    @Schema(description = "返拨日期-结束")
    private String endFbrq;

    @Schema(description = "备注")
    private String bz;
}
