package cn.iocoder.yudao.module.lghjft.service.workflow.wfsqhzjs.monitor;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf.WfHzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfsqhzjf.WfHzMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

/**
 * 通用审批监听器（同时处理主管工会+省总工会审批，自动区分节点）
 */

@Component("HZJNauditListener") // 一个监听器适配所有审批节点，不用写两个
public class UnionAuditListener implements TaskListener {

    @Resource
    private WfHzMapper wfHzMapper;

    @Override
    public void notify(DelegateTask delegateTask) {
        try {
            // 1. 获取核心上下文（关键：拿到当前节点的任务Key，判断是主管/省总）
            String processId = delegateTask.getProcessInstanceId();
            String taskKey = delegateTask.getTaskDefinitionKey(); // 节点的任务Key：managerAudit/provinceAudit
            // 重点：优先读「任务本地变量」（第二个节点的变量只在这里）
            Map<String, Object> localVars = delegateTask.getVariablesLocal();
//            System.out.println("当前节点[" + taskKey + "]的本地变量：" + localVars);
            // 2. 按节点Key适配取值（变量名和表单里的一致）
            WfHzDO data = wfHzMapper.selectOne(
                    new LambdaQueryWrapper<WfHzDO>()
                            .eq(WfHzDO::getProcessInstanceId, processId)
            );
            if (data == null) return;
            // 3. 主管审批节点取值
            if ("Activity_09jy0vr".equals(taskKey)) {
                String opinion = (String) localVars.get("manager_opinion");
                String leaderName = (String) localVars.get("manager_leader_name");
                String handlerName = (String) localVars.get("manager_handler_name");
                String ContactPhone = (String) localVars.get("manager_phone");
                data.setZgghsjy(opinion);//审批意见
                data.setZgghsfzr(leaderName);//审批负责人
                data.setZgghsjbr(handlerName);//审批经办人
                data.setZgghsjbrdh(ContactPhone);//审批经办人手机号
                data.setZgghsrq(LocalDate.now());//审批时间
            }
            // 4. 省总审批节点取值
            else if ("Activity_1u8wx1b".equals(taskKey)) {
                String opinion = (String) localVars.get("province_opinion");
                String leaderName = (String) localVars.get("province_leader_name");
                data.setSghzsjy(opinion);
                data.setSghsfzr(leaderName);
                data.setSghsrq(LocalDate.now());

            }
            // 5. 强制更新业务表（核心：不管变量在全局还是本地，只要拿到就写入）
            wfHzMapper.updateById(data);
            System.out.println("节点[" + taskKey + "]审批数据写入成功：" + data);
        } catch (Exception e) {
            System.err.println("审批监听器异常（节点：" + delegateTask.getTaskDefinitionKey() + "）");
            e.printStackTrace();
        }
   }
}