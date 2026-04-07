package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 征收未主管信息确认 DO - 映射 v1 表 zswzgdw_qr
 *
 * 遵循 v1 表映射规范：不继承 BaseDO、不使用 @TableLogic、
 * 审计字段使用 v1 命名 (createBy / updateBy)。
 */
@TableName("zswzgdw_qr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ZswzgdwQrDO {

    /** 登记序号（关联 zswzgdw.djxh） */
    private String djxh;

    /** 确认结果代码 */
    private String qrjgDm;

    /** 备注 */
    private String bz;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    private LocalDateTime updateTime;
}
