package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小额拨付占比 Response VO")
@Data
@ExcelIgnoreUnannotated
public class XebfzbResVO {
    private String xend;
    @ExcelProperty("市级产业工会")
    private String sjdm;
    @ExcelProperty("主管工会")
    private String deptId;
    @ExcelProperty("缴费笔数")
    private Long jfbs;
    private Long jfhs;
    @ExcelProperty("缴费金额")
    private BigDecimal jfje;
    private BigDecimal sjje;
    private BigDecimal xjjfje;
    private BigDecimal sjjfje;
    private BigDecimal szqzjfje;
    private Long xebs;
    private Long xehs;
    private BigDecimal xjxeje;
    private BigDecimal sjxeje;
    private BigDecimal szqzxeje;
    @ExcelProperty("小额金额")
    private BigDecimal xeje;
    @ExcelProperty("基层金额")
    private BigDecimal jcghje;
}
