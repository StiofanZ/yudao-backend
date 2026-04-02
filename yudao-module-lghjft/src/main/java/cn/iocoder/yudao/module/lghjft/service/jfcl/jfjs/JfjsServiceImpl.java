package cn.iocoder.yudao.module.lghjft.service.jfcl.jfjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs.JfclJfjsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfjs.JfjsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class JfjsServiceImpl implements JfjsService {

    @Resource
    private JfjsMapper jfjsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createJfjs(JfjsSaveReqVO createReqVO) {
        JfclJfjsDO entity = BeanUtils.toBean(createReqVO, JfclJfjsDO.class);
        jfjsMapper.insert(entity);
        return 0L;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJfjs(JfjsSaveReqVO updateReqVO) {
        validateJfjsExists(updateReqVO.getGhjfId());
        JfclJfjsDO updateObj = BeanUtils.toBean(updateReqVO, JfclJfjsDO.class);
        jfjsMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJfjs(Long id) {
        validateJfjsExists(id);
        jfjsMapper.deleteById(id);
    }

    @Override
    public JfclJfjsDO getJfjs(Long id) {
        return jfjsMapper.selectById(id);
    }

    @Override
    public PageResult<JfclJfjsDO> getJfjsPage(JfjsPageReqVO pageReqVO) {
        return jfjsMapper.selectPage(pageReqVO);
    }

    private void validateJfjsExists(Long id) {
        if (jfjsMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}
