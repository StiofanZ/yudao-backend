package cn.iocoder.yudao.module.lghjft.service.cxtj.cbjmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxhzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxtjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.cbjmx.CbjmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CbjmxServiceImpl implements CbjmxService {

    @Resource
    private CbjmxMapper cbjmxMapper;

    @Override
    public CbjmxDO getCbjmx(Long id) {
        return cbjmxMapper.selectById(id);
    }

    @Override
    public PageResult<CbjmxDO> getCbjmxPage(CbjmxPageReqVO pageReqVO) {
        return cbjmxMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CbjmxtjResVO> getCbjmxTjList(CbjmxPageReqVO pageReqVO) {
        return cbjmxMapper.selectTjList(pageReqVO);
    }

    @Override
    public List<CbjmxhzResVO> getCbjmxHzList(CbjmxPageReqVO pageReqVO) {
        return cbjmxMapper.selectHzList(pageReqVO);
    }
}
