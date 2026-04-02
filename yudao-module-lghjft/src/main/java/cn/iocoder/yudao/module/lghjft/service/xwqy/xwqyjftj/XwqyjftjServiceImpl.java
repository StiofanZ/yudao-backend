package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjftj;

import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjfhAggVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjftj.XwqyjftjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class XwqyjftjServiceImpl implements XwqyjftjService {

    @Resource
    private XwqyjftjMapper xwqyjftjMapper;

    @Override
    public List<XwqyjftjAggVO> getXwqyjftjList(XwqyjftjPageReqVO req) {
        return xwqyjftjMapper.selectXwqyjftjList(req);
    }

    @Override
    public List<XwqyjftjfhAggVO> getXwqyjftjfhList(XwqyjftjPageReqVO req) {
        return xwqyjftjMapper.selectXwqyjftjfhList(req);
    }
}
