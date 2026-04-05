package cn.iocoder.yudao.module.lghjft.service.hbzz.jfbfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfbfmx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jfbfmx.JfbfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jfbfmx.JfbfmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JF_NOT_EXISTS;

@Service
@Validated
public class JfbfmxServiceImpl implements JfbfmxService {

    @Resource
    private JfbfmxMapper jfbfmxMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createJfbfmx(JfbfmxSaveReqVO createReqVO) {
        JfbfmxDO jfbfmx = BeanUtils.toBean(createReqVO, JfbfmxDO.class);
        jfbfmxMapper.insert(jfbfmx);
        return jfbfmx.getGhjfId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJfbfmx(JfbfmxSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        JfbfmxDO updateObj = BeanUtils.toBean(updateReqVO, JfbfmxDO.class);
        jfbfmxMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJfbfmx(Long id) {
        validateExists(id);
        jfbfmxMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJfbfmxListByIds(List<Long> ids) {
        jfbfmxMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (jfbfmxMapper.selectById(id) == null) {
            throw exception(JF_NOT_EXISTS);
        }
    }

    @Override
    public JfbfmxDO getJfbfmx(Long id) {
        return jfbfmxMapper.selectById(id);
    }

    @Override
    public PageResult<JfbfmxDO> getJfbfmxPage(JfbfmxPageReqVO pageReqVO) {
        return jfbfmxMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<JfbfmxSummaryResVO> getSzmxPage(JfbfmxPageReqVO pageReqVO) {
        return buildFullPage(jfbfmxMapper.selectSzmxList(pageReqVO));
    }

    @Override
    public PageResult<JfbfmxDetailResVO> getHymxPage(JfbfmxPageReqVO pageReqVO) {
        return buildFullPage(jfbfmxMapper.selectHymxList(pageReqVO));
    }

    @Override
    public PageResult<JfbfmxDetailResVO> getXjmxPage(JfbfmxPageReqVO pageReqVO) {
        return buildFullPage(jfbfmxMapper.selectXjmxList(pageReqVO));
    }

    @Override
    public PageResult<JfbfmxDetailResVO> getSjmxPage(JfbfmxPageReqVO pageReqVO) {
        return buildFullPage(jfbfmxMapper.selectSjmxList(pageReqVO));
    }

    @Override
    public PageResult<JfbfmxDetailResVO> getSdmxPage(JfbfmxPageReqVO pageReqVO) {
        return buildFullPage(jfbfmxMapper.selectSdmxList(pageReqVO));
    }

    @Override
    public List<JfbfmxtjResVO> getTjByDept(JfbfmxPageReqVO pageReqVO) {
        return jfbfmxMapper.selectTjByDept(pageReqVO);
    }

    @Override
    public List<JfbfmxjsbjResVO> getTjByJsbj(JfbfmxPageReqVO pageReqVO) {
        return jfbfmxMapper.selectTjByJsbj(pageReqVO);
    }

    @Override
    public List<JfbfmxcbjResVO> getTjByCbj(JfbfmxPageReqVO pageReqVO) {
        return jfbfmxMapper.selectTjByCbj(pageReqVO);
    }

    @Override
    public List<JfbfmxzspmResVO> getTjByZspm(JfbfmxPageReqVO pageReqVO) {
        return jfbfmxMapper.selectTjByZspm(pageReqVO);
    }

    @Override
    public List<JfbfmxhkResVO> getTjByHkpch(JfbfmxPageReqVO pageReqVO) {
        return jfbfmxMapper.selectTjByHkpch(pageReqVO);
    }

    private <T> PageResult<T> buildFullPage(List<T> records) {
        List<T> safeRecords = records == null ? new ArrayList<>() : records;
        return new PageResult<>(safeRecords, (long) safeRecords.size());
    }
}
