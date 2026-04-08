package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 特殊经费确认 DO (child table gh_jf_tsjf)
 * v1 table - no BaseDO, no @TableLogic
 */
@TableName("gh_jf_tsjf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhJfTsjfDO {

    @TableId
    private Long ghjfId;
    private String spuuid;
    private String tsjfbj;
    private String tsjfsm;
    private LocalDateTime clsj;
    private String tsjfwj;
    private String tsjftp;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
