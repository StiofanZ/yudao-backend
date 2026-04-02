package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 征收未主管单位新增/修改 Request VO")
@Data
public class ZswzgdwSaveReqVO {

    private String id;
    private String dwdm;
    private String deptId;
    private String zgswjDm;
    private String shxydm;
    private String nsrmc;
    private String djxh;
    private Long bs;
    private BigDecimal jfje;
    private BigDecimal jcghje;
    private String qrjgDm;
}
