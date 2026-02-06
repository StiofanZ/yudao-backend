package cn.iocoder.yudao.module.lghjft.service.workflow.wftdfsq;


//import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqApproveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo.WfTdfSqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo.WfTdfSqSaveReqVO;
import jakarta.validation.Valid;

public interface WfTdfSqService {

    WfTdfSqRespVO getDetail(Long id);

    Long create(@Valid WfTdfSqSaveReqVO req);



}