package cn.iocoder.yudao.module.lghjft.service.sjwh.szqzjzdc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.szqzjzdc.SzqzjzdcDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.szqzjzdc.SzqzjzdcMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class SzqzjzdcServiceImpl implements SzqzjzdcService {

    @Resource
    private SzqzjzdcMapper szqzjzdcMapper;

    @Override
    public SzqzjzdcDO getSzqzjzdc(String id) {
        return szqzjzdcMapper.selectById(id);
    }

    @Override
    public PageResult<SzqzjzdcDO> getSzqzjzdcPage(SzqzjzdcPageReqVO pageReqVO) {
        return szqzjzdcMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SzqzjzdcDO> getLegacySdjzList(SzqzjzdcPageReqVO pageReqVO) {
        return szqzjzdcMapper.selectLegacySdjzList(pageReqVO);
    }

    @Override
    public List<SzqzjzdcDO> getLegacyHyjzList(SzqzjzdcPageReqVO pageReqVO) {
        return szqzjzdcMapper.selectLegacyHyjzList(pageReqVO);
    }
}
