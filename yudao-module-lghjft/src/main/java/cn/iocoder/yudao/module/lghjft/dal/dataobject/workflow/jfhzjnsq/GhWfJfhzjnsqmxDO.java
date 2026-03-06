package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

@TableName("gh_wf_jfhzjnsqmx")
@KeySequence("gh_wf_jfhzjnsqmx_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhWfJfhzjnsqmxDO extends BaseDO {

    @TableId
    private Long id;

    private Long jfhzjnsqId;
    private String shxydm;
    private String dwmc;
    private String zgsbm;
    private Integer zgzs;
    private BigDecimal ygzze;
}
