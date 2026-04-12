package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小额代管经费做账分页 Request VO")
@Data
public class XedgjfPageReqVO extends PageParam {

    @Schema(description = "划款信息ID")
    private Long hkxxId;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "拨付文件日期起，取 MID(HKPCH,1,8)")
    private String beginHkpch;

    @Schema(description = "拨付文件日期止，取 MID(HKPCH,1,8)")
    private String endHkpch;

    @Schema(description = "缴费期间")
    private Long jfqj;

    @Schema(description = "类型，代管经费固定为 3")
    private String lx;

    @Schema(description = "账号")
    private String zh;

    @Schema(description = "账号1")
    private String zh1;

    @Schema(description = "账号2")
    private String zh2;

    @Schema(description = "账号3")
    private String zh3;

    @Schema(description = "往期已返金额")
    private BigDecimal wqyfje;

    @Schema(description = "本期返还金额")
    private BigDecimal je;

    @Schema(description = "到账标记")
    private String dzbj;

    @Schema(description = "确认日期起")
    private String beginQrrq;

    @Schema(description = "确认日期止")
    private String endQrrq;

    @Schema(description = "银行回单号")
    private String yhhdh;

    @Schema(description = "工会机构")
    private String deptId;

    @Schema(description = "地址")
    private String dz;
}
