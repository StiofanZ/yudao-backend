package cn.iocoder.yudao.module.lghjft.service.cxtj.szjffydsqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.szjffydsqk.SzjffydsqkDO;
import jakarta.validation.Valid;

import java.util.List;

public interface SzjffydsqkService {
    String createSzjffydsqk(@Valid SzjffydsqkSaveReqVO createReqVO);

    void updateSzjffydsqk(@Valid SzjffydsqkSaveReqVO updateReqVO);

    void deleteSzjffydsqk(String id);

    void deleteSzjffydsqkListByIds(List<String> ids);

    SzjffydsqkDO getSzjffydsqk(String id);

    PageResult<SzjffydsqkDO> getSzjffydsqkPage(SzjffydsqkPageReqVO pageReqVO);
}
