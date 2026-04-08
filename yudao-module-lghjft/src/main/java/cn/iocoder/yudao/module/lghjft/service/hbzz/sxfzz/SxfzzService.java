package cn.iocoder.yudao.module.lghjft.service.hbzz.sxfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzSaveReqVO;
import jakarta.validation.Valid;

import java.util.List;

public interface SxfzzService {
    Long createSxfzz(@Valid SxfzzSaveReqVO createReqVO);

    void updateSxfzz(@Valid SxfzzSaveReqVO updateReqVO);

    void deleteSxfzz(Long id);

    void deleteSxfzzListByIds(List<Long> ids);

    SxfzzResVO getSxfzz(Long id);

    PageResult<SxfzzResVO> getSxfzzPage(SxfzzPageReqVO pageReqVO);
}
