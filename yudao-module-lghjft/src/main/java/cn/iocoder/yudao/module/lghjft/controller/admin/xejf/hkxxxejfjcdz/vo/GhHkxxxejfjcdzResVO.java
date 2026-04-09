package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小额缴费基层到账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhHkxxxejfjcdzResVO {
    private Long hkxxId;
    @ExcelProperty("划款批次号")
    private String hkpch;
    private Long xh;
    private Long jfqj;
    private String lx;
    @ExcelProperty("账号")
    private String zh;
    @ExcelProperty("户名")
    private String hm;
    @ExcelProperty("行号")
    private String hh;
    @ExcelProperty("新账号")
    private String xzh;
    @ExcelProperty("新户名")
    private String xhm;
    @ExcelProperty("新行号")
    private String xhh;
    private BigDecimal wqyfje;
    @ExcelProperty("本期返还金额")
    private BigDecimal je;
    @ExcelProperty("工会机构代码")
    private String deptId;
    @ExcelProperty("地址")
    private String dz;
    @ExcelProperty("附言")
    private String fy;
    private String jym;
    @ExcelProperty("退回标记")
    private String thbj;
    @ExcelProperty("退回日期")
    private String thrq;
    @ExcelProperty("退回原因")
    private String thyy;
    private String hkxxidgl;
    private String schkpch;
    @ExcelProperty("备注信息")
    private String bz;
    @ExcelProperty("修改标记")
    private String xgbj;
    private String scbz;
    private String yxbj;
    @ExcelProperty("修改人")
    private String xgr;
    @ExcelProperty("修改时间")
    private String xgsj;
}
