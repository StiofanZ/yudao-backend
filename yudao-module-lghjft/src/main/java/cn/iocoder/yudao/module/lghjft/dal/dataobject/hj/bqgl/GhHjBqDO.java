package cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.bqgl;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 户籍标签 DO
 *
 * @author 芋道源码
 */
@TableName("gh_hj_bq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhHjBqDO extends BaseDO {

    /**
     * 归类管理代码
     */
    @TableId
    private Long id;

    /**
     * 标签ID
     */
    private String bqId;

    /**
     * 登记序号
     */
    private String djxh;

}
