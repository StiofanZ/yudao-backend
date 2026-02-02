package cn.iocoder.yudao.module.lghjft.dal.dataobject.wftdfsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

@TableName("lghjft_wf_tdf_sq")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfTdfSqDO extends BaseDO {

    @TableId
    private Long id;

    // 申请信息
    private String shxydm;
    private String nsrmc;
    private String situationDesc;
    private String unitLeader;
    private String handler;
    private String contactPhone;
    private String accountName;
    private String bankName;
    private String accountNo;
    private String bankCode;
    private LocalDate applyDate;

    // 流程
    private String processInstanceId;
    // 主管工会
    private String managerOpinion;
    private String managerLeaderName;
    private String managerHandlerName;
    private LocalDate managerApproveTime;

    // 省总工会
    private String provinceOpinion;
    private String provinceLeaderName;
    private String provinceHandlerName;
    private LocalDate provinceApproveTime;
    private Integer refundMethod;
}