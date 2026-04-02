package cn.iocoder.yudao.module.lghjft.service.xejf.xebfzb;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebfzb.XebfzbDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xebfzb.XebfzbMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.XEBFZB_NOT_EXISTS;

@Service
@Validated
public class XebfzbServiceImpl implements XebfzbService {

    @Resource
    private XebfzbMapper xebfzbMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createXebfzb(XebfzbSaveReqVO createReqVO) {
        XebfzbDO entity = BeanUtils.toBean(createReqVO, XebfzbDO.class);
        xebfzbMapper.insert(entity);
        return entity.getXend();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateXebfzb(XebfzbSaveReqVO updateReqVO) {
        XebfzbDO updateObj = BeanUtils.toBean(updateReqVO, XebfzbDO.class);
        xebfzbMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXebfzb(String id) {
        validateExists(id);
        xebfzbMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXebfzbListByIds(List<String> ids) {
        xebfzbMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (xebfzbMapper.selectById(id) == null) {
            throw exception(XEBFZB_NOT_EXISTS);
        }
    }

    @Override
    public XebfzbDO getXebfzb(String id) {
        return xebfzbMapper.selectById(id);
    }

    @Override
    public PageResult<XebfzbDO> getXebfzbPage(XebfzbPageReqVO pageReqVO) {
        return xebfzbMapper.selectPage(pageReqVO);
    }
}
