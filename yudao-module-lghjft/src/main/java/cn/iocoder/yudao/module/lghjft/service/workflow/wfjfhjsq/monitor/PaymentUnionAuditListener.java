package cn.iocoder.yudao.module.lghjft.service.workflow.wfjfhjsq.monitor;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq.WfJfhjSqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfjfhjsq.WfJfhjSqMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;


@Component("PaymentUnionAuditListener") // 可视化配置的代理表达式需匹配此名称
public class PaymentUnionAuditListener implements TaskListener {
    @Resource
    private WfJfhjSqMapper wfJfhjSqMapper;
    @Resource
    private AdminUserService userService;
    @Override
    public void notify(DelegateTask delegateTask) {
        try {
            // 1. 获取核心上下文（关键：拿到当前节点的任务Key，判断是主管/省总）
            String processId = delegateTask.getProcessInstanceId();
            String taskKey = delegateTask.getTaskDefinitionKey(); // 节点的任务Key：managerAudit/provinceAudit
            Map<String, Object> localVars = delegateTask.getVariablesLocal();
            //2.获取当前用户信息
            AdminUserDO user = userService.getUser(getLoginUserId());
            String nickname = user.getNickname();

            // 4. 查询你的业务表数据
            WfJfhjSqDO data = wfJfhjSqMapper.selectOne(
                    new LambdaQueryWrapper<WfJfhjSqDO>()
                            .eq(WfJfhjSqDO::getProcessInstanceId, processId)
            );
            if (data == null) return;
//            本单位负责人
            if ("Activity_00yh6va".equals(taskKey)) {
//                String leaderName = (String) localVars.get("unit_leader");
                data.setUnitLeader(nickname);
                data.setApplyDate(LocalDate.now());
            }
//            基层经办人
            if ("Activity_0usl1ld".equals(taskKey)) {
//                String GHandler = (String) localVars.get("grassroots_handler");
                data.setGrassrootsHandler(nickname);
                data.setGrassrootsApproveTime(LocalDate.now()); // 审批时间

            }
//           基层负责人
            if ("Activity_0e4r9jx".equals(taskKey)) {
                String opinion = (String) localVars.get("grassroots_opinion");
                data.setGrassrootsOpinion(opinion);
//                String leader = (String) localVars.get("grassroots_leader");
                data.setGrassrootsLeader(nickname);

            }
//            主管工会经办人
            if ("Activity_1wfoem6".equals(taskKey)) {
//                String Handler = (String) localVars.get("manager_handler_name");
                data.setManagerHandlerName(nickname);
                data.setManagerApproveTime(LocalDate.now()); // 审批时间

            }
//            主管工会负责人
            if ("Activity_1392cn1".equals(taskKey)) {
                String opinion = (String) localVars.get("manager_opinion");
                data.setManagerOpinion(opinion);

//                String leader = (String) localVars.get("manager_leader_name");
                data.setManagerLeaderName(nickname);
            }
//            主管工会财务负责人
            if ("Activity_0c1frae".equals(taskKey)) {
//                String financeLeader = (String) localVars.get("manager_finance_leader");
                data.setManagerFinanceLeader(nickname);
            }
            // 6. 更新数据库
            wfJfhjSqMapper.updateById(data);
            // 5. 强制更新业务表（核心：不管变量在全局还是本地，只要拿到就写入）
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}