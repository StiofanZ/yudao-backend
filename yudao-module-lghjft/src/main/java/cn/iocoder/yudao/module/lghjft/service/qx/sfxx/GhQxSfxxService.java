package cn.iocoder.yudao.module.lghjft.service.qx.sfxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;

import java.util.List;

public interface GhQxSfxxService {

    Long createSfxx(SfxxSaveReqVO createReqVO);

    void updateSfxx(SfxxSaveReqVO updateReqVO);

    void deleteSfxx(Long id);

    void deleteSfxxListByIds(List<Long> ids);

    GhQxSfxxDO getSfxx(Long id);

    PageResult<GhQxSfxxDO> getSfxxPage(SfxxPageReqVO pageReqVO);

    void auditSfxx(Long id, Integer status, String jjyy);

    void unbindSfxx(Long id, String jbyy);

    List<cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxRespVO> getKbdsfxxList(String lxdh);

}
