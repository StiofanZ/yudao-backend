package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.xzqh;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 行政区划 DO
 *
 * @author 李文军
 */
@TableName("dm_xzqh")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XzqhDO {

    /**
     * 行政区划代码
     */
    @TableId(type = IdType.INPUT)
    private String xzqhDm;
    /**
     * 行政区划名称
     */
    private String xzqhmc;
    /**
     * 上级行政区划代码
     */
    private String sjxzqhDm;
    /**
     * 行政区划级别
     */
    private String xzqhjb;

}
