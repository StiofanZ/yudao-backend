package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CsFpblDqdssjVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long blId;
    private String bluuid;
    private String lx;
    private String ms;
    private Date yxqq;
    private Date yxqz;
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
