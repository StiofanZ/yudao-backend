package cn.iocoder.yudao.module.lghjft.service.sjwh.fjcjfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fjcjfzz.vo.FjcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fjcjfzz.vo.FjcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fjcjfzz.vo.FjcjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.fjcjfzz.FjcjfzzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface FjcjfzzService {
    Long createFjcjfzz(@Valid FjcjfzzSaveReqVO createReqVO);

    void updateFjcjfzz(@Valid FjcjfzzSaveReqVO updateReqVO);

    void deleteFjcjfzz(Long id);

    void deleteFjcjfzzListByIds(List<Long> ids);

    FjcjfzzDO getFjcjfzz(Long id);

    PageResult<FjcjfzzResVO> getFjcjfzzPage(FjcjfzzPageReqVO pageReqVO);
}
