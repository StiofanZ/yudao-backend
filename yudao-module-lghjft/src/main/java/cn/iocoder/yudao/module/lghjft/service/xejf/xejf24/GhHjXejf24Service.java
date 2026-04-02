package cn.iocoder.yudao.module.lghjft.service.xejf.xejf24;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo.GhHjXejf24PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo.GhHjXejf24SaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf24.GhHjXejf24DO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhHjXejf24Service {
    String createGhHjXejf24(@Valid GhHjXejf24SaveReqVO createReqVO);

    void updateGhHjXejf24(@Valid GhHjXejf24SaveReqVO updateReqVO);

    void deleteGhHjXejf24(String id);

    void deleteGhHjXejf24ListByIds(List<String> ids);

    GhHjXejf24DO getGhHjXejf24(String id);

    PageResult<GhHjXejf24DO> getGhHjXejf24Page(GhHjXejf24PageReqVO pageReqVO);
}
