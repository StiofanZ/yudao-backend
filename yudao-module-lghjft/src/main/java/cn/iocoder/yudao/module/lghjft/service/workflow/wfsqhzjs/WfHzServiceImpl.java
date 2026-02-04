package cn.iocoder.yudao.module.lghjft.service.workflow.wfsqhzjs;

import cn.hutool.core.collection.CollUtil;
//import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzPageReqVO;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf.WfHzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf.WfHzmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfsqhzjf.WfHzMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfsqhzjf.WfHzmxMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

/**
 * 工会经费汇总缴纳申请表（主表） Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class WfHzServiceImpl implements WfHzService {
    public static final String PROCESS_KEY = "WF_SQ_HZJNSQ";
    @Resource
    private WfHzmxMapper wfHzmxMapper;
    @Resource
    private WfHzMapper wfHzMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;
    @Override
    public Long createWfHz(WfHzSaveReqVO createReqVO) {
        // 1. 插入主表数据
        WfHzDO wfHz = BeanUtils.toBean(createReqVO, WfHzDO.class);
        Long loginUserId = WebFrameworkUtils.getLoginUserId();
        wfHz.setUpdater(String.valueOf(loginUserId));
        wfHz.setSqrq(LocalDate.now());//        申请时间
        wfHz.setCreateTime(LocalDateTime.now()); // 补充创建时间（如果DO里有该字段）
        wfHz.setUpdateTime(LocalDateTime.now()); // 补充更新时间（如果DO里有该字段）
        wfHzMapper.insert(wfHz);
        Long mainId = wfHz.getId(); // 获取主表自增ID

        // 2. 批量插入明细表数据（核心：补充的明细表存储逻辑）
        List<WfHzmxSaveReqVO> detailVOList = createReqVO.getDetailList();
        if (!CollectionUtils.isEmpty(detailVOList)) {
            // 把明细表VO转换成DO，并绑定主表ID、补充公共字段
            List<WfHzmxDO> detailDOList = Lists.newArrayList();
            for (WfHzmxSaveReqVO detailVO : detailVOList) {
                WfHzmxDO detailDO = BeanUtils.toBean(detailVO, WfHzmxDO.class);
                // 绑定主表外键（关键：关联主表）
                detailDO.setHzId(mainId);
                // 补充公共字段（创建人、创建/更新时间）
                detailDO.setCreateTime(LocalDateTime.now());
                detailDO.setUpdateTime(LocalDateTime.now());
                detailDOList.add(detailDO);
            }
            // 批量插入明细表（避免循环单插，提升性能）
            wfHzmxMapper.insertBatch(detailDOList);
        }

        // 3. 启动流程
        String processInstanceId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(wfHz.getId()))
                        .setVariables(new HashMap<>())
        );

        // 4. 更新流程实例ID
        wfHzMapper.updateById(new WfHzDO()
                .setId(wfHz.getId())
                .setProcessInstanceId(processInstanceId));
        return wfHz.getId();
    }


    @Override
    public WfHzDO getDetail(Long id) {
//        // 1. 查询主表DO
//        WfHzDO mainDO = wfHzMapper.selectById(id);
//        if (mainDO == null) {
//            throw new BusinessException(ErrorCode.NOT_FOUND);
//        }
//
//        // 2. 查询该主表下的所有明细表DO
//        List<WfHzmxDO> detailDOList = wfHzmxMapper.selectList(Wrappers.lambdaQuery(WfHzmxDO.class)
//                .eq(WfHzmxDO::getHzId, id));
//
//        // 3. DO转RespVO
//        WfHzRespVO mainRespVO = BeanUtils.toBean(mainDO, WfHzRespVO.class);
//        // 转换明细表DO为明细表RespVO
//        List<WfHzmxRespVO> detailRespVOList = BeanUtils.toBean(detailDOList, WfHzmxRespVO.class);
//        // 把明细表列表设置到主表RespVO中
//        mainRespVO.setDetailList(detailRespVOList);



        return wfHzMapper.selectById(id);
    }

}