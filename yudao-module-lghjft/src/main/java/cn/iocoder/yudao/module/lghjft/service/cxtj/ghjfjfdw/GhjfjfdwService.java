package cn.iocoder.yudao.module.lghjft.service.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;

public interface GhjfjfdwService {

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
