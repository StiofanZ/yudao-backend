package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 小额缴费组织管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class XejfghzzResVO {
    @ExcelProperty("登记序号")
    private String djxh;
    @ExcelProperty("工会机构代码")
    private String deptId;
    private String hyghbz;
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @ExcelProperty("纳税人名称")
    private String nsrmc;
    private String[] xejfzz244;
    private String[] xejfzz255;
    private String[] xejfzz25;
    private String[] xejfzz23;
    private String nsrjc;
    @ExcelProperty("主管税务机关代码")
    private String zgswjDm;
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
    private String nsrztmc;
    private LocalDateTime fzcrq;
    private LocalDateTime zxrq;
    private String zcdz;
    private String yzbm;
    private String lxr;
    private String lxdh;
    @ExcelProperty("工会类别代码")
    private String ghlbDm;
    @ExcelProperty("系统类别代码")
    private String xtlbDm;
    private String hjfl1Dm;
    private String hjfl2Dm;
    private String hjfl3Dm;
    private String xwsbqr;
    private String dclghbj;
    private String xwyqbj;
    private String sdghjgDm;
    private String clghbj;
    private LocalDateTime clghrq;
    private String jcghdm;
    private String jcghmc;
    private String jcghzh;
    private String jcghhm;
    private String jcghhh;
    private String jcghyh;
    private String bz;
    private String jym;
    private String nsrsbh;
    private String fileurl;
}
