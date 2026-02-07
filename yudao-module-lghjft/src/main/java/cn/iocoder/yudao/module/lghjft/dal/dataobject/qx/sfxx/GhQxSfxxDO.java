package cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("gh_qx_sfxx")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhQxSfxxDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long dlzhId;

    private String djxh;

    private String sflx;

    private String ghlx;

    private String qxlx;

    private String sqyy;

    private String jbyy;

    private Long deptId;

    private Integer status;

}
