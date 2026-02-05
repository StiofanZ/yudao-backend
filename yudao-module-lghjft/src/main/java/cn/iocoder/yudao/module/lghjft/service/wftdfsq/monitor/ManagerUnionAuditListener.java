//package cn.iocoder.yudao.module.lghjft.service.wftdfsq.monitor;
//
//import cn.iocoder.yudao.module.lghjft.dal.dataobject.wftdfsq.WfTdfSqDO;
//import cn.iocoder.yudao.module.lghjft.dal.mysql.wftdfsq.WfTdfSqMapper;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import org.flowable.engine.delegate.TaskListener;
//import org.flowable.task.service.delegate.DelegateTask;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.Resource;
//import java.time.LocalDate;
//import java.util.Map;
//
///**
// * 主管工会审批节点监听器（修正版：去掉不存在的BpmProcessInstanceApi方法）
// */
//@Component("managerUnionAuditListener") // 可视化配置的代理表达式需匹配此名称
//public class ManagerUnionAuditListener implements TaskListener {
//
//
//    @Resource
//    private WfTdfSqMapper wfTdfSqMapper;
//
//    @Override
//    public void notify(DelegateTask delegateTask) {
//        try {
//            // 1. 核心：获取当前审批节点的所有数据（Flowable变量）
//            Map<String, Object> vars = delegateTask.getVariables();
//
//            // ========== 打印2：监听到的所有审批变量（完整数据） ==========
//            System.out.println("监听到的所有审批数据：" + vars);
//
//            // 2. 提取2个核心数据（根据你的表单字段名修改！）
//            String opinion = (String) vars.get("manager_opinion"); // 审批意见（表单字段名）
//            String leaderName = (String) vars.get("manager_leader_name"); // 审批负责人
//            String handlerName = (String) vars.get("manager_handler_name"); // 审批经办人
//            String approveTimeStr = (String) vars.get("manager_approve_time"); // 审批时间
//            LocalDate approveTime = null;
//            // 非空判断 + 解析（日期格式要和字符串匹配，这里是yyyy-MM-dd）
//            if (approveTimeStr != null && !approveTimeStr.isEmpty()) {
//                approveTime = LocalDate.parse(approveTimeStr);
//
//            }
//
//
//            // 3. 获取流程实例ID，关联你的业务表
//            String processId = delegateTask.getProcessInstanceId();
//       // 4. 查询你的业务表数据
//            WfTdfSqDO data = wfTdfSqMapper.selectOne(
//                    new LambdaQueryWrapper<WfTdfSqDO>()
//                            .eq(WfTdfSqDO::getProcessInstanceId, processId)
//            );
//            if (data == null) return;
//            System.out.println("查询到的业务表原始数据：id=" + data.getId() + "，processInstanceId=" + data.getProcessInstanceId());
//            // 5. 写入你的业务表（只填核心字段）
//            data.setManagerOpinion(opinion); // 审批意见写入业务表字段
//            data.setManagerLeaderName(leaderName); // 审批负责人写入业务表字段
//            data.setManagerHandlerName(handlerName);//审批经办人
//            data.setManagerApproveTime(approveTime); // 审批时间（也可从vars取）
//            // 6. 更新数据库
//            wfTdfSqMapper.updateById(data);
//
//        } catch (Exception e) {
//            // 异常仅打印，不影响流程
//            e.printStackTrace();
//        }
//    }
//}