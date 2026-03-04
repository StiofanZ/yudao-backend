package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 基层经费到账对象分页 Request VO")
@Data
public class HkxxPageReqVO extends PageParam {

    // ===================== 主表字段 =====================
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

    @Schema(description = "正确账号")
    private String xzh;

    @Schema(description = "正确户名")
    private String xhm;

    @Schema(description = "正确行号")
    private String xhh;

    @Schema(description = "金额")
    private BigDecimal je;

    @Schema(description = "工会机构ID")
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
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date thrq;

    @Schema(description = "退回原因")
    private String thyy;

    @Schema(description = "生成划款批次号")
    private String schkpch;

    @Schema(description = "修改标志")
    private String xgbj;

    @Schema(description = "修改人")
    private String xgr;

    @Schema(description = "修改时间")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date xgsj;

    @Schema(description = "系统生成标志")
    private String scbz;

    @Schema(description = "有效标志")
    private String yxbj;

    @Schema(description = "备注")
    private String bz;

    // ===================== 子表查询字段 =====================
    @Schema(description = "到账标记")
    private String dzbj;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "修改人")
    private String updateBy;

    // ===================== 时间范围查询 =====================
    @Schema(description = "开始划款批次号")
    private String beginHkpch;

    @Schema(description = "结束划款批次号")
    private String endHkpch;

    @Schema(description = "开始确认日期")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date beginQrrq;

    @Schema(description = "结束确认日期")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date  endQrrq;

    @Schema(description = "开始退回日期")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date  beginThrq;

    @Schema(description = "结束退回日期")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date  endThrq;

    @Schema(description = "开始创建时间")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date  beginCreateTime;

    @Schema(description = "结束创建时间")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date  endCreateTime;

    @Schema(description = "开始修改时间")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date  beginUpdateTime;

    @Schema(description = "结束修改时间")
    @org.springframework.format.annotation.DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date  endUpdateTime;
}