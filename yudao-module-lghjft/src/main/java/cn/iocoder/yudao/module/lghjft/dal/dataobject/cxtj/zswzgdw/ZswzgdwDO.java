package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 征收未主管单位 DO — 映射 v1 表 zswzgdw
 *
 * 遵循 v1 表映射规范：不继承 BaseDO、不使用 @TableLogic。
 */
@TableName("zswzgdw")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ZswzgdwDO {

    @TableId(type = IdType.INPUT)
    private String djxh;

    private String id;
    private String dwdm;
    private String deptId;
    private String zgswjDm;
    private String shxydm;
    private String nsrmc;
    private Long bs;
    private BigDecimal jfje;
    private BigDecimal jcghje;
    private String qrjgDm;

    /** 征收未主管信息确认列表（非数据库字段，XML resultMap 填充） */
    @TableField(exist = false)
    private List<ZswzgdwQrDO> zswzgdwQrList;
}
