package cn.iocoder.yudao.module.lghjft.service.sjwh.znjzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.znjzz.vo.ZnjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.znjzz.vo.ZnjzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.znjzz.vo.ZnjzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.znjzz.ZnjzzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface ZnjzzService {
    Long createZnjzz(@Valid ZnjzzSaveReqVO createReqVO);

    void updateZnjzz(@Valid ZnjzzSaveReqVO updateReqVO);

    void deleteZnjzz(Long id);

    void deleteZnjzzListByIds(List<Long> ids);

    ZnjzzDO getZnjzz(Long id);

    PageResult<ZnjzzResVO> getZnjzzPage(ZnjzzPageReqVO pageReqVO);
}
