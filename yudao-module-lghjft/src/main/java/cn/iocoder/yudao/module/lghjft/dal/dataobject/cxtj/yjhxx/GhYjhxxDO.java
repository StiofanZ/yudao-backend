package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.yjhxx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * GhYjhxx DO
 */
@TableName("gh_yjhxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhYjhxxDO {

    @TableId(type = IdType.AUTO)
    private Long jhxxId;
    private String nsrmc;
    private String shxydm;
    private String nsrsbh;
    private String yxbj;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
