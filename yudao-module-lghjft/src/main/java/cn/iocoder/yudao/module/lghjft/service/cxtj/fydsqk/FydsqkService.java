package cn.iocoder.yudao.module.lghjft.service.cxtj.fydsqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk.FydsqkDO;
import jakarta.validation.Valid;

import java.util.List;

public interface FydsqkService {
    String createFydsqk(@Valid FydsqkSaveReqVO createReqVO);

    void updateFydsqk(@Valid FydsqkSaveReqVO updateReqVO);

    void deleteFydsqk(String id);

    void deleteFydsqkListByIds(List<String> ids);

    FydsqkDO getFydsqk(String id);

    PageResult<FydsqkDO> getFydsqkPage(FydsqkPageReqVO pageReqVO);
}
