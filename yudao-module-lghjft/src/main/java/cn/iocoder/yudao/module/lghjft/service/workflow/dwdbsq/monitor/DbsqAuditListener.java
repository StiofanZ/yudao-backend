package cn.iocoder.yudao.module.lghjft.service.workflow.dwdbsq.monitor;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.dwdbsq.WfDbsqMapper;
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

    @Component("DBSQAuditListener")
public class DbsqAuditListener implements TaskListener {

    @Resource
    private WfDbsqMapper wfDbsqMapper;
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

            WfDbsqDO data = wfDbsqMapper.selectOne(new LambdaQueryWrapper<WfDbsqDO>()
                    .eq(WfDbsqDO::getLcslId, processId));
            if (data == null) {
                return;
            }
            if ("Activity_yzgghfzr".equals(taskKey)) {
                data.setYzghfzr(nickname);
//                data.setSqrq(LocalDate.now());

                System.out.println( data.setYzghfzr(nickname));
                System.out.println(data.setYzghyj((String) localVars.get("Activity_yzghfzryj")));
            }
            if ("Activity_xzgghjbr".equals(taskKey)) {
                data.setXzgghjbr(nickname);
                data.setXzgghsprq(LocalDate.now());
            }
            if ("Activity_xzgghfzr".equals(taskKey)) {
                data.setXzgghspyj((String) localVars.get("Activity_xzghfzryj"));
                data.setXzgghfzr(nickname);
            }
            if ("Activity_szgghjbr".equals(taskKey)) {
                data.setSghjbr(nickname);
                data.setSghsprq(LocalDate.now());
            }
            if ("Activity_szgghfzr".equals(taskKey)) {
                data.setSghspyj((String) localVars.get("Activity_szghfzryj"));
                data.setSghfzr(nickname);
            }
            System.out.println(data);
            wfDbsqMapper.updateById(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
