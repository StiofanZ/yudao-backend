package cn.iocoder.yudao.module.lghjft.service.hbzz.znjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.znjtz.ZnjtzDO;
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
