package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分配比例新增/修改 Request VO")
@Data
public class CsFpblSaveReqVO {

    private Long blId;
    private String bluuid;
    private String lx;
    private String ms;
    private LocalDateTime yxqq;
    private LocalDateTime yxqz;
    private String xybz;
    private BigDecimal jcghbl;
    private BigDecimal hyghbl;
    private BigDecimal sdghbl;
    private BigDecimal xjghbl;
    private BigDecimal sjghbl;
    private BigDecimal szghbl;
    private BigDecimal qgzghbl;
    private BigDecimal sjcjbl;
    private BigDecimal sdsjbl;
    private BigDecimal swjgbl;
    private String tj;
    private Long yxj;
    private String mrbz;
    private String jym;
}
