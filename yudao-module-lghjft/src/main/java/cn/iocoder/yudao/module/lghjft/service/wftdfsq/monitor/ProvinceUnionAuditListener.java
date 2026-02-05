//    package cn.iocoder.yudao.module.lghjft.service.wftdfsq.monitor;
//
//    import cn.iocoder.yudao.module.lghjft.dal.dataobject.wftdfsq.WfTdfSqDO;
//    import cn.iocoder.yudao.module.lghjft.dal.mysql.wftdfsq.WfTdfSqMapper;
//    import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//    import org.flowable.engine.delegate.TaskListener;
//    import org.flowable.task.service.delegate.DelegateTask;
//    import org.springframework.stereotype.Component;
//
//    import jakarta.annotation.Resource;
//    import java.time.LocalDate;
//    import java.util.Map;
//
//    /**
//     * 省总工会审批节点监听器
//     * 核心：提取省总工会审批数据，同步到业务表的省总工会专属字段
//     */
//    @Component("provinceUnionAuditListener") // 代理表达式：${provinceUnionAuditListener}
//    public class ProvinceUnionAuditListener implements TaskListener {
//
//        @Resource
//        private WfTdfSqMapper wfTdfSqMapper;
//
//        @Override
//        public void notify(DelegateTask delegateTask) {
//            try {
//                // ========== 1. 打印流程基础信息（调试用） ==========
//                String processId = delegateTask.getProcessInstanceId();
//                String taskName = delegateTask.getName();
//                System.out.println("===== 省总工会审批监听器触发 =====");
//                System.out.println("流程实例ID：" + processId);
//                System.out.println("当前审批节点：" + taskName);
//
//                // ========== 2. 获取省总工会审批的所有变量 ==========
//                Map<String, Object> vars = delegateTask.getVariables();
//                System.out.println("省总工会监听到的所有审批数据：" + vars);
//                Map<String, Object> localVars = delegateTask.getVariablesLocal();
//                System.out.println("任务本地变量：" + localVars);
//                // ========== 3. 提取省总工会核心审批数据（根据你的表单字段名修改！） ==========
//                // 省总工会审批意见（表单字段名：province_opinion）
//                String opinion = (String) vars.get("province_opinion");
//                // 省总工会审批负责人（表单字段名：province_leader_name）
//                String LeaderName = (String) vars.get("province_leader_name");
//                // 省总工会审批经办人（表单字段名：province_handler_name）
//                String HandlerName = (String) vars.get("province_handler_name");
//                // 省总工会审批时间（表单字段名：province_approve_time）
//                String approveTimeStr = (String) vars.get("province_approve_time"); // 审批时间
//                Integer refundMethod =  (Integer) vars.get("refund_method");
//                LocalDate approveTime = null;
//                // 非空判断 + 解析（日期格式要和字符串匹配，这里是yyyy-MM-dd）
//                if (approveTimeStr != null && !approveTimeStr.isEmpty()) {
//                    approveTime = LocalDate.parse(approveTimeStr);
//                }
//                // ========== 5. 查询业务表数据 ==========
//                WfTdfSqDO data = wfTdfSqMapper.selectOne(
//                        new LambdaQueryWrapper<WfTdfSqDO>()
//                                .eq(WfTdfSqDO::getProcessInstanceId, processId)
//                );
//                if (data == null) return;
//                System.out.println("省总工会审批：查询到的业务表数据：id=" + data.getId());
//
//                // ========== 6. 写入省总工会专属业务字段 ==========
//                data.setProvinceOpinion(opinion); // 省总工会审批意见
//                data.setProvinceLeaderName(LeaderName); // 省总工会负责人
//                data.setProvinceHandlerName(HandlerName); // 省总工会经办人
//                data.setProvinceApproveTime(approveTime); // 审批时间（也可从vars取）
//                data.setRefundMethod(refundMethod);
//                System.out.println( data.setProvinceOpinion(opinion));
//                System.out.println( data.setProvinceLeaderName(LeaderName));
//                System.out.println( data.setProvinceHandlerName(HandlerName));
//                System.out.println( data.setProvinceApproveTime(approveTime));
//                System.out.println( data.setRefundMethod(refundMethod));
//                // ========== 7. 更新数据库 ==========
//                wfTdfSqMapper.updateById(data);
//                System.out.println(wfTdfSqMapper.updateById(data));
//            } catch (Exception e) {
//                System.err.println("===== 省总工会审批监听器异常 =====");
//                e.printStackTrace();
//            }
//        }
//    }