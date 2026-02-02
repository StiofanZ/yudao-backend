package cn.iocoder.yudao.module.lghjft.service.wftdfsq;


//import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqApproveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqRespVO;

public interface WfTdfSqService {

    WfTdfSqRespVO getDetail(Long id);

    Long create(WfTdfSqSaveReqVO req);

// void approve(WfTdfSqApproveReqVO req);

}