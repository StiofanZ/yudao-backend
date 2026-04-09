package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 小额拨付记账凭证 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhHkxxxejfResVO {
    private Long hkxxId;
    @ExcelProperty("划款批次号")
    private String hkpch;
    private Long xh;
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
    private BigDecimal wqyfje;
    @ExcelProperty("金额")
    private BigDecimal je;
    @ExcelProperty("工会机构代码")
    private String deptId;
    private String dz;
    private String fy;
    private String jym;
    private String thbj;
    private LocalDateTime thrq;
    private String thyy;
    private String hkxxidgl;
    private String schkpch;
    private LocalDateTime qrrq;
    private String yhhdh;
    private String bz;
    private String xgbj;
    private String scbz;
    private String yxbj;
}
