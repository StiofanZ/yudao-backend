package cn.iocoder.yudao.module.lghjft.service.workflow.wfjfhjsq.monitor;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq.WfJfhjSqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfjfhjsq.WfJfhjSqMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
@Component("PaymentUnionAuditListener") // 可视化配置的代理表达式需匹配此名称
public class PaymentUnionAuditListener implements TaskListener{
    @Resource
    private WfJfhjSqMapper wfJfhjSqMapper;
    @Override
    public void notify(DelegateTask delegateTask) {
        try {
            // 1. 获取核心上下文（关键：拿到当前节点的任务Key，判断是主管/省总）
            String processId = delegateTask.getProcessInstanceId();
            String taskKey = delegateTask.getTaskDefinitionKey(); // 节点的任务Key：managerAudit/provinceAudit
            // 重点：优先读「任务本地变量」（第二个节点的变量只在这里）
            Map<String, Object> localVars = delegateTask.getVariablesLocal();
            // 4. 查询你的业务表数据
            WfJfhjSqDO data = wfJfhjSqMapper.selectOne(
                    new LambdaQueryWrapper<WfJfhjSqDO>()
                            .eq(WfJfhjSqDO::getProcessInstanceId, processId)
            );

            if (data == null) return;
//            本单位负责人
            if("Activity_15t0upm".equals(taskKey)){
                String leaderName = (String) localVars.get("unit_leader");
                data.setUnitLeader(leaderName);
                data.setApplyDate(LocalDate.now());
            }
//            基层经办人
            if("Activity_0lbyva0".equals(taskKey)){
                String GHandler = (String) localVars.get("grassroots_handler");
                data.setGrassrootsHandler(GHandler);
            }
//           基层负责人
            if("Activity_0yodds0".equals(taskKey)){
                String opinion = (String) localVars.get("grassroots_opinion");
                data.setGrassrootsOpinion(opinion);
                String leader = (String) localVars.get("grassroots_leader");
                data.setGrassrootsLeader(leader);
                data.setGrassrootsApproveTime(LocalDate.now()); // 审批时间

            }
//            主管工会经办人
            if("Activity_17xs5bx".equals(taskKey)){
                String Handler = (String) localVars.get("manager_handler_name");
                data.setManagerHandlerName(Handler);
            }
//            主管工会负责人
            if("Activity_0v1dksm".equals(taskKey)){
                String opinion = (String) localVars.get("manager_opinion");
                data.setManagerOpinion(opinion);

                String leader = (String) localVars.get("manager_leader_name");
                data.setManagerLeaderName(leader);
                data.setManagerApproveTime(LocalDate.now()); // 审批时间
            }
//            主管工会财务负责人
            if("Activity_18n3e03".equals(taskKey)){
                String financeLeader = (String) localVars.get("manager_finance_leader");
                data.setManagerFinanceLeader(financeLeader);
            }
            // 6. 更新数据库
            wfJfhjSqMapper.updateById(data);
            // 5. 强制更新业务表（核心：不管变量在全局还是本地，只要拿到就写入）
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}