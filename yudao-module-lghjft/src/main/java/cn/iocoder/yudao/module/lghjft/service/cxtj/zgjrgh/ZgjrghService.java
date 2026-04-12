package cn.iocoder.yudao.module.lghjft.service.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;

public interface ZgjrghService {

    /**
     * 获得金融工会信息核对
     *
     * @param id 主键 ID
     * @return 金融工会信息核对
     */
    ZgjrghDO getZgjrgh(Long id);

    /**
     * 获得金融工会信息核对分页
     *
     * @param pageReqVO 分页查询
     * @return 金融工会信息核对分页
     */
    PageResult<ZgjrghDO> getZgjrghPage(ZgjrghPageReqVO pageReqVO);
}
