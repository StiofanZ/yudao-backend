package cn.iocoder.yudao.module.lghjft.service.xejf.xejfzzgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfzzgl.XejfghzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejfzzgl.XejfghzzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.XEJFZZGL_NOT_EXISTS;

@Service
@Validated
public class XejfghzzServiceImpl implements XejfghzzService {

    @Resource
    private XejfghzzMapper xejfghzzMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createXejfghzz(XejfghzzSaveReqVO createReqVO) {
        XejfghzzDO entity = BeanUtils.toBean(createReqVO, XejfghzzDO.class);
        xejfghzzMapper.insert(entity);
        return entity.getDjxh();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateXejfghzz(XejfghzzSaveReqVO updateReqVO) {
        XejfghzzDO updateObj = BeanUtils.toBean(updateReqVO, XejfghzzDO.class);
        xejfghzzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXejfghzz(String id) {
        validateExists(id);
        xejfghzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXejfghzzListByIds(List<String> ids) {
        xejfghzzMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (xejfghzzMapper.selectById(id) == null) {
            throw exception(XEJFZZGL_NOT_EXISTS);
        }
    }

    @Override
    public XejfghzzDO getXejfghzz(String id) {
        return xejfghzzMapper.selectById(id);
    }

    @Override
    public PageResult<XejfghzzDO> getXejfghzzPage(XejfghzzPageReqVO pageReqVO) {
        return xejfghzzMapper.selectPage(pageReqVO);
    }
}
