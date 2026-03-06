package cn.iocoder.yudao.module.lghjft.service.workflow.jfhjsq.monitor;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhjsq.GhWfJfhjsqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhjsq.GhWfJfhjsqMapper;
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

@Component("PaymentUnionAuditListener")
public class PaymentUnionAuditListener implements TaskListener {

    @Resource
    private GhWfJfhjsqMapper jfhjsqMapper;
    @Resource
    private AdminUserService userService;

    @Override
    public void notify(DelegateTask delegateTask) {
        try {
            String processId = delegateTask.getProcessInstanceId();
            String taskKey = delegateTask.getTaskDefinitionKey();
            Map<String, Object> localVars = delegateTask.getVariablesLocal();
            AdminUserDO user = userService.getUser(getLoginUserId());
            String nickname = user == null ? null : user.getNickname();

            GhWfJfhjsqDO data = jfhjsqMapper.selectOne(new LambdaQueryWrapper<GhWfJfhjsqDO>()
                    .eq(GhWfJfhjsqDO::getLcslId, processId));
            if (data == null) {
                return;
            }
            if ("Activity_00yh6va".equals(taskKey)) {
                data.setDwfzr(nickname);
                data.setSqrq(LocalDate.now());
            }
            if ("Activity_0usl1ld".equals(taskKey)) {
                data.setJcghjbr(nickname);
                data.setJcghgzrq(LocalDate.now());
            }
            if ("Activity_0e4r9jx".equals(taskKey)) {
                data.setJcghyj((String) localVars.get("grassroots_opinion"));
                data.setJcghfzr(nickname);
            }
            if ("Activity_1wfoem6".equals(taskKey)) {
                data.setZgghjbr(nickname);
                data.setZgghsprq(LocalDate.now());
            }
            if ("Activity_1392cn1".equals(taskKey)) {
                data.setZgghspyj((String) localVars.get("manager_opinion"));
                data.setZgghfzr(nickname);
            }
            if ("Activity_0c1frae".equals(taskKey)) {
                data.setZgghcwfzr(nickname);
            }
            jfhjsqMapper.updateById(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
