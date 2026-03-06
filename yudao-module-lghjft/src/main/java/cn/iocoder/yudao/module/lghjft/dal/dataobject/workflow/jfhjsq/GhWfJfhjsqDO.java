package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhjsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@TableName("gh_wf_jfhjsq")
@KeySequence("gh_wf_jfhjsq_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhWfJfhjsqDO extends BaseDO {

    @TableId
    private Long id;
    private String shxydm;
    private String dwmc;
    private String lxr;
    private String lxdh;
    private BigDecimal syfl;
    private Integer zgzs;
    private BigDecimal ygzze;
    private BigDecimal ybjje;
    private String hjkssj;
    private String hjjssj;
    private Integer hjzys;
    private BigDecimal ljhjje;
    private String hjqksm;
    private String dwfzr;
    private String jbr;
    private LocalDate sqrq;
    private String jcghyj;
    private String jcghfzr;
    private String jcghjbr;
    private LocalDate jcghgzrq;
    private String zgghspyj;
    private String zgghfzr;
    private String zgghjbr;
    private LocalDate zgghsprq;
    private String zgghcwfzr;
    private String lcslId;
}
