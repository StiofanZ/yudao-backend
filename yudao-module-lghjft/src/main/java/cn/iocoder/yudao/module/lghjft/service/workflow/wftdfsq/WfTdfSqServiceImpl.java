package cn.iocoder.yudao.module.lghjft.service.workflow.wftdfsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
//import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqApproveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo.WfTdfSqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo.WfTdfSqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.wftdfsq.vo.WfTdfSqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wftdfsq.WfTdfSqAttachmentDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wftdfsq.WfTdfSqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wftdfsq.WfTdfSqAttachmentMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wftdfsq.WfTdfSqMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public  class WfTdfSqServiceImpl implements WfTdfSqService {

    public static final String PROCESS_KEY = "WF_SQ_TDFSQ";

    @Resource
    private WfTdfSqMapper wfTdfSqMapper;
    @Resource
    private WfTdfSqAttachmentMapper attachmentMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;
    @Override
    public WfTdfSqRespVO getDetail(Long id) {
        // ========== 新增：打印用户ID完整链路 ==========
        // 1. 打印工具类获取的原始用户ID（未转换）
        Long rawLoginUserId = WebFrameworkUtils.getLoginUserId();
        // 2. 转换为字符串后的结果
        String creatorStr = String.valueOf(rawLoginUserId);
        // 1. 查询主表
        WfTdfSqDO main = wfTdfSqMapper.selectById(id);
        if (main == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_TDF_SQ_NOT_EXISTS);
        }

        // 2. 查询附件（移除deleted条件，因为表中无该字段）
        List<WfTdfSqAttachmentDO> attachments = attachmentMapper.selectList(
                new LambdaQueryWrapper<WfTdfSqAttachmentDO>()
                        .eq(WfTdfSqAttachmentDO::getApplyId, id) // 仅保留apply_id关联
        );

        // 3. 转换主表
        WfTdfSqRespVO vo = BeanUtils.toBean(main, WfTdfSqRespVO.class);

        // 4. 按类型提取URL，初始化空值避免前端报错
        vo.setVoucherUrl(""); // 默认空字符串
        vo.setPayrollUrl("");
        vo.setLicenseUrl("");
        for (WfTdfSqAttachmentDO att : attachments) {
            if (att.getFileType() == null) continue; // 空值防护
            if ("voucher".equals(att.getFileType())) {
                vo.setVoucherUrl(att.getFileUrl());
            } else if ("payroll".equals(att.getFileType())) {
                vo.setPayrollUrl(att.getFileUrl());
            } else if ("license".equals(att.getFileType())) {
                vo.setLicenseUrl(att.getFileUrl());
            }
        }

        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(@Valid WfTdfSqSaveReqVO req) {
        // 1. 保存主表
        WfTdfSqDO main = BeanUtils.toBean(req, WfTdfSqDO.class);
        main.setCreator(String.valueOf(WebFrameworkUtils.getLoginUserId()));
        main.setApplyDate(LocalDate.now());
        wfTdfSqMapper.insert(main);

        // 2. 保存附件（仅新增部分）
        if (CollUtil.isNotEmpty(req.getAttachments())) {
            List<WfTdfSqAttachmentDO> list = req.getAttachments().stream()
                    .map(item -> {
                        WfTdfSqAttachmentDO att = new WfTdfSqAttachmentDO();
                        att.setApplyId(main.getId());
                        att.setFileType(item.getType());
                        att.setFileUrl(item.getFileUrl());
                        att.setCreator(String.valueOf(WebFrameworkUtils.getLoginUserId()));
                        return att;
                    })
                    .collect(Collectors.toList());
            attachmentMapper.insertBatch(list);
        }

        // 3. 启动流程
        String processInstanceId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(main.getId()))
                        .setVariables(new HashMap<>())
        );

        // 4. 更新流程实例ID
        wfTdfSqMapper.updateById(new WfTdfSqDO()
                .setId(main.getId())
                .setProcessInstanceId(processInstanceId));

        return main.getId();
    }
    @Override
    public PageResult<WfTdfSqDO> getSelfPage(Long userId, WfTdfSqAppPageReqVO pageReqVO) {
        return wfTdfSqMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<WfTdfSqDO>()
                .eq(WfTdfSqDO::getCreator, userId)
                .orderByDesc(WfTdfSqDO::getId));
    }
}
