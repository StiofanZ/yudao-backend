package cn.iocoder.yudao.module.lghjft.service.workflow.wfjfhjsq;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq.vo.WfJfhjSqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq.WfJfhjSqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfjfhjsq.WfJfhjSqMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * 工会经费缓缴申请 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated

public class WfJfhjSqServiceImpl implements WfJfhjSqService {
    public static final String PROCESS_KEY = "WF_SQ_JFHJSQ";
    @Resource
    private WfJfhjSqMapper wfJfhjSqMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;

    @Override
//    有异常进行回滚
    @Transactional(rollbackFor = Exception.class)
    public Long createWfJfhjSq(WfJfhjSqSaveReqVO createReqVO) {
        // 1. 插入主表数据
        WfJfhjSqDO wfJfhjSq = BeanUtils.toBean(createReqVO, WfJfhjSqDO.class);
        Long loginUserId = WebFrameworkUtils.getLoginUserId();
        wfJfhjSq.setApplyDate(LocalDate.now());
        wfJfhjSq.setUpdater(String.valueOf(loginUserId));
        wfJfhjSq.setCreateTime(LocalDateTime.now()); // 补充创建时间（如果DO里有该字段）
        wfJfhjSq.setUpdateTime(LocalDateTime.now()); // 补充更新时间（如果DO里有该字段）
        wfJfhjSqMapper.insert(wfJfhjSq);
        // 3. 启动流程
        String processInstanceId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(wfJfhjSq.getId()))
                        .setVariables(new HashMap<>())
        );

        // 4. 更新流程实例ID
        wfJfhjSqMapper.updateById(new WfJfhjSqDO()
                .setId(wfJfhjSq.getId())
                .setProcessInstanceId(processInstanceId));
        // 返回
        return wfJfhjSq.getId();
    }


    @Override
    public WfJfhjSqDO getWfJfhjSq(Long id) {
        WfJfhjSqDO wfJfhjSq = wfJfhjSqMapper.selectById(id);
        if (wfJfhjSq == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_JFHJ_SQ_NOT_EXISTS);
        }
        return wfJfhjSq;
    }

}
