package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 基层经费到账对象 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HkxxResVO {

    @Schema(description = "划款信息ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer hkxxId;

    @ExcelProperty("划款批次号")
    @Schema(description = "划款批次号")
    private String hkpch;

    @ExcelProperty("序号")
    @Schema(description = "序号")
    private Long xh;

    @ExcelProperty(value = "类型", converter = DictConvert.class)
    @DictFormat("sys_fclx_type")
    @Schema(description = "类型")
    private String lx;

    @ExcelProperty("账号")
    @Schema(description = "账号")
    private String zh;

    @ExcelProperty("户名")
    @Schema(description = "户名")
    private String hm;

    @ExcelProperty("行号")
    @Schema(description = "行号")
    private String hh;

    @ExcelProperty("正确账号")
    @Schema(description = "正确账号")
    private String xzh;

    @ExcelProperty("正确户名")
    @Schema(description = "正确户名")
    private String xhm;

    @ExcelProperty("正确行号")
    @Schema(description = "正确行号")
    private String xhh;

    @ExcelProperty("金额")
    @Schema(description = "金额")
    private BigDecimal je;

    @ExcelProperty("工会机构ID")
    @Schema(description = "工会机构ID")
    private String deptId;

    @ExcelProperty("地址")
    @Schema(description = "地址")
    private String dz;

    @ExcelProperty("附言")
    @Schema(description = "附言")
    private String fy;

    @Schema(description = "校验码")
    private String jym;

    @ExcelProperty("退回标志")
    @Schema(description = "退回标志")
    private String thbj;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty("退回日期")
    @Schema(description = "退回日期")
    private Date thrq;

    @ExcelProperty("退回原因")
    @Schema(description = "退回原因")
    private String thyy;

    @Schema(description = "生成划款批次号")
    private String schkpch;

    @ExcelProperty("备注")
    @Schema(description = "备注")
    private String bz;

    @ExcelProperty("修改标志")
    @Schema(description = "修改标志")
    private String xgbj;

    @Schema(description = "系统生成标志")
    private String scbz;

    @Schema(description = "有效标志")
    private String yxbj;

    @ExcelProperty("修改人")
    @Schema(description = "修改人")
    private String xgr;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("修改时间")
    @Schema(description = "修改时间")
    private Date xgsj;
    // ===================== Base 字段 =====================
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间")
    @Schema(description = "创建时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("更新时间")
    @Schema(description = "更新时间")
    private Date updateTime;

    @ExcelProperty("创建者")
    @Schema(description = "创建者")
    private String createBy;

    @ExcelProperty("更新者")
    @Schema(description = "更新者")
    private String updateBy;
}