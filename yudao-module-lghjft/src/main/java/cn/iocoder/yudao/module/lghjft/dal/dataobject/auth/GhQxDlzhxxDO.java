package cn.iocoder.yudao.module.lghjft.dal.dataobject.auth;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 用户账号信息 DO
 */
@TableName("gh_qx_dlzhxx")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhQxDlzhxxDO extends TenantBaseDO {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户账号
     */
    private String yhzh;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String yhnc;
    /**
     * 备注
     */
    private String bz;
    /**
     * 联系电话
     */
    private String lxdh;
    /**
     * 用户邮箱
     */
    private String yhyx;
    /**
     * 社会信用代码
     */
    private String shxydm;
    /**
     * 用户性别
     */
    private Integer yhxb;
    /**
     * 头像地址
     */
    private String txdz;
    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

}
