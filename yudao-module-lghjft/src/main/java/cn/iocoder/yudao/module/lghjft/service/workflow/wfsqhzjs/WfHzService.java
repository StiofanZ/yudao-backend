package cn.iocoder.yudao.module.lghjft.service.workflow.wfsqhzjs;

import java.util.*;

//import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfsqhzjf.vo.WfHzAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf.WfHzDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 工会经费汇总缴纳申请表（主表） Service 接口
 *
 * @author 李文军
 */
public interface WfHzService {

    /**
     * 创建工会经费汇总缴纳申请表（主表）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWfHz(@Valid WfHzSaveReqVO createReqVO);


    /**
     * 获得工会经费汇总缴纳申请表（主表）
     *
     * @param id 编号
     * @return 工会经费汇总缴纳申请表（主表）
     */
    WfHzRespVO getDetail(Long id);

    /**
     * 获得工会经费汇总缴纳申请分页（只查询自己的）
     *
     * @param userId 当前登录用户ID
     * @param pageReqVO 分页查询条件
     * @return 分页结果
     */
    PageResult<WfHzDO> getSelfPage(Long userId, WfHzAppPageReqVO pageReqVO);

}