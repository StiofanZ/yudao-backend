package cn.iocoder.yudao.module.lghjft.service.workflow.jfhzjnsq;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhzjnsq.vo.GhWfJfhzjnsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.GhWfJfhzjnsqDO;
import jakarta.validation.Valid;

/**
 * 工会经费汇总缴纳申请表（主表） Service 接口
 *
 * @author 李文军
 */
public interface GhWfJfhzjnsqService {

    /**
     * 创建工会经费汇总缴纳申请表（主表）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGhWfJfhzjnsq(@Valid GhWfJfhzjnsqSaveReqVO createReqVO);


    /**
     * 获得工会经费汇总缴纳申请表（主表）
     *
     * @param id 编号
     * @return 工会经费汇总缴纳申请表（主表）
     */
    GhWfJfhzjnsqRespVO getDetail(Long id);

    /**
     * 获得工会经费汇总缴纳申请分页（只查询自己的）
     *
     * @param userId    当前登录用户ID
     * @param pageReqVO 分页查询条件
     * @return 分页结果
     */
    PageResult<GhWfJfhzjnsqDO> getSelfPage(Long userId, GhWfJfhzjnsqAppPageReqVO pageReqVO);

}