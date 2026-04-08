package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * v1 selecList result: HKPCH, ZH, HM, HH, JE, DZ, FY, CREATE_TIME, CREATE_BY (user_name)
 */
@Schema(description = "管理后台 - 生成划拨文件 Response VO")
@Data
public class SchbwjResVO {

    @Schema(description = "划拨批次号")
    private String hkpch;

    @Schema(description = "收款人账号")
    private String zh;

    @Schema(description = "收款人名称")
    private String hm;

    @Schema(description = "收款人开户行行号")
    private String hh;

    @Schema(description = "金额")
    private BigDecimal je;

    @Schema(description = "收款人地址")
    private String dz;

    @Schema(description = "附言")
    private String fy;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "创建人")
    private String createBy;
}
