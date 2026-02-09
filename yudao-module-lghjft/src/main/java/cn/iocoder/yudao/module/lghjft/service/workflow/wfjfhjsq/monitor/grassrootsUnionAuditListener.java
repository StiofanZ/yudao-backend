//package cn.iocoder.yudao.module.lghjft.service.workflow.wfjfhjsq.monitor;
//
//import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq.WfJfhjSqDO;
//import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfjfhjsq.WfJfhjSqMapper;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import org.flowable.engine.delegate.TaskListener;
//import org.flowable.task.service.delegate.DelegateTask;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.Resource;
//
//import java.time.LocalDate;
//import java.util.Map;
//
///**
// * 省总工会审批节点监听器
// * 核心：提取省总工会审批数据，同步到业务表的省总工会专属字段
// */
//@Component("grassrootsUnionAuditListener") // 代理表达式：${provinceUnionAuditListener}
//public class grassrootsUnionAuditListener implements TaskListener {
//
//    @Resource
//    private WfJfhjSqMapper wfJfhjSqMapper;
//
//    @Override
//    public void notify(DelegateTask delegateTask) {
//        try {
//            // ========== 1. 打印流程基础信息（调试用） ==========
//            String processId = delegateTask.getProcessInstanceId();
//            String taskName = delegateTask.getName();
//            // ========== 2. 获取基层工会审批的所有变量 ==========
//            Map<String, Object> vars = delegateTask.getVariables();
//            // ========== 3. 提取基层工会核心审批数据==========
//            String opinion = (String) vars.get("grassroots_opinion");
//            // 基层工会审批负责人（表单字段名：province_leader_name）
//            String LeaderName = (String) vars.get("grassroots_leader");
//            // 省总基层工会审批经办人（表单字段名：province_handler_name）
//            String HandlerName = (String) vars.get("grassroots_handler");
//            // ========== 5. 查询业务表数据 ==========
//            WfJfhjSqDO data = wfJfhjSqMapper.selectOne(
//                    new LambdaQueryWrapper<WfJfhjSqDO>()
//                            .eq(WfJfhjSqDO::getProcessInstanceId, processId)
//            );
//            if (data == null) return;
//            // ========== 6. 写入基层工会专属业务字段 ==========
//            data.setGrassrootsOpinion(opinion); // 基层工会审批意见
//            data.setGrassrootsLeader(LeaderName); // 基层工会负责人
//            data.setGrassrootsHandler(HandlerName); // 基层工会经办人
//            data.setGrassrootsApproveTime(LocalDate.now()); // 审批时间
//            // ========== 7. 更新数据库 ==========
//            wfJfhjSqMapper.updateById(data);
//            System.out.println(wfJfhjSqMapper.updateById(data));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}