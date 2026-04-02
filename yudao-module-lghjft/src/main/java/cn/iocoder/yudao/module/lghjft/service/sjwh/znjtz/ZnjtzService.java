package cn.iocoder.yudao.module.lghjft.service.sjwh.znjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.znjtz.vo.ZnjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.znjtz.vo.ZnjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.znjtz.ZnjtzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface ZnjtzService {
    Long createZnjtz(@Valid ZnjtzSaveReqVO createReqVO);

    void updateZnjtz(@Valid ZnjtzSaveReqVO updateReqVO);

    void deleteZnjtz(Long id);

    void deleteZnjtzListByIds(List<Long> ids);

    ZnjtzDO getZnjtz(Long id);

    PageResult<ZnjtzDO> getZnjtzPage(ZnjtzPageReqVO pageReqVO);
}
