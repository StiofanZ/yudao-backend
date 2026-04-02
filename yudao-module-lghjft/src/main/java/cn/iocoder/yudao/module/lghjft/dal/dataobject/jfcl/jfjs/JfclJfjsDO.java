package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 经费处理-经费结算 DO (gh_qsjshkrj 表)
 */
@TableName("gh_qsjshkrj")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JfclJfjsDO {

    private String lx;
    @TableId(type = IdType.INPUT)
    private Date rkrq;
    private BigDecimal je;
    private Date jsrq;
    private Date hkrq;
    private String pch;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
