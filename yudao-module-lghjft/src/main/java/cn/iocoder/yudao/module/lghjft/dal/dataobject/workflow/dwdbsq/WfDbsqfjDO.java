package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工会隶属关系调拨申请-附件 DO
 *
 * @author 李文军
 */
@TableName("gh_wf_dbsq_fj")
@KeySequence("gh_wf_dbsq_attachment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfDbsqfjDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联调拨申请表ID（gh_wf_dbsq.id）
     */
    private Long dbsqId;
    /**
     * 文件名
     */
    private String wjmc;
    private String ywjmc;
    /**
     * 文件访问地址
     */
    private String wjlj;
    /**
     * 文件类型（pdf/png/jpg/docx）
     */
    private String fjlx;

}