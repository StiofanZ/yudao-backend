package cn.iocoder.yudao.module.lghjft.service.workflow.tdfsq.monitor;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.tdfsq.GhWfTdfsqMapper;
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

@Component("TDFauditListener")
public class UnionAuditListener implements TaskListener {

    @Resource
    private GhWfTdfsqMapper tdfsqMapper;
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

            GhWfTdfsqDO data = tdfsqMapper.selectOne(new LambdaQueryWrapper<GhWfTdfsqDO>()
                    .eq(GhWfTdfsqDO::getLcslId, processId));
            if (data == null) {
                return;
            }
            if ("Activity_1nq1r9v".equals(taskKey)) {
                data.setDwfzr(nickname);
            }
            if ("Activity_1q8wiw8".equals(taskKey)) {
                data.setZgghjbr(nickname);
                data.setZgghsprq(LocalDate.now());
            }
            if ("Activity_0mvjhw9".equals(taskKey)) {
                data.setZgghspyj((String) localVars.get("manager_opinion"));
                data.setZgghfzr(nickname);
            }
            if ("Activity_1mu9vhp".equals(taskKey)) {
                data.setSghjbr(nickname);
                data.setSghsprq(LocalDate.now());
                String thfsStr = (String) localVars.get("thfs");
                if (thfsStr != null && !thfsStr.isBlank()) {
                    try {
                        data.setThfs(Integer.parseInt(thfsStr));
                    } catch (NumberFormatException ignore) {
                        data.setThfs(null);
                    }
                }
            }
            if ("Activity_1bbpeee".equals(taskKey)) {
                data.setSghspyj((String) localVars.get("province_opinion"));
                data.setSghfzr(nickname);
            }
            tdfsqMapper.updateById(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
