package cn.iocoder.yudao.module.lghjft.service.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.top.TopMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class TopServiceImpl implements TopService {

    @Resource
    private TopMapper topMapper;

    @Override
    public TopDO getTop(String id) {
        return topMapper.selectById(id);
    }

    @Override
    public PageResult<TopDO> getTopPage(TopPageReqVO pageReqVO) {
        return topMapper.selectPage(pageReqVO);
    }
}
