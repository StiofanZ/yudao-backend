package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小额缴费基层到账分页 Request VO")
@Data
public class GhHkxxxejfjcdzPageReqVO extends PageParam {
    @Schema(description = "划款信息ID")
    private Long hkxxId;
    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "缴费期间")
    private Long jfqj;
    @Schema(description = "工会机构")
    private String deptId;
    @Schema(description = "账号")
    private String zh;
    @Schema(description = "新账号")
    private String xzh;
    @Schema(description = "本期返还金额")
    private BigDecimal je;
    @Schema(description = "退回标记")
    private String thbj;
    @Schema(description = "退回日期")
    private String thrq;
    @Schema(description = "户名")
    private String hm;
    @Schema(description = "新户名")
    private String xhm;
    @Schema(description = "地址")
    private String dz;
    @Schema(description = "附言")
    private String fy;
    @Schema(description = "修改标记")
    private String xgbj;
    @Schema(description = "修改时间")
    private String updateTime;
}
