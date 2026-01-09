package cn.iocoder.yudao.module.lghjft.dal.dataobject.auth;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 单点登录集成配置 DO
 *
 * @author 芋道源码
 */
@TableName("gh_cs_sso")
@KeySequence("gh_cs_sso_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhCsSsoDO extends BaseDO {

    /**
     * 用户ID
     */
    @TableId
    private Long userId;
    /**
     * 用户名称
     */
    private String lghUser;

}
