package cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bszn;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
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
     * 事项名称
     */
    private String sxmc;

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
     * 办理部门
     */
    private String blbm;

    /**
     * 下架原因(1:已失效政策,2:新政策替代)
     */
    private String xjyy;

    /**
     * 业务分类(1:缴费管理,2:返拨管理,3:退费管理,4:缓交管理)
     */
    private Integer ywfl;

    /**
     * 办理主体(1:全总工会,2:省总工会,3:基层工会,4:缴费单位)
     */
    private Integer blzt;

    /**
     * 发布时间
     */
    private java.time.LocalDate fbsj;

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

    /**
     * 阅读量
     */
    private Integer readCount;

    /**
     * 排名 (非数据库字段)
     */
    @TableField(exist = false)
    private Integer rank;

}
