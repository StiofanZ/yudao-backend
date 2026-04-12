package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.bfzhpc;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 拨付信息 DO
 *
 * @author 李文军
 */
@TableName("gh_hkxx_bfzhpc")
@KeySequence("gh_hkxx_bfzhpc_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HkxxBfzhpcDO {

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
