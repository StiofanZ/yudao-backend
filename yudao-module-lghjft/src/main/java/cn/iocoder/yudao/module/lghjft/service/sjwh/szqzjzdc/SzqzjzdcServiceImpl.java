package cn.iocoder.yudao.module.lghjft.service.sjwh.szqzjzdc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcSaveReqVO;
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
    public List<SzqzjzdcDO> getLegacyMainList(SzqzjzdcPageReqVO pageReqVO) {
        return szqzjzdcMapper.selectLegacyMainList(pageReqVO);
    }

    @Override
    public List<SzqzjzdcDO> getLegacySdjzList(SzqzjzdcPageReqVO pageReqVO) {
        return szqzjzdcMapper.selectLegacySdjzList(pageReqVO);
    }

    @Override
    public List<SzqzjzdcDO> getLegacyHyjzList(SzqzjzdcPageReqVO pageReqVO) {
        return szqzjzdcMapper.selectLegacyHyjzList(pageReqVO);
    }

    @Override
    public List<SzqzjzdcDO> getLegacyCbjList(SzqzjzdcPageReqVO pageReqVO) {
        return szqzjzdcMapper.selectLegacyCbjList(pageReqVO);
    }

    @Override
    public String createSzqzjzdc(SzqzjzdcSaveReqVO createReqVO) {
        SzqzjzdcDO szqzjzdc = BeanUtils.toBean(createReqVO, SzqzjzdcDO.class);
        szqzjzdcMapper.insert(szqzjzdc);
        return szqzjzdc.getPzbh();
    }

    @Override
    public void updateSzqzjzdc(SzqzjzdcSaveReqVO updateReqVO) {
        SzqzjzdcDO szqzjzdc = BeanUtils.toBean(updateReqVO, SzqzjzdcDO.class);
        szqzjzdcMapper.updateById(szqzjzdc);
    }

    @Override
    public void deleteSzqzjzdcByPzbhs(List<String> pzbhs) {
        szqzjzdcMapper.deleteByIds(pzbhs);
    }
}
