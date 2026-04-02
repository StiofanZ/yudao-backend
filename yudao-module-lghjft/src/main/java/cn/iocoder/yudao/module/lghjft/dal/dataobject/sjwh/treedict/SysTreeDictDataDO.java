package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 树形字典数据 DO
 *
 * @author zhaofan
 */
@TableName("sys_tree_dict_data")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysTreeDictDataDO {

    /**
     * 主键ID
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 所属字典编码（关联 sys_tree_dict.code）
     */
    private String treeDict;
    /**
     * 父节点ID，0=根节点
     */
    private String pid;
    /**
     * 数据编码
     */
    private String code;
    /**
     * 数据标签
     */
    private String label;
    /**
     * 层级编码
     */
    private String levelCode;
    /**
     * 层级深度
     */
    private Integer levelDepth;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 是否叶子节点 1=叶子
     */
    private String isLeaf;
    /**
     * 路径
     */
    private String path;
    /**
     * 图标
     */
    private String icon;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除标志 0=正常 其他=删除
     */
    @TableField("del_flag")
    private String delFlag;

}
