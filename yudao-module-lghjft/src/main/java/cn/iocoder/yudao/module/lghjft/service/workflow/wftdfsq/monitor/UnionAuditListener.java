package cn.iocoder.yudao.module.lghjft.service.workflow.wftdfsq.monitor;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wftdfsq.WfTdfSqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wftdfsq.WfTdfSqMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.util.Map;

/**
 * 通用审批监听器（同时处理主管工会+省总工会审批，自动区分节点）
 */

@Component("TDFauditListener") // 一个监听器适配所有审批节点，不用写两个
public class UnionAuditListener implements TaskListener {

    @Resource
    private WfTdfSqMapper wfTdfSqMapper;

    @Override
    public void notify(DelegateTask delegateTask) {
        try {
            // 1. 获取核心上下文（关键：拿到当前节点的任务Key，判断是主管/省总）
            String processId = delegateTask.getProcessInstanceId();
            String taskKey = delegateTask.getTaskDefinitionKey(); // 节点的任务Key：managerAudit/provinceAudit
            // 重点：优先读「任务本地变量」（第二个节点的变量只在这里）
            Map<String, Object> localVars = delegateTask.getVariablesLocal();
            // 2. 按节点Key适配取值（变量名和你表单里的一致）
            WfTdfSqDO data = wfTdfSqMapper.selectOne(
                    new LambdaQueryWrapper<WfTdfSqDO>()
                            .eq(WfTdfSqDO::getProcessInstanceId, processId)
            );
            if (data == null) return;
            //本单位负责人审批
            if("Activity_11dpxw7".equals(taskKey)) {
                String leaderName = (String) localVars.get("unit_leader");
                data.setUnitLeader(leaderName);
            }
            // 3. 主管审批（经办人）
            if("Activity_07t08zq".equals(taskKey)){
                String handlerName = (String) localVars.get("manager_handler_name");
                data.setManagerHandlerName(handlerName);

            }
            // 3. 主管审批（负责人）
            if ("Activity_0snr8kv".equals(taskKey)) {
                String MleaderName = (String) localVars.get("manager_leader_name");
                String Mopinion = (String) localVars.get("manager_opinion");
                System.out.println(MleaderName);
                System.out.println(Mopinion);
                data.setManagerOpinion(Mopinion);
                data.setManagerLeaderName(MleaderName);
                data.setManagerApproveTime(LocalDate.now());
            }
            // 4. 省总审批（经办人）
            if("Activity_0tltjq9".equals(taskKey)){
                String PhandlerName = (String) localVars.get("province_handler_name");
                String refundMethodStr = (String) localVars.get("refund_method");
                data.setProvinceHandlerName(PhandlerName);

                // 处理退款方式的类型转换（避免之前的ClassCastException）
                if (refundMethodStr != null && !refundMethodStr.isEmpty()) {
                    try {
                        data.setRefundMethod(Integer.parseInt(refundMethodStr));
                    } catch (NumberFormatException e) {
                        data.setRefundMethod(0); // 默认值
                    }
                }
            }
            // 4. 省总审批（负责人）
         if ("Activity_0fdnky3".equals(taskKey)) {
                String opinion = (String) localVars.get("province_opinion");
                String leaderName = (String) localVars.get("province_leader_name");
               //传入数据
                data.setProvinceOpinion(opinion);
                data.setProvinceLeaderName(leaderName);
               data.setProvinceApproveTime(LocalDate.now());
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