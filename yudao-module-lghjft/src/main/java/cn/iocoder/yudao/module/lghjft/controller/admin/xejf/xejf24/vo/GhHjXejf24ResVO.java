package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 24年小额确认 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhHjXejf24ResVO {
    @Schema(description = "工会机构代码")
    @ExcelProperty("工会机构代码")
    private String deptId;
    private String hyghbz;
    @ExcelProperty("登记序号")
    private String djxh;
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @ExcelProperty("纳税人名称")
    private String nsrmc;
    private String nsrjc;
    private String zgswjDm;
    @ExcelProperty("主管税务机关名称")
    private String zgswjmc;
    private String zgswskfjDm;
    private String zgswskfjmc;
    private String ssglyDm;
    private String ssglyxm;
    private String zzjglxDm;
    private String zzjglxmc;
    private String hyDm;
    private String hymc;
    private String djzclxDm;
    private String djzclxmc;
    private String dwlsgxDm;
    private String dwlsgxmc;
    private BigDecimal zgrs;
    private String nsrztDm;
    @ExcelProperty("纳税人状态名称")
    private String nsrztmc;
    private LocalDateTime fzcrq;
    private LocalDateTime zxrq;
    private String zcdz;
    private String yzbm;
    private String lxr;
    private String lxdh;
    private String ghlbDm;
    private String xtlbDm;
    private String hjfl1Dm;
    private String hjfl2Dm;
    private String hjfl3Dm;
    @ExcelProperty("24小额原始情况")
    private String hjfl4Dm;
    @ExcelProperty("24小额确认情况")
    private String hjfl5Dm;
    @ExcelProperty("24小额类型")
    private String hjfl6Dm;
    private LocalDateTime hjfl7Dm;
    private String hjfl8Dm;
    private String hjfl9Dm;
    private String sdghjgDm;
    private String clghbj;
    private LocalDateTime clghrq;
    private String jcghdm;
    private String jcghmc;
    @ExcelProperty("基层工会账户")
    private String jcghzh;
    @ExcelProperty("基层工会户名")
    private String jcghhm;
    @ExcelProperty("基层工会行号")
    private String jcghhh;
    @ExcelProperty("基层工会银行")
    private String jcghyh;
    @ExcelProperty("备注")
    private String bz;
    private String jym;
    private String nsrsbh;
    private String fileurl;
}
