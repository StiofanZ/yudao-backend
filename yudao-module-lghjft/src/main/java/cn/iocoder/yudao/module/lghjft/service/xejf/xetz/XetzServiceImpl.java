package cn.iocoder.yudao.module.lghjft.service.xejf.xetz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz.vo.XetzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xetz.XetzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xetz.XetzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class XetzServiceImpl implements XetzService {

    @Resource
    private XetzMapper xetzMapper;

    @Override
    public XetzDO getXetz(String id) {
        return xetzMapper.selectById(id);
    }

    @Override
    public PageResult<XetzDO> getXetzPage(XetzPageReqVO pageReqVO) {
        return xetzMapper.selectPage(pageReqVO);
    }
}
