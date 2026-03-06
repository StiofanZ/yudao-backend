package cn.iocoder.yudao.module.lghjft.service.nrgl.wtfk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk.GhNrglWtfkDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhNrglWtfkService {

    Long createGhNrglWtfk(@Valid GhNrglWtfkSaveReqVO createReqVO);

    void updateGhNrglWtfk(@Valid GhNrglWtfkSaveReqVO updateReqVO);

    void deleteGhNrglWtfk(Long id, Boolean isAdminView);

    void deleteGhNrglWtfkListByIds(List<Long> ids, Boolean isAdminView);

    GhNrglWtfkDO getGhNrglWtfk(Long id);

    PageResult<GhNrglWtfkDO> getGhNrglWtfkPage(GhNrglWtfkPageReqVO pageReqVO);

    void handleProcess(@Valid GhNrglWtfkClReqVO reqVO);

    List<GhNrglWtfkClmxRespVO> getGhNrglWtfkClmxList(Long wtfkId);

    GhNrglWtfkRespVO getGhNrglWtfkDetail(Long id);
}
