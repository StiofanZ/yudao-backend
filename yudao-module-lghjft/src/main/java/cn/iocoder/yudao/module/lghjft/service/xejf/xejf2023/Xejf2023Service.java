package cn.iocoder.yudao.module.lghjft.service.xejf.xejf2023;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023SaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.XejftjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf2023.Xejf2023DO;
import jakarta.validation.Valid;

import java.util.List;

public interface Xejf2023Service {
    Long createXejf2023(@Valid Xejf2023SaveReqVO createReqVO);

    void updateXejf2023(@Valid Xejf2023SaveReqVO updateReqVO);

    void deleteXejf2023(Long id);

    void deleteXejf2023ListByIds(List<Long> ids);

    Xejf2023DO getXejf2023(Long id);

    PageResult<Xejf2023DO> getXejf2023Page(Xejf2023PageReqVO pageReqVO);

    List<Xejf2023DO> getXejf2023PageTz(Xejf2023PageReqVO pageReqVO);

    List<XejftjResVO> getXejf2023Xetj(Xejf2023PageReqVO pageReqVO);
}
