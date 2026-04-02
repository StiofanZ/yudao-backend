package cn.iocoder.yudao.module.lghjft.service.workflow.tdfsq;


//import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.GhWfTdfsqApproveReqVO;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqKtfxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.tdfsq.vo.GhWfTdfsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhWfTdfsqService {

    GhWfTdfsqResVO getDetail(Long id);

    /**
     * App 端获取退抵费申请详情，带 IDOR 校验（只有申请人才能查看）
     */
    GhWfTdfsqResVO getDetailWithOwnerCheck(Long id);

    Long create(@Valid GhWfTdfsqSaveReqVO req);

    PageResult<GhWfTdfsqDO> getSelfPage(Long userId, GhWfTdfsqAppPageReqVO pageReqVO);

    // ====================== 新增：查询可退费明细 ======================
    List<GhWfTdfsqKtfxxResVO> getKtfxxList(String djxh, Integer sqtflxDm);
}