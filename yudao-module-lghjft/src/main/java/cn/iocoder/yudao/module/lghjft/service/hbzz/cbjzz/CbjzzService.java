package cn.iocoder.yudao.module.lghjft.service.hbzz.cbjzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzSaveReqVO;
import jakarta.validation.Valid;

import java.util.List;

public interface CbjzzService {
    Long createCbjzz(@Valid CbjzzSaveReqVO createReqVO);

    void updateCbjzz(@Valid CbjzzSaveReqVO updateReqVO);

    void deleteCbjzz(Long id);

    void deleteCbjzzListByIds(List<Long> ids);

    CbjzzResVO getCbjzz(Long id);

    PageResult<CbjzzResVO> getCbjzzPage(CbjzzPageReqVO pageReqVO);

    /** v1 export grouped by sjdm */
    List<CbjzzResVO> getCbjzzListCbj(CbjzzPageReqVO pageReqVO);
}
