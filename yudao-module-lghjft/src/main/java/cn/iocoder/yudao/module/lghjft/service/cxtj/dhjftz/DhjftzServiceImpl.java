package cn.iocoder.yudao.module.lghjft.service.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.dhjftz.DhjftzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DhjftzServiceImpl implements DhjftzService {

    @Resource
    private DhjftzMapper dhjftzMapper;

    @Override
    public DhjftzDO getDhjftz(String djxh) {
        return dhjftzMapper.selectById(djxh);
    }

    @Override
    public PageResult<DhjftzDO> getDhjftzPage(DhjftzPageReqVO pageReqVO) {
        return dhjftzMapper.selectPage(pageReqVO);
    }
}
