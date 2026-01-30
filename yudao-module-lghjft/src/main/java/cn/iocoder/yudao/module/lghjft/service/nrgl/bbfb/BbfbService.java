package cn.iocoder.yudao.module.lghjft.service.nrgl.bbfb;

import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bbfb.BbfbDO;

import java.util.List;

/**
 * 版本发布 Service 接口
 *
 * @author 芋道源码
 */
public interface BbfbService {

    /**
     * 创建版本发布
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBbfb(BbfbCreateReqVO createReqVO);

    /**
     * 更新版本发布
     *
     * @param updateReqVO 更新信息
     */
    void updateBbfb(BbfbUpdateReqVO updateReqVO);

    /**
     * 删除版本发布
     *
     * @param id 编号
     */
    void deleteBbfb(Long id);

    /**
     * 获得版本发布
     *
     * @param id 编号
     * @return 版本发布
     */
    BbfbDO getBbfb(Long id);

    /**
     * 获得版本发布列表
     *
     * @param listReqVO 查询条件
     * @return 版本发布列表
     */
    List<BbfbDO> getBbfbList(BbfbListReqVO listReqVO);

    /**
     * 发布版本
     *
     * @param id 编号
     */
    void publish(Long id);

    /**
     * 获得公开版本发布列表
     *
     * @return 版本发布列表
     */
    List<BbfbDO> getPublicBbfbList();

}
