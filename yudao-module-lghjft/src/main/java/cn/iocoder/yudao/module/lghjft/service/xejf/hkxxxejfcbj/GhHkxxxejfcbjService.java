package cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejfcbj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfcbj.GhHkxxxejfcbjDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhHkxxxejfcbjService {
    Long createGhHkxxxejfcbj(@Valid GhHkxxxejfcbjSaveReqVO createReqVO);

    void updateGhHkxxxejfcbj(@Valid GhHkxxxejfcbjSaveReqVO updateReqVO);

    void deleteGhHkxxxejfcbj(Long id);

    void deleteGhHkxxxejfcbjListByIds(List<Long> ids);

    GhHkxxxejfcbjResVO getGhHkxxxejfcbj(Long id);

    PageResult<GhHkxxxejfcbjResVO> getGhHkxxxejfcbjPage(GhHkxxxejfcbjPageReqVO pageReqVO);
}
