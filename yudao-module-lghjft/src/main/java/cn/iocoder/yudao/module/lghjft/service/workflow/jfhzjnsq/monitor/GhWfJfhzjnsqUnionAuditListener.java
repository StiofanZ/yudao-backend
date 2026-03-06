package cn.iocoder.yudao.module.lghjft.service.workflow.jfhzjnsq.monitor;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.GhWfJfhzjnsqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhzjnsq.GhWfJfhzjnsqMapper;
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

@Component("HZJNauditListener")
public class GhWfJfhzjnsqUnionAuditListener implements TaskListener {

    @Resource
    private GhWfJfhzjnsqMapper jfhzjnsqMapper;
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
            String mobile = user == null ? null : user.getMobile();

            GhWfJfhzjnsqDO data = jfhzjnsqMapper.selectOne(new LambdaQueryWrapper<GhWfJfhzjnsqDO>()
                    .eq(GhWfJfhzjnsqDO::getLcslId, processId));
            if (data == null) {
                return;
            }
            if ("Activity_17uvq8x".equals(taskKey)) {
                data.setDwfzr(nickname);
            }
            if ("Activity_1q28sd2".equals(taskKey)) {
                data.setZgghjbr(nickname);
                data.setZgghjbrdh(mobile);
                data.setZgghsprq(LocalDate.now());
            }
            if ("Activity_1rdldy4".equals(taskKey)) {
                data.setZgghspyj((String) localVars.get("manager_opinion"));
                data.setZgghfzr(nickname);
            } else if ("Activity_14hltqc".equals(taskKey)) {
                data.setSghspyj((String) localVars.get("province_opinion"));
                data.setSghfzr(nickname);
                data.setSghsprq(LocalDate.now());
            }
            jfhzjnsqMapper.updateById(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
