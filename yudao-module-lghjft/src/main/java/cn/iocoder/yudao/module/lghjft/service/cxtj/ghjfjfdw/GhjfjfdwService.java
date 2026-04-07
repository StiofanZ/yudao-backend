package cn.iocoder.yudao.module.lghjft.service.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;
import jakarta.validation.Valid;

public interface GhjfjfdwService {

    /**
     * 创建近三年缴费情况
     *
     * @param createReqVO 创建信息
     * @return 登记序号
     */
    String createGhjfjfdw(@Valid GhjfjfdwSaveReqVO createReqVO);

    /**
     * 更新近三年缴费情况
     *
     * @param updateReqVO 更新信息
     */
    void updateGhjfjfdw(@Valid GhjfjfdwSaveReqVO updateReqVO);

    /**
     * 批量删除近三年缴费情况
     *
     * @param djxhs 登记序号数组
     */
    void deleteGhjfjfdwBatch(String[] djxhs);

    /**
     * 获得近三年缴费情况
     *
     * @param djxh 登记序号
     * @return 近三年缴费情况
     */
    GhjfjfdwDO getGhjfjfdw(String djxh);

    /**
     * 获得近三年缴费情况分页
     *
     * @param pageReqVO 分页查询
     * @return 近三年缴费情况分页
     */
    PageResult<GhjfjfdwDO> getGhjfjfdwPage(GhjfjfdwPageReqVO pageReqVO);
}
