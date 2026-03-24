package cn.iocoder.yudao.module.lghjft.service.workflow.tdfsq;


//import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.GhWfTdfsqApproveReqVO;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqKtfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.tdfsq.vo.GhWfTdfsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhWfTdfsqService {

    GhWfTdfsqRespVO getDetail(Long id);

    Long create(@Valid GhWfTdfsqSaveReqVO req);

    PageResult<GhWfTdfsqDO> getSelfPage(Long userId, GhWfTdfsqAppPageReqVO pageReqVO);

    // ====================== 新增：查询可退费明细 ======================
    List<GhWfTdfsqKtfxxRespVO> getKtfxxList(String djxh, Integer sqtflxDm);
}