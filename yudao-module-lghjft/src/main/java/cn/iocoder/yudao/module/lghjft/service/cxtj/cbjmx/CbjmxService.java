package cn.iocoder.yudao.module.lghjft.service.cxtj.cbjmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxhzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxtjDO;

import java.util.List;

/**
 * 筹备金统计 Service 接口
 */
public interface CbjmxService {

    /**
     * 获得筹备金明细分页 (v1 list)
     */
    PageResult<CbjmxDO> getCbjmxPage(CbjmxPageReqVO pageReqVO);

    /**
     * 获得筹备金统计列表 (v1 listtj) - GROUP BY 聚合
     */
    PageResult<CbjmxtjDO> getCbjmxtjPage(CbjmxPageReqVO reqVO);

    /**
     * 获得筹备金统计列表 (v1 listtj) - GROUP BY 聚合，导出用
     */
    List<CbjmxtjDO> getCbjmxtjList(CbjmxPageReqVO reqVO);

    /**
     * 获得筹备金汇总列表 (v1 listhz) - GROUP BY 聚合，不分页
     */
    List<CbjmxhzDO> getCbjmxhzList(CbjmxPageReqVO reqVO);
}
