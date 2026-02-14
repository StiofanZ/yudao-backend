package cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.ghhjyhxx;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

/**
 * 工会户籍银行信息 DO
 *
 * @author 芋道源码
 */
@TableName("gh_hj_yhxx")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhHjYhxxDO extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登记序号
     */
    private String djxh;

    /**
     * 基层工会账号
     */
    private String jcghzh;

    /**
     * 基层工会户名
     */
    private String jcghhm;

    /**
     * 基层工会行号
     */
    private String jcghhh;

    /**
     * 基层工会银行
     */
    private String jcghyh;

    /**
     * 有效起期
     */
    private LocalDate yxqq;

    /**
     * 有效止期
     */
    private LocalDate yxqz;

}
