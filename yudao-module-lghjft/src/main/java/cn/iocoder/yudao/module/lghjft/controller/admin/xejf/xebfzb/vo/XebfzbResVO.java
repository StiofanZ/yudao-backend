package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小额拨付占比 Response VO")
@Data
public class XebfzbResVO {
    private String xend;
    private String sjdm;
    private String deptId;
    private Long jfbs;
    private Long jfhs;
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
    private BigDecimal xeje;
    private BigDecimal jcghje;
}
