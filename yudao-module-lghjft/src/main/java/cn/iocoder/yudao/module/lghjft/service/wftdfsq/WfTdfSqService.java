package cn.iocoder.yudao.module.lghjft.service.wftdfsq;


//import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqApproveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqSaveReqVO;
import jakarta.validation.Valid;

public interface WfTdfSqService {

    WfTdfSqRespVO getDetail(Long id);

    Long create(@Valid WfTdfSqSaveReqVO req);



}