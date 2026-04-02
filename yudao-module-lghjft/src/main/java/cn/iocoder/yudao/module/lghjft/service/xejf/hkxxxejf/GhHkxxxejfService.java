package cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejf.GhHkxxxejfDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhHkxxxejfService {
    Long createGhHkxxxejf(@Valid GhHkxxxejfSaveReqVO createReqVO);

    void updateGhHkxxxejf(@Valid GhHkxxxejfSaveReqVO updateReqVO);

    void deleteGhHkxxxejf(Long id);

    void deleteGhHkxxxejfListByIds(List<Long> ids);

    GhHkxxxejfDO getGhHkxxxejf(Long id);

    PageResult<GhHkxxxejfDO> getGhHkxxxejfPage(GhHkxxxejfPageReqVO pageReqVO);
}
