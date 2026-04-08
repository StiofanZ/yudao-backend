package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class QsjshkrjDO implements Serializable {
    private static final long serialVersionUID = -7714355803241803874L;

    private String lx;
    private String rkrq;
    private BigDecimal je;
    private String jsrq;
    private String hkrq;
    private String pch;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
