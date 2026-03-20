package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.hkxxbfzhpc;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 拨付信息 DO
 *
 * @author 李文军
 */
@TableName("gh_hkxx_bfzhpc")
@KeySequence("gh_hkxx_bfzhpc_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HkxxBfzhpcDO extends BaseDO {

    /**
     * 账户排除ID
     */
    @TableId
    private Integer zhpcid;
    /**
     * 税票UUID
     */
    private String spuuid;
    /**
     * 登记序号
     */
    private String djxh;
    /**
     * 账号
     */
    private String zh;
    /**
     * 户名
     */
    private String hm;
    /**
     * 行号
     */
    private String hh;
    /**
     * 排除状态
     */
    private String zt;
    /**
     * 备注
     */
    private String bz;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;


}