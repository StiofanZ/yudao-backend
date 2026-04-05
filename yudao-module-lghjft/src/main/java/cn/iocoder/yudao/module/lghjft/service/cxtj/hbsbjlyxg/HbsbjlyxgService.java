package cn.iocoder.yudao.module.lghjft.service.cxtj.hbsbjlyxg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjlyxg.HbsbjlyxgDO;
import jakarta.validation.Valid;

import java.util.List;

public interface HbsbjlyxgService {
    Long createHbsbjlyxg(@Valid HbsbjlyxgSaveReqVO createReqVO);

    void updateHbsbjlyxg(@Valid HbsbjlyxgSaveReqVO updateReqVO);

    void deleteHbsbjlyxg(Long id);

    void deleteHbsbjlyxgListByIds(List<Long> ids);

    HbsbjlyxgDO getHbsbjlyxg(Long id);

    PageResult<HbsbjlyxgDO> getHbsbjlyxgPage(HbsbjlyxgPageReqVO pageReqVO);
}
