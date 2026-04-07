package cn.iocoder.yudao.module.lghjft.service.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;
import jakarta.validation.Valid;

public interface TopService {

    /**
     * 创建缴费排行
     *
     * @param createReqVO 创建信息
     * @return 登记序号
     */
    String createTop(@Valid TopSaveReqVO createReqVO);

    /**
     * 更新缴费排行
     *
     * @param updateReqVO 更新信息
     */
    void updateTop(@Valid TopSaveReqVO updateReqVO);

    /**
     * 删除缴费排行
     *
     * @param djxh 登记序号
     */
    void deleteTop(String djxh);

    /**
     * 获得缴费排行
     *
     * @param djxh 登记序号
     * @return 缴费排行
     */
    TopDO getTop(String djxh);

    /**
     * 获得缴费排行分页
     *
     * @param pageReqVO 分页查询
     * @return 缴费排行分页
     */
    PageResult<TopDO> getTopPage(TopPageReqVO pageReqVO);
}
