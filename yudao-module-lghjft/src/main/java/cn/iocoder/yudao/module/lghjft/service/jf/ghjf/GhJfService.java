package cn.iocoder.yudao.module.lghjft.service.jf.ghjf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jf.ghjf.GhJfDO;
import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;

/**
 * 税务入库 Service 接口
 *
 * @author 芋道源码
 */
public interface GhJfService {

    /**
     * 创建税务入库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createGhJf(@Valid GhJfCreateReqVO createReqVO);

    /**
     * 更新税务入库
     *
     * @param updateReqVO 更新信息
     */
    void updateGhJf(@Valid GhJfUpdateReqVO updateReqVO);

    /**
     * 删除税务入库
     *
     * @param id 编号
     */
    void deleteGhJf(Integer id);

    /**
     * 获得税务入库
     *
     * @param id 编号
     * @return 税务入库
     */
    GhJfDO getGhJf(Integer id);

    /**
     * 获得税务入库列表
     *
     * @param ids 编号
     * @return 税务入库列表
     */
    List<GhJfDO> getGhJfList(Collection<Integer> ids);

    /**
     * 获得税务入库分页
     *
     * @param pageReqVO 分页查询
     * @return 税务入库分页
     */
    PageResult<GhJfDO> getGhJfPage(GhJfReqVO pageReqVO);

}
