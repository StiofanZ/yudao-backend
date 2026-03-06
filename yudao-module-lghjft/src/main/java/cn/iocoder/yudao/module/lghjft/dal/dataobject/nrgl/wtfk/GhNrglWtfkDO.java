package cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@TableName(value = "gh_nrgl_wtfk", autoResultMap = true)
@KeySequence("gh_nrgl_wtfk_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhNrglWtfkDO extends BaseDO {

    @TableId
    private Long id;

    private String fkbh;

    private Long yhId;

    private String yhmc;

    private String lx;

    private String ptmc;

    private String nr;

    private String lxdh;

    private String lxyx;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<FjItem> fjList;

    private Integer zt;

    private Long clrId;

    private LocalDateTime clsj;

    private String clsm;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FjItem {
        private String wjlj;
        private String wjmc;
        private String ywjmc;
    }
}
