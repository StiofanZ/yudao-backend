package cn.iocoder.yudao.module.lghjft.service.jfcl.jfbjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfbjs.JfclJfbjsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfbjs.JfbjsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class JfbjsServiceImpl implements JfbjsService {

    @Resource
    private JfbjsMapper jfbjsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createJfbjs(JfbjsSaveReqVO createReqVO) {
        JfclJfbjsDO entity = BeanUtils.toBean(createReqVO, JfclJfbjsDO.class);
        jfbjsMapper.insert(entity);
        return entity.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJfbjs(JfbjsSaveReqVO updateReqVO) {
        validateJfbjsExists(updateReqVO.getGhjfId());
        JfclJfbjsDO updateObj = BeanUtils.toBean(updateReqVO, JfclJfbjsDO.class);
        jfbjsMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJfbjs(Long id) {
        validateJfbjsExists(id);
        jfbjsMapper.deleteById(id);
    }

    @Override
    public JfclJfbjsDO getJfbjs(Long id) {
        return jfbjsMapper.selectById(id);
    }

    @Override
    public PageResult<JfclJfbjsDO> getJfbjsPage(JfbjsPageReqVO pageReqVO) {
        return jfbjsMapper.selectPage(pageReqVO);
    }

    private void validateJfbjsExists(Long id) {
        if (jfbjsMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}
