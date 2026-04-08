package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 手工拨付录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SgbflrResVO {

    @Schema(description = "划款信息ID")
    private Long hkxxId;
    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "序号")
    private Long xh;
    @Schema(description = "类型")
    private String lx;
    @Schema(description = "账号")
    private String zh;
    @Schema(description = "户名")
    private String hm;
    @Schema(description = "行号")
    private String hh;
    @Schema(description = "金额")
    private BigDecimal je;
    @Schema(description = "工会机构")
    private String deptId;
    @Schema(description = "地址")
    private String dz;
    @Schema(description = "附言")
    private String fy;
    @Schema(description = "校验码")
    private String jym;
    @Schema(description = "退回标志")
    private String thbj;
    @Schema(description = "退回日期")
    private LocalDateTime thrq;
    @Schema(description = "退回原因")
    private String thyy;
    @Schema(description = "修改标记")
    private String xgbj;
    @Schema(description = "有效标记")
    private String yxbj;
    @Schema(description = "生成划款批次号")
    private String schkpch;
    @Schema(description = "备注信息")
    private String bz;
    @Schema(description = "系统生成标志")
    private String scbz;
    @Schema(description = "创建人")
    private String createBy;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新人")
    private String updateBy;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
