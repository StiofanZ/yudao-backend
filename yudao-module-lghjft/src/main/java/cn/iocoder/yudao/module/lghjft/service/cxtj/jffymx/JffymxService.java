package cn.iocoder.yudao.module.lghjft.service.cxtj.jffymx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxSummaryResVO;

/**
 * 经费分月明细 Service 接口
 */
public interface JffymxService {

    /**
     * 获得经费分月明细分页
     *
     * @param pageReqVO 分页查询
     * @return 分页结果
     */
    PageResult<JffymxSummaryResVO> selectJffymxList(JffymxPageReqVO pageReqVO);
}
