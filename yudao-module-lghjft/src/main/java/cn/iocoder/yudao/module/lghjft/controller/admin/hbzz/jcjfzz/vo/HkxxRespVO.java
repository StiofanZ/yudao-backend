package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 基层经费到账对象 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HkxxRespVO  {

@Schema(description = "划款信息ID", requiredMode = Schema.RequiredMode.REQUIRED)
private Integer hkxxId;

    @ExcelProperty("划款批次号")
    @Schema(description = "划款批次号")
    private String hkpch;

    @ExcelProperty("序号")
    @Schema(description = "序号")
    private Long xh;

    @ExcelProperty("类型")
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
    private Date  thrq;

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
    private Date  xgsj;
    // ===================== 子表字段 =====================
    @ExcelProperty("到账标记")
    @Schema(description = "到账标记")
    private String dzbj;

    @JsonFormat(pattern = "yyyy-MM-dd")

    @ExcelProperty("确认日期")
    @Schema(description = "确认日期")
    private Date  qrrq;

    @ExcelProperty("银行回单号")
    @Schema(description = "银行回单号")
    private String yhhdh;

    // ===================== Base 字段 =====================
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间")
    @Schema(description = "创建时间")
    private Date  createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("更新时间")
    @Schema(description = "更新时间")
    private Date  updateTime;

    @ExcelProperty("创建者")
    @Schema(description = "创建者")
    private String createBy;

    @ExcelProperty("更新者")
    @Schema(description = "更新者")
    private String updateBy;
    // ===================== 子表列表：基层经费到账确认 =====================
    @Schema(description = "基层经费到账子表列表")
    private List<HkxxQrszRespVO> jcjfdzList;

    @Data
    @Schema(description = "基层经费到账子表 Response VO")
    public static class HkxxQrszRespVO {

        @Schema(description = "划款信息ID")
        private Integer hkxxId;

        @Schema(description = "划款信息校验码")
        private String ghHkxxJym;

        @Schema(description = "到账标记")
        private String dzbj;

        @Schema(description = "确认日期")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date qrrq;

        @Schema(description = "银行回单号")
        private String yhhdh;

        @Schema(description = "备注")
        private String bz;

        @Schema(description = "创建人")
        private String createBy;

        @Schema(description = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;

        @Schema(description = "修改人")
        private String updateBy;

        @Schema(description = "修改时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date updateTime;

    }
}