package cn.iocoder.yudao.module.lghjft.service.jfcl.dqdssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfclDqdssjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.dqdssj.DqdssjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class DqdssjServiceImpl implements DqdssjService {

    @Resource
    private DqdssjMapper dqdssjMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDqdssj(DqdssjSaveReqVO createReqVO) {
        JfclDqdssjDO entity = BeanUtils.toBean(createReqVO, JfclDqdssjDO.class);
        dqdssjMapper.insert(entity);
        return 0L;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDqdssj(DqdssjSaveReqVO updateReqVO) {
        validateDqdssjExists(updateReqVO.getGhjfId());
        JfclDqdssjDO updateObj = BeanUtils.toBean(updateReqVO, JfclDqdssjDO.class);
        dqdssjMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDqdssj(Long id) {
        validateDqdssjExists(id);
        dqdssjMapper.deleteById(id);
    }

    @Override
    public JfclDqdssjDO getDqdssj(Long id) {
        return dqdssjMapper.selectById(id);
    }

    @Override
    public PageResult<JfclDqdssjDO> getDqdssjPage(DqdssjPageReqVO pageReqVO) {
        return dqdssjMapper.selectPage(pageReqVO);
    }

    private void validateDqdssjExists(Long id) {
        if (dqdssjMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}
