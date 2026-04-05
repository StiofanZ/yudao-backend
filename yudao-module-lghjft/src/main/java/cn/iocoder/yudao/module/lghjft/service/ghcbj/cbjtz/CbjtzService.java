package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzBatchReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.DgjftzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjtz.CbjtzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface CbjtzService {
    Long createCbjtz(@Valid CbjtzSaveReqVO createReqVO);

    void updateCbjtz(@Valid CbjtzSaveReqVO updateReqVO);

    void deleteCbjtz(Long id);

    void deleteCbjtzListByIds(List<Long> ids);

    CbjtzDO getCbjtz(Long id);

    PageResult<CbjtzDO> getCbjtzPage(CbjtzPageReqVO pageReqVO);

    /**
     * 批量确认返拨
     */
    void batchCbjqrfbpl(List<CbjtzBatchReqVO> records);

    List<DgjftzResVO> getDgjftzList(CbjtzPageReqVO pageReqVO);

    PageResult<CbjtzDO> getDgjftzPage(CbjtzPageReqVO pageReqVO);
}
