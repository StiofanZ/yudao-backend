package cn.iocoder.yudao.module.lghjft.service.cxtj.jftzfn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo.JftzfnPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo.JftzfnSummaryResVO;

/**
 * 经费台账分年 Service 接口
 */
public interface JftzfnService {

    /**
     * 获得经费台账分年分页
     *
     * @param pageReqVO 分页查询
     * @return 分页结果
     */
    PageResult<JftzfnSummaryResVO> selectJftzfnList(JftzfnPageReqVO pageReqVO);
}
