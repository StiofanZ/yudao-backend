package cn.iocoder.yudao.module.lghjft.service.nrgl.zcjd;

import java.util.List;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcjd.ZcjdDO;
import jakarta.validation.Valid;

/**
 * 政策解读 Service 接口
 *
 * @author 芋道源码
 */
public interface ZcjdService {

    /**
     * 创建政策解读
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createZcjd(@Valid ZcjdCreateReqVO createReqVO);

    /**
     * 更新政策解读
     *
     * @param updateReqVO 更新信息
     */
    void updateZcjd(@Valid ZcjdUpdateReqVO updateReqVO);

    /**
     * 删除政策解读
     *
     * @param id 编号
     */
    void deleteZcjd(Long id);

    /**
     * 获得政策解读
     *
     * @param id 编号
     * @return 政策解读
     */
    ZcjdDO getZcjd(Long id);

    /**
     * 获得政策解读列表
     *
     * @param listReqVO 查询条件
     * @return 政策解读列表
     */
    List<ZcjdDO> getZcjdList(ZcjdListReqVO listReqVO);

    /**
     * 发布政策解读
     *
     * @param id 编号
     */
    void publishZcjd(Long id);

    /**
     * 获得公开政策解读列表
     *
     * @param deptId 部门编号
     * @return 政策解读列表
     */
    List<ZcjdDO> getPublicZcjdList(Long deptId);

}
