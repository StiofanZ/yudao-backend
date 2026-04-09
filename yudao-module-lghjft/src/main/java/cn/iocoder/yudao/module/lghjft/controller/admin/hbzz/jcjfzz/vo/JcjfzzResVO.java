package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 基层经费到账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JcjfzzResVO {

    @Schema(description = "划款信息ID")
    private Long hkxxId;

    @Schema(description = "划款批次号")
    @ExcelProperty("划款批次号")
    private String hkpch;

    @Schema(description = "序号")
    @ExcelProperty("序号")
    private Long xh;

    @Schema(description = "类型")
    @ExcelProperty(value = "类型", converter = DictConvert.class)
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
    @ExcelProperty("新账号")
    private String xzh;

    @Schema(description = "新户名")
    @ExcelProperty("新户名")
    private String xhm;

    @Schema(description = "新行号")
    @ExcelProperty("新行号")
    private String xhh;

    @Schema(description = "金额")
    @ExcelProperty("金额")
    private BigDecimal je;

    @Schema(description = "工会机构")
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "地址")
    @ExcelProperty("地址")
    private String dz;

    @Schema(description = "附言")
    @ExcelProperty("附言")
    private String fy;

    @Schema(description = "校验码")
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

    @Schema(description = "生成划款批次号")
    private String schkpch;

    @Schema(description = "确认日期")
    @ExcelProperty("确认日期")
    private LocalDateTime qrrq;

    @Schema(description = "银行回单号")
    @ExcelProperty("银行回单号")
    private String yhhdh;

    @Schema(description = "到账标记")
    @ExcelProperty("到账标记")
    private String dzbj;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String bz;

    @Schema(description = "修改人")
    @ExcelProperty("修改人")
    private String xgr;

    @Schema(description = "修改时间")
    @ExcelProperty("修改时间")
    private LocalDateTime xgsj;

    @Schema(description = "系统生成标志")
    private String scbz;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "基层经费到账子表")
    private List<JcjfdzItem> jcjfdzList;

    @Data
    public static class JcjfdzItem {
        private Long hkxxId;
        private String ghHkxxJym;
        private String dzbj;
        private LocalDateTime qrrq;
        private String yhhdh;
        private String bz;
        private String createBy;
        private LocalDateTime createTime;
        private String updateBy;
        private LocalDateTime updateTime;
    }
}
