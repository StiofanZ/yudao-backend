package cn.gszs.yudao.module.lghjft.service.wfsqtfsq;

import cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqPageReqVO;
import cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqSaveReqVO;
import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqDO;
import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsqmx.WfSqTfsqmxDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 申请-退费申请 Service 接口
 *
 * @author 李文军
 */
public interface WfSqTfsqService {

    /**
     * 创建申请-退费申请
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWfSqTfsq(@Valid WfSqTfsqSaveReqVO createReqVO);

    /**
     * 更新申请-退费申请
     *
     * @param updateReqVO 更新信息
     */
    void updateWfSqTfsq(@Valid WfSqTfsqSaveReqVO updateReqVO);

    /**
     * 删除申请-退费申请
     *
     * @param id 编号
     */
    void deleteWfSqTfsq(Long id);

    /**
     * 批量删除申请-退费申请
     *
     * @param ids 编号
     */
    void deleteWfSqTfsqListByIds(List<Long> ids);

    /**
     * 获得申请-退费申请
     *
     * @param id 编号
     * @return 申请-退费申请
     */
    WfSqTfsqDO getWfSqTfsq(Long id);

    /**
     * 获得申请-退费申请分页
     *
     * @param pageReqVO 分页查询
     * @return 申请-退费申请分页
     */
    PageResult<WfSqTfsqDO> getWfSqTfsqPage(WfSqTfsqPageReqVO pageReqVO);

    // ==================== 子表（申请-退费申请明细） ====================

    /**
     * 获得申请-退费申请明细列表
     *
     * @param id 退费申请明细ID
     * @return 申请-退费申请明细列表
     */
    List<WfSqTfsqmxDO> getWfSqTfsqmxListById(Long id);

}