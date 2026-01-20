package cn.iocoder.yudao.module.lghjft.service.nrgl.bszn;

import java.util.List;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bszn.BsznDO;
import jakarta.validation.Valid;

/**
 * 办事指南 Service 接口
 *
 * @author 芋道源码
 */
public interface BsznService {

    /**
     * 创建办事指南
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBszn(@Valid BsznCreateReqVO createReqVO);

    /**
     * 更新办事指南
     *
     * @param updateReqVO 更新信息
     */
    void updateBszn(@Valid BsznUpdateReqVO updateReqVO);

    /**
     * 删除办事指南
     *
     * @param id 编号
     */
    void deleteBszn(Long id);

    /**
     * 获得办事指南
     *
     * @param id 编号
     * @return 办事指南
     */
    BsznDO getBszn(Long id);

    /**
     * 获得办事指南列表
     *
     * @param listReqVO 查询条件
     * @return 办事指南列表
     */
    List<BsznDO> getBsznList(BsznListReqVO listReqVO);

    /**
     * 发布办事指南
     *
     * @param id 编号
     */
    void publishBszn(Long id);

    /**
     * 获得公开办事指南列表
     *
     * @param deptId 部门编号
     * @return 办事指南列表
     */
    List<BsznDO> getPublicBsznList(Long deptId);

}
