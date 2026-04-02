package cn.iocoder.yudao.module.lghjft.service.cxtj.syhzxxfy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo.SyhzxxfyPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxxfy.SyhzxxfyDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.syhzxxfy.SyhzxxfyMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SyhzxxfyServiceImpl implements SyhzxxfyService {

    @Resource
    private SyhzxxfyMapper syhzxxfyMapper;

    @Override
    public SyhzxxfyDO getSyhzxxfy(String id) {
        return syhzxxfyMapper.selectById(id);
    }

    @Override
    public PageResult<SyhzxxfyDO> getSyhzxxfyPage(SyhzxxfyPageReqVO pageReqVO) {
        return syhzxxfyMapper.selectPage(pageReqVO);
    }
}
