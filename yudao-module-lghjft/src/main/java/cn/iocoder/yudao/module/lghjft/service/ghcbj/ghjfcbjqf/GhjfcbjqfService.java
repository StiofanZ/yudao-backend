package cn.iocoder.yudao.module.lghjft.service.ghcbj.ghjfcbjqf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjqfDO;

/**
 * 筹备金全返 Service 接口
 */
public interface GhjfcbjqfService {

    /**
     * 获得筹备金全返分页
     *
     * @param pageReqVO 分页查询
     * @return 分页结果
     */
    PageResult<GhjfcbjqfDO> getGhjfcbjqfPage(GhjfcbjqfPageReqVO pageReqVO);

    GhjfcbjqfDO getGhjfcbjqf(Long id);

    void updateGhjfcbjqf(GhjfcbjqfSaveReqVO updateReqVO);
}
