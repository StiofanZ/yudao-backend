package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 征收未主管单位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ZswzgdwResVO {

    @Schema(description = "序号")
    @ExcelProperty("序号")
    private String id;

    @Schema(description = "默认归属工会")
    @ExcelProperty(value = "默认归属工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String dwdm;

    @Schema(description = "主管工会")
    @ExcelProperty(value = "主管工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "主管税务局代码")
    @ExcelProperty(value = "主管税务局代码", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zgswjDm;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "登记序号")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "缴费笔数")
    @ExcelProperty("缴费笔数")
    private Long bs;

    @Schema(description = "缴费金额")
    @ExcelProperty("缴费金额")
    private BigDecimal jfje;

    @Schema(description = "基层工会金额")
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "确认结果代码")
    @ExcelProperty(value = "确认结果代码", converter = DictConvert.class)
    @DictFormat("sys_qrjg")
    private String qrjgDm;

    @Schema(description = "征收未主管信息确认列表")
    private List<ZswzgdwQrVO> zswzgdwQrList;
}
