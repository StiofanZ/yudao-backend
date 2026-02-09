package cn.iocoder.yudao.module.lghjft.service.workflow.wftdfsq;


//import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqApproveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo.WfTdfSqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo.WfTdfSqSaveReqVO;
import jakarta.validation.Valid;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.wftdfsq.vo.WfTdfSqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wftdfsq.WfTdfSqDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

public interface WfTdfSqService {

    WfTdfSqRespVO getDetail(Long id);

    Long create(@Valid WfTdfSqSaveReqVO req);

    PageResult<WfTdfSqDO> getSelfPage(Long userId, WfTdfSqAppPageReqVO pageReqVO);



}