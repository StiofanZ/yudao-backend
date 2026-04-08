package cn.iocoder.yudao.module.lghjft.service.jfcl.jfjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs.JfclJfjsDO;

public interface JfjsService {

    /**
     * v1: selecList — 分页查询 gh_qsjshkrj
     */
    PageResult<JfclJfjsDO> getJfjsPage(JfjsPageReqVO pageReqVO);

    /**
     * v1: updateJfclJfjs — 工会经费结算
     * 查询 gh_jf 中 rkrq 范围内 jsbj='N' 的数据,
     * jmse!=0 → jsbj='W', else → jsbj='Y',
     * 批量更新 gh_jf
     */
    void settleJfjs(JfjsPageReqVO reqVO);
}
