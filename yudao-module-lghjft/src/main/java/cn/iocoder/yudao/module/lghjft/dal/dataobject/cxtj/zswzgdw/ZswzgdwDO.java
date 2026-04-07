package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Zswzgdw DO
 */
@TableName("zswzgdw")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ZswzgdwDO {

    private String id;
    private String dwdm;
    private String deptId;
    private String zgswjDm;
    private String shxydm;
    private String nsrmc;
    @TableId(type = IdType.INPUT)
    private String djxh;
    private Long bs;
    private BigDecimal jfje;
    private BigDecimal jcghje;
}
