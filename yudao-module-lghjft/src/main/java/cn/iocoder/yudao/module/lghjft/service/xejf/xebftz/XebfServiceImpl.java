package cn.iocoder.yudao.module.lghjft.service.xejf.xebftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebftz.XebfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xebftz.XebfMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class XebfServiceImpl implements XebfService {

    @Resource
    private XebfMapper xebfMapper;

    @Override
    public XebfDO getXebf(Long id) {
        return xebfMapper.selectById(id);
    }

    @Override
    public PageResult<XebfDO> getXebfPage(XebfPageReqVO pageReqVO) {
        return xebfMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GhhkxxxejfszResVO> getGhHkxxxejfszList(String jfqj) {
        return xebfMapper.selectGhHkxxxejfszList(jfqj);
    }
}
