package cn.gszs.yudao.module.lghjft.service.wfsqtfsq;

import cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqPageReqVO;
import cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqSaveReqVO;
import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqDO;
import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqmxDO;
import cn.gszs.yudao.module.lghjft.dal.mysql.wfsqtfsq.WfSqTfsqMapper;
import cn.gszs.yudao.module.lghjft.dal.mysql.wfsqtfsq.WfSqTfsqmxMapper;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.gszs.yudao.module.lghjft.enums.ErrorCodeConstants.WF_SQ_TFSQ_NOT_EXISTS;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;

/**
 * 申请-退费申请 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class WfSqTfsqServiceImpl implements WfSqTfsqService {

    @Resource
    private WfSqTfsqMapper wfSqTfsqMapper;
    @Resource
    private WfSqTfsqmxMapper wfSqTfsqmxMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createWfSqTfsq(WfSqTfsqSaveReqVO createReqVO) {
        // 插入
        WfSqTfsqDO wfSqTfsq = BeanUtils.toBean(createReqVO, WfSqTfsqDO.class);
        wfSqTfsqMapper.insert(wfSqTfsq);


        // 插入子表
        createWfSqTfsqmxList(wfSqTfsq.getId(), createReqVO.getWfSqTfsqmxs());
        // 返回
        return wfSqTfsq.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWfSqTfsq(WfSqTfsqSaveReqVO updateReqVO) {
        // 校验存在
        validateWfSqTfsqExists(updateReqVO.getId());
        // 更新
        WfSqTfsqDO updateObj = BeanUtils.toBean(updateReqVO, WfSqTfsqDO.class);
        wfSqTfsqMapper.updateById(updateObj);

        // 更新子表
        updateWfSqTfsqmxList(updateReqVO.getId(), updateReqVO.getWfSqTfsqmxs());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWfSqTfsq(Long id) {
        // 校验存在
        validateWfSqTfsqExists(id);
        // 删除
        wfSqTfsqMapper.deleteById(id);

        // 删除子表
        deleteWfSqTfsqmxById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWfSqTfsqListByIds(List<Long> ids) {
        // 删除
        wfSqTfsqMapper.deleteByIds(ids);

        // 删除子表
        deleteWfSqTfsqmxByIds(ids);
    }


    private void validateWfSqTfsqExists(Long id) {
        if (wfSqTfsqMapper.selectById(id) == null) {
            throw exception(WF_SQ_TFSQ_NOT_EXISTS);
        }
    }

    @Override
    public WfSqTfsqDO getWfSqTfsq(Long id) {
        return wfSqTfsqMapper.selectById(id);
    }

    @Override
    public PageResult<WfSqTfsqDO> getWfSqTfsqPage(WfSqTfsqPageReqVO pageReqVO) {
        return wfSqTfsqMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（申请-退费申请明细） ====================

    @Override
    public List<WfSqTfsqmxDO> getWfSqTfsqmxListById(Long id) {
        return wfSqTfsqmxMapper.selectListById(id);
    }

    private void createWfSqTfsqmxList(Long id, List<WfSqTfsqmxDO> list) {
        list.forEach(o -> o.setId(id).clean());
        wfSqTfsqmxMapper.insertBatch(list);
    }

    private void updateWfSqTfsqmxList(Long id, List<WfSqTfsqmxDO> list) {
        list.forEach(o -> o.setId(id).clean());
        List<WfSqTfsqmxDO> oldList = wfSqTfsqmxMapper.selectListById(id);
        List<List<WfSqTfsqmxDO>> diffList = diffList(oldList, list, (oldVal, newVal) -> {
            boolean same = ObjectUtil.equal(oldVal.getId(), newVal.getId());
            if (same) {
                newVal.setId(oldVal.getId()).clean(); // 解决更新情况下：updateTime 不更新
            }
            return same;
        });

        // 第二步，批量添加、修改、删除
        if (CollUtil.isNotEmpty(diffList.get(0))) {
            wfSqTfsqmxMapper.insertBatch(diffList.get(0));
        }
        if (CollUtil.isNotEmpty(diffList.get(1))) {
            wfSqTfsqmxMapper.updateBatch(diffList.get(1));
        }
        if (CollUtil.isNotEmpty(diffList.get(2))) {
            wfSqTfsqmxMapper.deleteByIds(convertList(diffList.get(2), WfSqTfsqmxDO::getId));
        }
    }

    private void deleteWfSqTfsqmxById(Long id) {
        wfSqTfsqmxMapper.deleteById(id);
    }

    private void deleteWfSqTfsqmxByIds(List<Long> ids) {
        wfSqTfsqmxMapper.deleteByIds(ids);
    }

}