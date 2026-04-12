package cn.iocoder.yudao.module.lghjft.service.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;

import java.util.List;

public interface TopService {

    /**
     * 获得缴费排行
     *
     * @param djxh 登记序号
     * @return 缴费排行
     */
    TopDO getTop(String djxh);

    /**
     * 获得缴费排行列表（非分页）— 还原 v1 selectTopList，root = "620000"
     *
     * @param reqVO 查询条件
     * @return 缴费排行列表（LIMIT 100, ORDER BY jfjecy desc）
     */
    List<TopDO> getTopList(TopPageReqVO reqVO);

    /**
     * 获得缴费排行分页 — 还原 v1 selectTopList1，root = "100000"
     *
     * @param pageReqVO 分页查询
     * @return 缴费排行分页
     */
    PageResult<TopDO> getTopPage(TopPageReqVO pageReqVO);
}
