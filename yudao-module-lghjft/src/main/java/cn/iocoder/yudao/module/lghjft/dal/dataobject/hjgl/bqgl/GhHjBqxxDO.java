package cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

/**
 * 工会户籍标签 DO
 *
 * @author 芋道源码
 */
@TableName("gh_hj_bqxx")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhHjBqxxDO extends BaseDO {

    /**
     * 归类管理代码
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标签ID
     */
    private String bqId;

    /**
     * 登记序号
     */
    private String djxh;

    /**
     * 有效起期
     */
    private LocalDate yxqq;

    /**
     * 有效止期
     */
    private LocalDate yxqz;

}
