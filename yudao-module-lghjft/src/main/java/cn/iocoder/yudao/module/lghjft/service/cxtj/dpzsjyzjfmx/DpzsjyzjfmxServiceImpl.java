package cn.iocoder.yudao.module.lghjft.service.cxtj.dpzsjyzjfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo.DpzsjyzjfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dpzsjyzjfmx.DpzsjyzjfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.dpzsjyzjfmx.DpzsjyzjfmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DpzsjyzjfmxServiceImpl implements DpzsjyzjfmxService {

    @Resource
    private DpzsjyzjfmxMapper dpzsjyzjfmxMapper;

    @Override
    public DpzsjyzjfmxDO getDpzsjyzjfmx(String id) {
        return dpzsjyzjfmxMapper.selectById(id);
    }

    @Override
    public PageResult<DpzsjyzjfmxDO> getDpzsjyzjfmxPage(DpzsjyzjfmxPageReqVO pageReqVO) {
        return dpzsjyzjfmxMapper.selectPage(pageReqVO);
    }
}
