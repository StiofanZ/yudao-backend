package cn.iocoder.yudao.module.lghjft.service.workflow.wftdfsq.monitor;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wftdfsq.WfTdfSqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wftdfsq.WfTdfSqMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;

import java.time.LocalDate;
import java.util.Map;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 通用审批监听器（同时处理主管工会+省总工会审批，自动区分节点）
 */

@Component("TDFauditListener") // 一个监听器适配所有审批节点，不用写两个
public class UnionAuditListener implements TaskListener {

    @Resource
    private WfTdfSqMapper wfTdfSqMapper;
    @Resource
    private AdminUserService userService;

    @Override
    public void notify(DelegateTask delegateTask) {
        try {
            // 1. 获取核心上下文（关键：拿到当前节点的任务Key，判断是主管/省总）
            String processId = delegateTask.getProcessInstanceId();
            String taskKey = delegateTask.getTaskDefinitionKey();
            // 重点：优先读「任务本地变量」（第二个节点的变量只在这里）
            Map<String, Object> localVars = delegateTask.getVariablesLocal();
            //2.获取当前用户信息
            AdminUserDO user = userService.getUser(getLoginUserId());
            String nickname = user.getNickname();
            // 2. 按节点Key适配取值（变量名和你表单里的一致）
            WfTdfSqDO data = wfTdfSqMapper.selectOne(
                    new LambdaQueryWrapper<WfTdfSqDO>()
                            .eq(WfTdfSqDO::getProcessInstanceId, processId)
            );
            if (data == null) return;
            //本单位负责人审批
            if ("Activity_1nq1r9v".equals(taskKey)) {
                data.setUnitLeader(nickname);
            }
            // 3. 主管审批（经办人）
            if ("Activity_1q8wiw8".equals(taskKey)) {
                data.setManagerHandlerName(nickname);
                data.setManagerApproveTime(LocalDate.now());
            }
            // 3. 主管审批（负责人）
            if ("Activity_0mvjhw9".equals(taskKey)) {
                String Mopinion = (String) localVars.get("manager_opinion");
                data.setManagerOpinion(Mopinion);
                data.setManagerLeaderName(nickname);

            }
            // 4. 省总审批（经办人）
            if ("Activity_1mu9vhp".equals(taskKey)) {
                String refundMethodStr = (String) localVars.get("refundMethod");
                System.out.println(refundMethodStr);
                data.setProvinceHandlerName(nickname);
                data.setProvinceApproveTime(LocalDate.now());
                // 处理退款方式的类型转换（避免之前的ClassCastException）
                if (refundMethodStr != null && !refundMethodStr.isEmpty()) {
                    try {
                        data.setRefundMethod(Integer.parseInt(refundMethodStr));
                    } catch (NumberFormatException e) {
                        data.setRefundMethod(0);
                    }
                } else {
                    data.setRefundMethod(0);
                }

            }
            // 4. 省总审批（负责人）
            if ("Activity_1bbpeee".equals(taskKey)) {
                String opinion = (String) localVars.get("province_opinion");
                System.out.println(opinion);
                data.setProvinceOpinion(opinion);
                data.setProvinceLeaderName(nickname);

            }

            // 5. 强制更新业务表（核心：不管变量在全局还是本地，只要拿到就写入）
            wfTdfSqMapper.updateById(data);
            System.out.println("节点[" + taskKey + "]审批数据写入成功：" + data);

        } catch (Exception e) {
            System.err.println("审批监听器异常（节点：" + delegateTask.getTaskDefinitionKey() + "）");
            e.printStackTrace();
        }
    }
}