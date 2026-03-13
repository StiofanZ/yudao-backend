package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("gh_wf_tdfsq_fj")
@KeySequence("gh_wf_tdfsq_fj_seq")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhWfTdfsqFjDO extends BaseDO {

    @TableId
    private Long id;
    private Long tdfsqId;
    private String fjlx;
    private String wjlj;
    private String wjmc;
    private  String ywjmc;

}
