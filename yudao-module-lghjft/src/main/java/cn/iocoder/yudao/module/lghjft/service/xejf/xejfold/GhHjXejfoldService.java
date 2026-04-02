package cn.iocoder.yudao.module.lghjft.service.xejf.xejfold;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold.vo.GhHjXejfoldPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold.vo.GhHjXejfoldSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfold.GhHjXejfoldDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhHjXejfoldService {
    String createGhHjXejfold(@Valid GhHjXejfoldSaveReqVO createReqVO);

    void updateGhHjXejfold(@Valid GhHjXejfoldSaveReqVO updateReqVO);

    void deleteGhHjXejfold(String id);

    void deleteGhHjXejfoldListByIds(List<String> ids);

    GhHjXejfoldDO getGhHjXejfold(String id);

    PageResult<GhHjXejfoldDO> getGhHjXejfoldPage(GhHjXejfoldPageReqVO pageReqVO);
}
