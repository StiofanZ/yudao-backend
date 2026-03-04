package cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;
import java.time.LocalDateTime;

@TableName("gh_hkxx_qrsz")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HkxxQrszDO extends BaseDO {

    /**
     * 主键（自增）
     */
    @TableId
    private Integer id;

    /**
     * 划款信息ID
     */
    private Integer hkxxId;

    /**
     * 划款信息校验码
     */
    private String ghHkxxJym;

    /**
     * 到账标记
     */
    private String dzbj;

    /**
     * 确认日期
     */
    private LocalDateTime qrrq;

    /**
     * 银行回单号
     */
    private String yhhdh;



    /**
     * 备注
     */
    private String bz;

    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    @TableField(exist = false)
    private String creator;
    @TableField(exist = false)
    private String updater;
    @TableField(exist = false)
    private Boolean deleted;


}