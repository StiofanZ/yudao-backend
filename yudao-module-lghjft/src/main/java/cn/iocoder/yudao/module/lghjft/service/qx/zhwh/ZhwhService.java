package cn.iocoder.yudao.module.lghjft.service.qx.zhwh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhAuditReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.zhwh.GhQxZhwhDO;
import jakarta.validation.Valid;

public interface ZhwhService {

    Long createZhwh(@Valid ZhwhCreateReqVO createReqVO);

    void updateZhwh(@Valid ZhwhUpdateReqVO updateReqVO);

    void deleteZhwh(Long id);

    void cancelZhwh(Long id);

    /**
     * App 端撤回账户维护申请，带 IDOR 校验（只有申请人才能撤回）
     */
    void cancelZhwhWithOwnerCheck(Long id);

    GhQxZhwhDO getZhwh(Long id);

    GhQxZhwhDO getZhwhWithOwnerCheck(Long id);

    PageResult<GhQxZhwhDO> getZhwhPage(ZhwhPageReqVO pageReqVO);

    void auditZhwh(@Valid ZhwhAuditReqVO reqVO);
}
