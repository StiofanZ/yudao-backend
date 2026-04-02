package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.czrj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * QxCzrj DO
 */
@TableName("qx_czrj")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class QxCzrjDO {

    @TableId(type = IdType.AUTO)
    private Long czrjid;
    private String czydm;
    private String czyxm;
    private LocalDateTime czsj;
    private String czms;
    private String cznr;
}
