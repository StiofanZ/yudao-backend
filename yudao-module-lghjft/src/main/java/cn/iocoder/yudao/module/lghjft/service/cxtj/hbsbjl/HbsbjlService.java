package cn.iocoder.yudao.module.lghjft.service.cxtj.hbsbjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjl.HbsbjlDO;
import jakarta.validation.Valid;

import java.util.List;

public interface HbsbjlService {
    Long createHbsbjl(@Valid HbsbjlSaveReqVO createReqVO);

    void updateHbsbjl(@Valid HbsbjlSaveReqVO updateReqVO);

    void deleteHbsbjl(Long id);

    void deleteHbsbjlListByIds(List<Long> ids);

    HbsbjlDO getHbsbjl(Long id);

    PageResult<HbsbjlDO> getHbsbjlPage(HbsbjlPageReqVO pageReqVO);
}
