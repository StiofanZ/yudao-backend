package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 退回凭证重发 Response VO")
@Data
public class ThpzcfResVO {

    @Schema(description = "划款信息ID")
    private Long hkxxId;
    @Schema(description = "划款批次号")
    @ExcelProperty("划款批次号")
    private String hkpch;
    @Schema(description = "序号")
    @ExcelProperty("序号")
    private Long xh;
    @Schema(description = "类型")
    @ExcelProperty(value = "分成类型", converter = DictConvert.class)
    @DictFormat("sys_fclx_type")
    private String lx;
    @Schema(description = "账号")
    @ExcelProperty("账号")
    private String zh;
    @Schema(description = "户名")
    @ExcelProperty("户名")
    private String hm;
    @Schema(description = "行号")
    @ExcelProperty("行号")
    private String hh;
    @Schema(description = "新账号")
    @ExcelProperty("收款人账号(必填)")
    private String xzh;
    @Schema(description = "新户名")
    @ExcelProperty("收款人名称(必填)")
    private String xhm;
    @Schema(description = "新行号")
    @ExcelProperty("收款人开户行行号(必填)")
    private String xhh;
    @Schema(description = "金额")
    @ExcelProperty("金额")
    private BigDecimal je;
    @Schema(description = "工会机构")
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;
    @Schema(description = "地址")
    @ExcelProperty("收款人地址(可选)")
    private String dz;
    @Schema(description = "附言")
    @ExcelProperty("附言(可选)")
    private String fy;
    @Schema(description = "校验码")
    @ExcelProperty("校验码")
    private String jym;
    @Schema(description = "退回标志")
    @ExcelProperty("退回标志")
    private String thbj;
    @Schema(description = "退回日期")
    @ExcelProperty("退回日期")
    private LocalDateTime thrq;
    @Schema(description = "退回原因")
    @ExcelProperty("退回原因")
    private String thyy;
    @Schema(description = "修改标志")
    @ExcelProperty("修改标志")
    private String xgbj;
    @Schema(description = "划款信息关联")
    @ExcelProperty("划款信息关联")
    private String hkxxidgl;
    @Schema(description = "生成划款批次号")
    @ExcelProperty("生成划款批次号")
    private String schkpch;
    @Schema(description = "备注信息")
    @ExcelProperty("备注信息")
    private String bz;
    @Schema(description = "系统生成标志")
    @ExcelProperty("系统生成标志")
    private String scbz;
    @Schema(description = "修改人")
    @ExcelProperty("修改人")
    private String xgr;
    @Schema(description = "修改时间")
    @ExcelProperty("修改时间")
    private LocalDateTime xgsj;
    @Schema(description = "有效标记")
    private String yxbj;
    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;
    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateBy;
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}
