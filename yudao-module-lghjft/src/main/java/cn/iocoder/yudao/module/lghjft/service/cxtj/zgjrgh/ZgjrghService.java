package cn.iocoder.yudao.module.lghjft.service.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;
import jakarta.validation.Valid;

public interface ZgjrghService {

    /**
     * 创建金融工会信息核对
     *
     * @param createReqVO 创建信息
     * @return 主键 ID
     */
    Long createZgjrgh(@Valid ZgjrghSaveReqVO createReqVO);

    /**
     * 更新金融工会信息核对
     *
     * @param updateReqVO 更新信息
     */
    void updateZgjrgh(@Valid ZgjrghSaveReqVO updateReqVO);

    /**
     * 删除金融工会信息核对
     *
     * @param id 主键 ID
     */
    void deleteZgjrgh(Long id);

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
