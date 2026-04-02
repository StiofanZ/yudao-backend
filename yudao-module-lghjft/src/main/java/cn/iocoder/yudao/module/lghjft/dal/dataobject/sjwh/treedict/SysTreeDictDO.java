package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 树形字典 DO
 *
 * @author zhaofan
 */
@TableName("sys_tree_dict")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysTreeDictDO {

    /**
     * 主键ID
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典编码
     */
    private String code;
    /**
     * 结构类型 0=平级 1=树形
     */
    private String struType;
    /**
     * 是否系统参数 0=系统 1=自定义
     */
    private String isSysParam;
    /**
     * 备注
     */
    private String remark;
    /**
     * 租户ID
     */
    private String tenantId;
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
