package cn.iocoder.yudao.module.lghjft.service.cxtj.czrj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo.QxCzrjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo.QxCzrjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.czrj.QxCzrjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.czrj.QxCzrjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class QxCzrjServiceImpl implements QxCzrjService {

    @Resource
    private QxCzrjMapper qxCzrjMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createQxCzrj(QxCzrjSaveReqVO createReqVO) {
        QxCzrjDO obj = BeanUtils.toBean(createReqVO, QxCzrjDO.class);
        qxCzrjMapper.insert(obj);
        return obj.getCzrjid();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQxCzrj(QxCzrjSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getCzrjid());
        QxCzrjDO updateObj = BeanUtils.toBean(updateReqVO, QxCzrjDO.class);
        qxCzrjMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQxCzrj(Long id) {
        validateExists(id);
        qxCzrjMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQxCzrjListByIds(List<Long> ids) {
        qxCzrjMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (qxCzrjMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public QxCzrjDO getQxCzrj(Long id) {
        return qxCzrjMapper.selectById(id);
    }

    @Override
    public PageResult<QxCzrjDO> getQxCzrjPage(QxCzrjPageReqVO pageReqVO) {
        return qxCzrjMapper.selectPage(pageReqVO);
    }
}
