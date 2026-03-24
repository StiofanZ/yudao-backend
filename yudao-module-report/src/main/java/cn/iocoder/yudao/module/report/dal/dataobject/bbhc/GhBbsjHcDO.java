package cn.iocoder.yudao.module.report.dal.dataobject.bbhc;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 报表数据当前快照
 */
@TableName("gh_bbsj_hc")
@KeySequence("gh_bbsj_hc_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhBbsjHcDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 租户编号
     */
    private Long tenantId;

    /**
     * 报表 ID
     */
    private String bbid;

    /**
     * 报表编码
     */
    private String bbbm;

    /**
     * 报表名称
     */
    private String bbmc;

    /**
     * 执行类型
     */
    private String zxlx;

    /**
     * 业务日期
     */
    private LocalDate ywrq;

    /**
     * 查询参数 JSON
     */
    private String cxcsJson;

    /**
     * 参数摘要
     */
    private String cszy;

    /**
     * 批次编号
     */
    private String pcbh;

    /**
     * 报表更新标识
     */
    private String bbgxbs;

    /**
     * 结果 JSON
     */
    private String jgJson;

    /**
     * 生成时间
     */
    private LocalDateTime scsj;

    /**
     * 生成人
     */
    private String scr;
}
