package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@TableName("gh_wf_tdfsqmx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhWfTdfsqmxDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long tdfsqId;
    private String spuuid;
    private BigDecimal rkje;
    private BigDecimal tfsqJe;
    private String shxydm;
    private String nsrmc;
    private LocalDate skssqq;
    private LocalDate skssqz;
}