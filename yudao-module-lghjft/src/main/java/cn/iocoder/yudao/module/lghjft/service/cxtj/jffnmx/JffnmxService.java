package cn.iocoder.yudao.module.lghjft.service.cxtj.jffnmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxSummaryResVO;

/**
 * 经费分年明细 Service 接口
 */
public interface JffnmxService {

    /**
     * 获得经费分年明细分页
     *
     * @param pageReqVO 分页查询
     * @return 分页结果
     */
    PageResult<JffnmxSummaryResVO> selectJffnmxList(JffnmxPageReqVO pageReqVO);
}
