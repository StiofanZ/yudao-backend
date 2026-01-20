package cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcjd;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 政策解读 DO
 *
 * @author 芋道源码
 */
@TableName("gh_nrgl_zcjd")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZcjdDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;

    /**
     * 父编号
     */
    private Long parentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     * 0: 未审核, 1: 已审核, 2: 已发布, 3: 已过期, 4: 已下架
     */
    private Integer status;

    /**
     * 发布部门编号
     */
    private Long deptId;

    /**
     * 可见范围
     * 1: 完全可见, 2: 下级可见, 3: 本级可见
     */
    private Integer kjfw;

    /**
     * 附件路径
     */
    private String fjlj;

    /**
     * 发布部门(0:全总,1:省总,2:市州)
     */
    private Integer fbbm;

    /**
     * 下架原因(1:已失效政策,2:新政策替代)
     */
    private String xjyy;

    /**
     * 原文件发布日期
     */
    private java.time.LocalDateTime fbrq;

    /**
     * 关联政策ID
     */
    private Long glzcId;

}
