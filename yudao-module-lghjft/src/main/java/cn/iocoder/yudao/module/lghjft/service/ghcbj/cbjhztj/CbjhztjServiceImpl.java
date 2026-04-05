package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjhztj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjhzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjtjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjhztj.CbjhztjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjhztj.CbjhztjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CbjhztjServiceImpl implements CbjhztjService {

    @Resource
    private CbjhztjMapper cbjhztjMapper;

    @Override
    public CbjhztjDO getCbjhztj(Long id) {
        return cbjhztjMapper.selectById(id);
    }

    @Override
    public PageResult<CbjhztjDO> getCbjhztjPage(CbjhztjPageReqVO pageReqVO) {
        return cbjhztjMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CbjhztjtjResVO> getCbjhztjTjList(CbjhztjPageReqVO pageReqVO) {
        return cbjhztjMapper.selectTjList(pageReqVO);
    }

    @Override
    public List<CbjhztjhzResVO> getCbjhztjHzList(CbjhztjPageReqVO pageReqVO) {
        return cbjhztjMapper.selectHzList(pageReqVO);
    }
}
