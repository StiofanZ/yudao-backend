package cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 工会户籍标签代码 DO
 *
 * @author 芋道源码
 */
@TableName("gh_dm_hj_bq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhDmHjBqDO extends BaseDO {

    /**
     * 归类管理代码
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 标签归类名称
     */
    private String bqMc;

    /**
     * 部门ID
     */
    private Long deptId;

}
