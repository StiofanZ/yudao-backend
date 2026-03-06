package cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("gh_nrgl_wtfk_clmx")
@KeySequence("gh_nrgl_wtfk_clmx_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhNrglWtfkClmxDO extends BaseDO {

    @TableId
    private Long id;

    private Long wtfkId;

    private Long clrId;

    private String clrmc;

    private String clsm;

    private Integer zt;
}
