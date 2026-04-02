package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 24年小额确认新增/修改 Request VO")
@Data
public class GhHjXejf24SaveReqVO {
    @Schema(description = "工会机构代码")
    private String deptId;
    private String hyghbz;
    private String djxh;
    private String shxydm;
    private String nsrmc;
    private String nsrjc;
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
    private String ghlbDm;
    private String xtlbDm;
    private String hjfl1Dm;
    private String hjfl2Dm;
    private String hjfl3Dm;
    private String hjfl4Dm;
    private String hjfl5Dm;
    private String hjfl6Dm;
    private LocalDateTime hjfl7Dm;
    private String hjfl8Dm;
    private String hjfl9Dm;
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
