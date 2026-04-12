package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小额失败已修改分页 Request VO")
@Data
public class XejfhbsbyxgPageReqVO extends PageParam {

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "缴费期间")
    private Long jfqj;

    @Schema(description = "账号")
    private String zh;

    @Schema(description = "户名")
    private String hm;

    @Schema(description = "新账号")
    private String xzh;

    @Schema(description = "新户名")
    private String xhm;

    @Schema(description = "本期返还金额")
    private BigDecimal je;

    @Schema(description = "退回日期")
    private String thrq;

    @Schema(description = "修改时间")
    private String updateTime;

    @Schema(description = "工会机构")
    private String deptId;
}
