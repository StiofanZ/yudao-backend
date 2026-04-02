package cn.iocoder.yudao.module.lghjft.service.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ndrwwc.NdrwwcMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class NdrwwcServiceImpl implements NdrwwcService {

    @Resource
    private NdrwwcMapper ndrwwcMapper;

    @Override
    public NdrwwcDO getNdrwwc(String id) {
        return ndrwwcMapper.selectById(id);
    }

    @Override
    public PageResult<NdrwwcDO> getNdrwwcPage(NdrwwcPageReqVO pageReqVO) {
        return ndrwwcMapper.selectPage(pageReqVO);
    }
}
