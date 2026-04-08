package cn.iocoder.yudao.module.lghjft.service.hbzz.fjcjfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo.FjcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo.FjcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo.FjcjfzzSaveReqVO;
import jakarta.validation.Valid;

import java.util.List;

public interface FjcjfzzService {
    Long createFjcjfzz(@Valid FjcjfzzSaveReqVO createReqVO);

    void updateFjcjfzz(@Valid FjcjfzzSaveReqVO updateReqVO);

    void deleteFjcjfzz(Long id);

    void deleteFjcjfzzListByIds(List<Long> ids);

    FjcjfzzResVO getFjcjfzz(Long id);

    PageResult<FjcjfzzResVO> getFjcjfzzPage(FjcjfzzPageReqVO pageReqVO);
}
