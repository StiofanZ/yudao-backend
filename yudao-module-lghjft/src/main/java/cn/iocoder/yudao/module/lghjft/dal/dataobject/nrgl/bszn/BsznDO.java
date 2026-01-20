package cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bszn;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 办事指南 DO
 *
 * @author 芋道源码
 */
@TableName("gh_nrgl_bszn")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BsznDO extends BaseDO {

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
     * 0: 草稿, 1: 发布
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
     * 办理窗口
     */
    private String blck;

    /**
     * 咨询电话
     */
    private String zxdh;

    /**
     * 法定时限
     */
    private String fdsx;

    /**
     * 承诺时限
     */
    private String cnsx;

    /**
     * 收费标准
     */
    private String sfbz;

}
