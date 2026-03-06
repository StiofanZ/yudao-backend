package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

@TableName("gh_wf_tdfsq")
@KeySequence("gh_wf_tdfsq_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhWfTdfsqDO extends BaseDO {

    @TableId
    private Long id;
    private String shxydm;
    private String dwmc;
    private String qksm;
    private String dwfzr;
    private String jbr;
    private String lxdh;
    private String hm;
    private String khyhmc;
    private String zh;
    private String khyhhh;
    private LocalDate sqrq;
    private String lcslId;
    private String zgghspyj;
    private String zgghfzr;
    private String zgghjbr;
    private LocalDate zgghsprq;
    private String sghspyj;
    private String sghfzr;
    private String sghjbr;
    private LocalDate sghsprq;
    private Integer thfs;
}
