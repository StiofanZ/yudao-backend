package cn.iocoder.yudao.module.lghjft.service.qx.sfxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;

import java.util.List;

public interface GhQxSfxxService {

    Long createSfxx(SfxxSaveReqVO createReqVO);

    void updateSfxx(SfxxSaveReqVO updateReqVO);

    void deleteSfxx(Long id);

    void deleteSfxxListByIds(List<Long> ids);

    GhQxSfxxDO getSfxx(Long id);

    GhQxSfxxDO getSfxx(Long dlzhId, String djxh);

    PageResult<GhQxSfxxDO> getSfxxPage(SfxxPageReqVO pageReqVO);

    List<GhQxSfxxDO> getSfxxList(SfxxReqVO sfxxReqVO);

    void auditSfxx(Long id, Integer status, String jjyy);

    /**
     * App 端身份授权，带 IDOR 校验（只有身份信息所有者才能操作）
     */
    void auditSfxxWithOwnerCheck(Long id, Integer status, String jjyy);

    void unbindSfxx(Long id, String jbyy);

    /**
     * App 端解绑身份信息，带 IDOR 校验（只有身份信息所有者才能操作）
     */
    void unbindSfxxWithOwnerCheck(Long id, String jbyy);
}
