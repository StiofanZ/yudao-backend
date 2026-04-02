package cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejfjcdz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfjcdz.GhHkxxxejfjcdzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhHkxxxejfjcdzService {
    Long createGhHkxxxejfjcdz(@Valid GhHkxxxejfjcdzSaveReqVO createReqVO);

    void updateGhHkxxxejfjcdz(@Valid GhHkxxxejfjcdzSaveReqVO updateReqVO);

    void deleteGhHkxxxejfjcdz(Long id);

    void deleteGhHkxxxejfjcdzListByIds(List<Long> ids);

    GhHkxxxejfjcdzDO getGhHkxxxejfjcdz(Long id);

    PageResult<GhHkxxxejfjcdzDO> getGhHkxxxejfjcdzPage(GhHkxxxejfjcdzPageReqVO pageReqVO);
}
