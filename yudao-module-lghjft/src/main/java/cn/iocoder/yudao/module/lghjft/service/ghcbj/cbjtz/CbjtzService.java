package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzBatchReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjtz.CbjtzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface CbjtzService {
    Long createCbjtz(@Valid CbjtzSaveReqVO createReqVO);

    void updateCbjtz(@Valid CbjtzSaveReqVO updateReqVO);

    void deleteCbjtz(Long id);

    void deleteCbjtzListByIds(List<Long> ids);

    CbjtzDO getCbjtz(Long id);

    /**
     * v1 selectCbjtzList: from cbjtz view + join cxtj_cbjqrfb
     */
    PageResult<CbjtzResVO> getCbjtzPage(CbjtzPageReqVO pageReqVO);

    /**
     * v1 selectDgjftzList: from gh_jf + join cxtj_cbjqrfb
     */
    PageResult<CbjtzResVO> getDgjftzPage(CbjtzPageReqVO pageReqVO);

    /**
     * v1 batchCbjqrfbpl: insert/update cxtj_cbjqrfb + update gh_jf
     */
    void batchCbjqrfbpl(List<CbjtzBatchReqVO> records);
}
