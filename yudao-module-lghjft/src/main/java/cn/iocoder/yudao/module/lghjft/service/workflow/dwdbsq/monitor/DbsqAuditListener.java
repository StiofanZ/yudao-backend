package cn.iocoder.yudao.module.lghjft.service.workflow.dwdbsq.monitor;

import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxBaseVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.dwdbsq.WfDbsqMapper;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Component("DBSQAuditListener")
public class DbsqAuditListener implements TaskListener {

    @Resource
    private WfDbsqMapper wfDbsqMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private JcxxService jcxxService;

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("=== 所有流程变量：" + delegateTask.getVariables());
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
                data.setYzghyj((String) localVars.get("Activity_yzghfzryj"));
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
//            if ("Activity_szgghfzr".equals(taskKey)) {
//                data.setSghspyj((String) localVars.get("Activity_szghfzryj"));
//                data.setSghfzr(nickname);
//            }
            // ===================== 最后一个节点：判断通过/驳回 =====================
            if ("Activity_szgghfzr".equals(taskKey)) {
                data.setSghspyj((String) localVars.get("Activity_szghfzryj"));
                data.setSghfzr(nickname);
                //通过判断：TASK_STATUS=2
                Integer taskStatus = (Integer) delegateTask.getVariable("TASK_STATUS");
                boolean isPass = (taskStatus != null && taskStatus == 2);

                if (isPass) {
                    System.out.println("========== 最后节点审批通过，执行调拨 ==========");
                    doAllocation(delegateTask);
                } else {
                    System.out.println("========== 驳回，不执行调拨 ==========");
                }
            }
            System.out.println(data);
            wfDbsqMapper.updateById(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//单位调拨接口
    private void doAllocation(DelegateTask delegateTask) {
        Object djxhObj = delegateTask.getVariable("djxh");
        Object newDeptIdObj = delegateTask.getVariable("newDeptId");
        Object hyghbzObj = delegateTask.getVariable("hyghbz");

        String djxh = djxhObj != null ? djxhObj.toString() : null;
        String newDeptId = newDeptIdObj != null ? newDeptIdObj.toString() : null;
        String hyghbz = hyghbzObj != null ? hyghbzObj.toString() : null;
        System.out.println("========== +"+djxh +"==========");
        System.out.println("========== +"+newDeptId +"==========");
        System.out.println("========== +"+hyghbz +"==========");
        if (djxh == null || newDeptId == null || "null".equals(newDeptId)) {
            throw new RuntimeException("缺少调拨参数");
        }

        JcxxBaseVO vo = new JcxxBaseVO();
        vo.setDeptId(newDeptId);
        vo.setHyghbz(hyghbz != null ? hyghbz : newDeptId);
        vo.setDjxhs(Collections.singletonList(djxh));
        jcxxService.allocationJcxx(vo);

        // 3. 组装提示信息给前端
        DeptDO dept = deptService.getDept(Long.valueOf(newDeptId));
        String deptName = dept != null ? dept.getName() : "未知工会";
        String msg = "调拨完成，新主管工会：" + deptName + "，未拨付经费已迁移";
        delegateTask.setVariable("reason", msg);

    }
}
