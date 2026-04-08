package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 手工拨付录入分页 Request VO")
@Data
public class SgbflrPageReqVO extends PageParam {

    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "账号")
    private String zh;
    @Schema(description = "户名")
    private String hm;
    @Schema(description = "行号")
    private String hh;
    @Schema(description = "金额")
    private BigDecimal je;
    @Schema(description = "地址")
    private String dz;
    @Schema(description = "附言")
    private String fy;
    @Schema(description = "退回标志")
    private String thbj;
    @Schema(description = "退回原因")
    private String thyy;
    @Schema(description = "修改标记")
    private String xgbj;
    @Schema(description = "有效标记")
    private String yxbj;
    @Schema(description = "生成划款批次号")
    private String schkpch;
    @Schema(description = "系统生成标志")
    private String scbz;
    @Schema(description = "工会机构")
    private String deptId;
    @Schema(description = "退回日期-开始")
    private String beginThrq;
    @Schema(description = "退回日期-结束")
    private String endThrq;
}
