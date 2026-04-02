package cn.iocoder.yudao.module.lghjft.service.sjwh.sxfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.sxfzz.vo.SxfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.sxfzz.vo.SxfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.sxfzz.SxfzzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface SxfzzService {
    Long createSxfzz(@Valid SxfzzSaveReqVO createReqVO);

    void updateSxfzz(@Valid SxfzzSaveReqVO updateReqVO);

    void deleteSxfzz(Long id);

    void deleteSxfzzListByIds(List<Long> ids);

    SxfzzDO getSxfzz(Long id);

    PageResult<SxfzzDO> getSxfzzPage(SxfzzPageReqVO pageReqVO);
}
