package cn.iocoder.yudao.module.lghjft.service.workflow.wfjfhjsq.monitor;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq.WfJfhjSqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfjfhjsq.WfJfhjSqMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.util.Map;

/**
 * 主管工会审批节点监听器（修正版：去掉不存在的BpmProcessInstanceApi方法）
 */
@Component("managerUnionAuditListener") // 可视化配置的代理表达式需匹配此名称
public class ManagerUnionAuditListener implements TaskListener {


    @Resource
    private WfJfhjSqMapper wfJfhjSqMapper;

    @Override
    public void notify(DelegateTask delegateTask) {
        try {
            // 1. 核心：获取当前审批节点的所有数据（Flowable变量）
            Map<String, Object> vars = delegateTask.getVariables();
            // 2. 提取2个核心数据（根据你的表单字段名修改！）
            String opinion = (String) vars.get("manager_opinion"); // 审批意见（表单字段名）
            String leaderName = (String) vars.get("manager_leader_name"); // 审批负责人
            String handlerName = (String) vars.get("manager_handler_name"); // 审批经办人
            String financeLeader =  (String) vars.get("manager_finance_leader");//财务负责人
             // 3. 获取流程实例ID，关联业务表
            String processId = delegateTask.getProcessInstanceId();
       // 4. 查询你的业务表数据
            WfJfhjSqDO data = wfJfhjSqMapper.selectOne(
                    new LambdaQueryWrapper<WfJfhjSqDO>()
                            .eq(WfJfhjSqDO::getProcessInstanceId, processId)
            );
            if (data == null) return;
            System.out.println("查询到的业务表原始数据：id=" + data.getId() + "，processInstanceId=" + data.getProcessInstanceId());
            // 5. 写入你的业务表（只填核心字段）
            data.setManagerOpinion(opinion); // 审批意见写入业务表字段
            data.setManagerLeaderName(leaderName); // 审批负责人写入业务表字段
            data.setManagerHandlerName(handlerName);//审批经办人
            data.setManagerApproveTime(LocalDate.now()); // 审批时间
            data.setManagerFinanceLeader(financeLeader);
            // 6. 更新数据库
            wfJfhjSqMapper.updateById(data);

        } catch (Exception e) {
            // 异常仅打印，不影响流程
            e.printStackTrace();
        }
    }
}