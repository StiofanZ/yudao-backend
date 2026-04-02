package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj;

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
 * 经费处理-当期代收数据 DO (gh_qsjshkrj 表)
 */
@TableName("gh_qsjshkrj")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JfclDqdssjDO {

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
