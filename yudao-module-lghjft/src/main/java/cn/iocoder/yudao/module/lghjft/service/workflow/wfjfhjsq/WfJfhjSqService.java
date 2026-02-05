package cn.iocoder.yudao.module.lghjft.service.workflow.wfjfhjsq;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq.vo.WfJfhjSqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq.WfJfhjSqDO;
import jakarta.validation.*;

/**
 * 工会经费缓缴申请 Service 接口
 *
 * @author 李文军
 */
public interface WfJfhjSqService {

    /**
     * 创建工会经费缓缴申请
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWfJfhjSq(@Valid WfJfhjSqSaveReqVO createReqVO);




    WfJfhjSqDO getWfJfhjSq(Long id);


}