package cn.iocoder.yudao.module.lghjft.service.wfsqtfsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqKtfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqmxRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.wfsqtfsq.WfSqTfsqMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.WF_SQ_TFSQ_NOT_EXISTS;

/**
 * 申请-退费申请 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class WfSqTfsqServiceImpl implements WfSqTfsqService {

    public static final String PROCESS_KEY = "WF_SQ_TFSQ";

    @Resource
    private WfSqTfsqMapper wfSqTfsqMapper;
    @Resource
    private NsrxxMapper nsrxxMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;

    @Override
    public WfSqTfsqRespVO getWfSqTfsq(Long id) {
        WfSqTfsqDO tfsq = wfSqTfsqMapper.selectById(id);
        if (tfsq == null) {
            throw exception(WF_SQ_TFSQ_NOT_EXISTS);
        }
        List<WfSqTfsqmxDO> mxList = wfSqTfsqMapper.selectMxList(id);

        WfSqTfsqRespVO respVO = BeanUtils.toBean(tfsq, WfSqTfsqRespVO.class);
        respVO.setMxList(BeanUtils.toBean(mxList, WfSqTfsqmxRespVO.class));
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(List<WfSqTfsqSaveReqVO> list) {
        if (CollUtil.isEmpty(list)) {
            return null;
        }

        // 1. 创建申请单
        WfSqTfsqDO tfsq = WfSqTfsqDO.builder()
                .sqtflxDm(1) // 默认值，根据业务调整
                .status(0) // 待审批
                .build();
        wfSqTfsqMapper.insert(tfsq);

        // 2. 创建申请明细
        List<WfSqTfsqmxDO> mxs = list.stream().map(item -> WfSqTfsqmxDO.builder()
                .tfsqId(tfsq.getId())
                .spuuid(item.getSpuuid())
                .rkje(item.getKtfje()) // 入库金额即为可退费金额
                .tfsqJe(item.getTfje()) // 申请金额
                .shxydm(item.getShxydm())
                .nsrmc(item.getNsrmc())
                .skssqq(item.getSkssqq())
                .skssqz(item.getSkssqz())
                .build()).collect(Collectors.toList());

        // 循环插入
        mxs.forEach(wfSqTfsqMapper::insertMx);

        // 3. 发起流程
        String processInstanceId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(tfsq.getId()))
                        .setVariables(new HashMap<>()) // 可以根据需要传递变量
        );

        // 4. 更新流程实例编号
        wfSqTfsqMapper.updateById(new WfSqTfsqDO().setId(tfsq.getId()).setProcessInstanceId(processInstanceId));

        return tfsq.getId();
    }

    @Override
    public List<WfSqTfsqKtfxxRespVO> getKtfxxList(String djxh, LocalDate skssqq, LocalDate skssqz) {
        return wfSqTfsqMapper.getKtfxxList(djxh, skssqq, skssqz);
    }

}