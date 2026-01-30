package cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("gh_qx_dlzh")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhQxDlzhDO extends TenantBaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String yhzh;

    private String password;

    private String yhxm;

    private String yhbz;

    private String lxdh;

    private String yhyx;

    private String shxydm;

    private Integer yhxb;

    private String txdz;

    private Integer status;

    private String loginIp;

    private LocalDateTime loginDate;

}
