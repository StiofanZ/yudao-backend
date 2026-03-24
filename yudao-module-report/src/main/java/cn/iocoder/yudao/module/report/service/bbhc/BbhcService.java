package cn.iocoder.yudao.module.report.service.bbhc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.report.controller.admin.bbhc.vo.BbhcPageReqVO;
import cn.iocoder.yudao.module.report.controller.admin.bbhc.vo.BbhcRespVO;
import cn.iocoder.yudao.module.report.service.bbhc.bo.BbhcShowJgBO;
import jakarta.servlet.http.HttpServletRequest;
import org.jeecg.modules.jmreport.common.vo.Result;
import org.jeecg.modules.jmreport.desreport.entity.JimuReport;

import java.util.Map;

public interface BbhcService {

    BbhcShowJgBO hqOrScShow(String bbid, Map<String, Object> cxcs, ShowZxq zxq) throws Throwable;

    PageResult<BbhcRespVO> getDqPage(BbhcPageReqVO reqVO);

    PageResult<BbhcRespVO> getLsPage(BbhcPageReqVO reqVO);

    BbhcRespVO getDq(Long id);

    BbhcRespVO getLs(Long id);

    void cqscDq(Long id, HttpServletRequest request);

    void scDq(Long id);

    void scLs(Long id);

    @FunctionalInterface
    interface ShowZxq {
        Result<JimuReport> zx() throws Throwable;
    }
}
