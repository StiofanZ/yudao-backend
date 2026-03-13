package cn.iocoder.yudao.module.lghjft.service.workflow.dwdbsq;

import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 工会隶属关系调拨申请 Service 接口
 *
 * @author 李文军
 */
public interface WfDbsqService {

    /**
     * 创建工会隶属关系调拨申请
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid WfDbsqSaveReqVO createReqVO);


    /**
     * 获得工会隶属关系调拨申请
     *
     * @param id 编号
     * @return 工会隶属关系调拨申请
     */
    WfDbsqRespVO getDetail(Long id);

    /**
     * 获得工会隶属关系调拨申请分页
     *
     * @param pageReqVO 分页查询
     * @return 工会隶属关系调拨申请分页
     */

    PageResult<WfDbsqDO> getSelfPage(Long userId, WfDbsqPageReqVO pageReqVO);
}