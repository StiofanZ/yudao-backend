package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * Fydsqk DO
 */
@TableName("gh_hkxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class FydsqkDO {

    @TableId(type = IdType.INPUT)
    private String deptId;
}
