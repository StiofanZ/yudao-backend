package cn.iocoder.yudao.module.lghjft.service.hbzz.znjzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.znjzz.ZnjzzDO;
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
