package cn.iocoder.yudao.module.lghjft.service.jfcl.jfbjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfbjs.JfclJfbjsDO;

public interface JfbjsService {

    /**
     * v1: selectJfclJfbjs — 查询 gh_jf 中待补结算数据（分页）
     */
    PageResult<JfclJfbjsDO> getJfbjsPage(JfbjsPageReqVO pageReqVO);

    /**
     * v1: updateJfclJfbjs — 工会经费补结算
     * 1. 查询 gh_jf WHERE 条件 AND jsbj='N'
     * 2. jmse != 0 → jsbj='W', else → jsbj='Y'
     * 3. 批量更新 gh_jf
     * 4. 查询并更新 gh_qsjshkrj 结算日志
     */
    void settleJfbjs(JfbjsPageReqVO reqVO);
}
