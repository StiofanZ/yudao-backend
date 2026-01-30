package cn.iocoder.yudao.module.lghjft.service.nrgl.bszn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznReqVO;
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
     * 获得办事指南分页列表
     * 支持管理端查询和公开端查询
     *
     * @param listReqVO 查询条件
     * @return 办事指南分页列表
     */
    PageResult<BsznDO> getBsznPage(BsznReqVO listReqVO);

    /**
     * 发布办事指南
     *
     * @param id 编号
     */
    void publishBszn(Long id);

    /**
     * 下架办事指南
     *
     * @param id 编号
     * @param reason 下架原因
     */
    void offShelfBszn(Long id, String reason);

    /**
     * 审核办事指南
     *
     * @param id 编号
     * @param status 状态
     */
    void auditBszn(Long id, Integer status);

}
