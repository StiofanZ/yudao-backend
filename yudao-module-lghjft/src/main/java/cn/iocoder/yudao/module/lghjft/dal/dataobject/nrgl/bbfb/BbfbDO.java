package cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bbfb;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

/**
 * 版本发布 DO
 *
 * @author 芋道源码
 */
@TableName("gh_nrgl_bbfb")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BbfbDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;

    /**
     * 版本号
     */
    private String version;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     * 0: 草稿, 1: 已发布
     */
    private Integer status;

    /**
     * 发布部门编号
     */
    private Long deptId;

    /**
     * 发布时间
     */
    private LocalDate fbsj;

}
