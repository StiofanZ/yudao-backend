package cn.iocoder.yudao.module.lghjft.service.cxtj.syhzxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo.SyhzxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxx.SyhzxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.syhzxx.SyhzxxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SyhzxxServiceImpl implements SyhzxxService {

    @Resource
    private SyhzxxMapper syhzxxMapper;

    @Override
    public SyhzxxDO getSyhzxx(String id) {
        return syhzxxMapper.selectById(id);
    }

    @Override
    public PageResult<SyhzxxDO> getSyhzxxPage(SyhzxxPageReqVO pageReqVO) {
        return syhzxxMapper.selectPage(pageReqVO);
    }
}
