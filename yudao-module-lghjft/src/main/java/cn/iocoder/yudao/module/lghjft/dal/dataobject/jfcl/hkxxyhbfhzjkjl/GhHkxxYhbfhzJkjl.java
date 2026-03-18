package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hkxxyhbfhzjkjl;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * gh_hkxx_yhbfhz_jkjl DO
 *
 * @author 李文军
 */
@TableName("gh_hkxx_yhbfhz_jkjl")
@KeySequence("gh_hkxx_yhbfhz_jkjl_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhHkxxYhbfhzJkjl extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long yhbfhzJkjlId;
    /**
     * 银行接口名称
     */
    private String jkmc;
    /**
     * 汇总信息id
     */
    private Long fkId;
    /**
     * 请求报文
     */
    private String qqbw;
    /**
     * 响应报文
     */
    private String xybw;
    /**
     * 请求结果（成功-S，异常-E）
     */
    private String qqzt;
    /**
     * 请求信息，对应请求结果字段
     */
    private String qqjgxx;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;


}