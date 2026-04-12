package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 小额代管经费做账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class XedgjfResVO {

    @Schema(description = "划款信息ID")
    private Long hkxxId;

    @ExcelProperty("划款批次号")
    private String hkpch;

    private Long xh;

    @ExcelProperty("缴费期间")
    private Long jfqj;

    private String lx;

    @ExcelProperty("账号")
    private String zh;

    private String zh1;
    private String zh2;
    private String zh3;

    @ExcelProperty("户名")
    private String hm;

    private String hh;
    private String xzh;
    private String xhm;
    private String xhh;

    @ExcelProperty("往期已返金额")
    private BigDecimal wqyfje;

    @ExcelProperty("本期返还金额")
    private BigDecimal je;

    @ExcelProperty("工会机构")
    private String deptId;

    @ExcelProperty("地址")
    private String dz;

    private String fy;
    private String jym;
    private String thbj;
    private LocalDateTime thrq;
    private String thyy;
    private String hkxxidgl;
    private String schkpch;

    @ExcelProperty("到账日期")
    private LocalDateTime qrrq;

    @ExcelProperty("做账凭证号")
    private String yhhdh;

    @ExcelProperty("到账标记")
    private String dzbj;

    @ExcelProperty("备注")
    private String bz;

    private String xgbj;
    private String scbz;
    private String yxbj;
    private String createBy;
    private LocalDateTime createTime;

    @ExcelProperty("修改人")
    private String updateBy;

    @ExcelProperty("修改时间")
    private LocalDateTime updateTime;

    @Schema(description = "确认子表")
    private List<XedgjfQrszItemVO> qrszList;
}
