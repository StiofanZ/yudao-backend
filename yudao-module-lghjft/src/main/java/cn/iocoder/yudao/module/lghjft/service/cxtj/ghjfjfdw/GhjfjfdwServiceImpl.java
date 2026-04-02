package cn.iocoder.yudao.module.lghjft.service.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ghjfjfdw.GhjfjfdwMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class GhjfjfdwServiceImpl implements GhjfjfdwService {

    @Resource
    private GhjfjfdwMapper ghjfjfdwMapper;

    @Override
    public GhjfjfdwDO getGhjfjfdw(String id) {
        return ghjfjfdwMapper.selectById(id);
    }

    @Override
    public PageResult<GhjfjfdwDO> getGhjfjfdwPage(GhjfjfdwPageReqVO pageReqVO) {
        return ghjfjfdwMapper.selectPage(pageReqVO);
    }
}
