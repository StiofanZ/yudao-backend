package cn.iocoder.yudao.module.lghjft.service.cxtj.fyfcqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk.FyfcqkDO;

import java.util.List;

public interface FyfcqkService {

    PageResult<FyfcqkDO> getFyfcqkPage(FyfcqkPageReqVO pageReqVO);

    /**
     * 分月分成情况聚合查询（GROUP BY WITH ROLLUP），返回全部行含合计行
     */
    List<FyfcqkDO> getFyfcqkAggregateList(FyfcqkPageReqVO reqVO);
}
