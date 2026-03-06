package cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcwj;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

/**
 * 政策文件 DO
 */
@TableName("gh_nrgl_zcwj")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZcwjDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 文件内容
     */
    private String content;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态 0-草稿 2-已发布 4-已下架
     */
    private Integer status;

    /**
     * 发布部门编号
     */
    private Long deptId;

    /**
     * 可见范围 1-完全可见 2-下级可见 3-本级可见
     */
    private Integer kjfw;

    /**
     * 版本号
     */
    private String versionNo;

    /**
     * 标签，逗号分隔
     */
    private String tags;

    /**
     * 搜索关键词，逗号分隔
     */
    private String searchKeywords;

    /**
     * 附件地址，逗号分隔
     */
    private String attachmentUrls;

    /**
     * 发布部门 0-全总 1-省总 2-市州
     */
    private Integer fbbm;

    /**
     * 发布日期
     */
    private LocalDate fbrq;

    /**
     * 下架原因
     */
    private String offShelfReason;

    /**
     * 阅读量
     */
    private Integer readCount;
}
