package cn.iocoder.yudao.module.lghjft.service.hbzz.ghjfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo.GhjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo.GhjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo.GhjfzzSaveReqVO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhjfzzService {
    Long createGhjfzz(@Valid GhjfzzSaveReqVO createReqVO);

    void updateGhjfzz(@Valid GhjfzzSaveReqVO updateReqVO);

    void deleteGhjfzz(Long id);

    void deleteGhjfzzListByIds(List<Long> ids);

    GhjfzzResVO getGhjfzz(Long id);

    PageResult<GhjfzzResVO> getGhjfzzPage(GhjfzzPageReqVO pageReqVO);
}
