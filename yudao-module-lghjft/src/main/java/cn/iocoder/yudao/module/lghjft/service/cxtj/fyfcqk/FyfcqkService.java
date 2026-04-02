package cn.iocoder.yudao.module.lghjft.service.cxtj.fyfcqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk.FyfcqkDO;
import jakarta.validation.Valid;

import java.util.List;

public interface FyfcqkService {
    String createFyfcqk(@Valid FyfcqkSaveReqVO createReqVO);

    void updateFyfcqk(@Valid FyfcqkSaveReqVO updateReqVO);

    void deleteFyfcqk(String id);

    void deleteFyfcqkListByIds(List<String> ids);

    FyfcqkDO getFyfcqk(String id);

    PageResult<FyfcqkDO> getFyfcqkPage(FyfcqkPageReqVO pageReqVO);
}
