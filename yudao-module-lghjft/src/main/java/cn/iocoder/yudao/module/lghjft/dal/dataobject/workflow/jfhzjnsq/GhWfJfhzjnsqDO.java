package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

@TableName("gh_wf_jfhzjnsq")
@KeySequence("gh_wf_jfhzjnsq_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhWfJfhzjnsqDO extends BaseDO {

    @TableId
    private Long id;

    private String shxydm;
    private String dwmc;
    private String zgsbm;
    private String dwdz;
    private String ghfrdjzh;
    private String ghmc;
    private Integer zgzs;
    private Integer ghyhs;
    private String ghfzr;
    private String lxdh;
    private String ghzh;
    private String khyhmc;
    private String ghhm;
    private String khyhwdm;
    private String hzjnyy;
    private String dwfzr;
    private String jbr;
    private String jbrdh;
    private LocalDate sqrq;
    private Integer fzjgzs;
    private String zgghspyj;
    private String zgghfzr;
    private String zgghjbr;
    private String zgghjbrdh;
    private LocalDate zgghsprq;
    private String sghspyj;
    private String sghfzr;
    private LocalDate sghsprq;
    private String lcslId;
}
