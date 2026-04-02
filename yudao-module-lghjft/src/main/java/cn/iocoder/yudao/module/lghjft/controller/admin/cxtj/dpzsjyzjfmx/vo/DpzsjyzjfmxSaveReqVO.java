package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 近一周缴费新增/修改 Request VO")
@Data
public class DpzsjyzjfmxSaveReqVO {

    private String spuuid;
    private String zsswjgDm;
    private String swjg;
    private String ghjg;
    private String djxh;
    private String jfdw;
    private LocalDateTime skssqq;
    private LocalDateTime skssqz;
    private String zspm;
    private BigDecimal jfje;
    private LocalDateTime jfrq;
}
