package cn.iocoder.yudao.module.lghjft.service.hbzz.jcjfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.JcjfzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jcjfzz.JcjfzzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class JcjfzzServiceImpl implements JcjfzzService {

    @Resource
    private JcjfzzMapper jcjfzzMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createJcjfzz(JcjfzzSaveReqVO createReqVO) {
        JcjfzzDO jcjfzz = BeanUtils.toBean(createReqVO, JcjfzzDO.class);
        jcjfzzMapper.insert(jcjfzz);
        return jcjfzz.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJcjfzz(JcjfzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        JcjfzzDO updateObj = BeanUtils.toBean(updateReqVO, JcjfzzDO.class);
        jcjfzzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJcjfzz(Long id) {
        validateExists(id);
        jcjfzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJcjfzzListByIds(List<Long> ids) {
        jcjfzzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (jcjfzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public JcjfzzDO getJcjfzz(Long id) {
        return jcjfzzMapper.selectById(id);
    }

    @Override
    public PageResult<JcjfzzDO> getJcjfzzPage(JcjfzzPageReqVO pageReqVO) {
        return jcjfzzMapper.selectPage(pageReqVO);
    }
}
