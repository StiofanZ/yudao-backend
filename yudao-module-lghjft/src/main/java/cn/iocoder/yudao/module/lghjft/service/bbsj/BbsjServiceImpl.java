package cn.iocoder.yudao.module.lghjft.service.bbsj;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.lghjft.controller.app.bbsj.vo.BbsjRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.bbsj.JmBbDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.bbsj.JmBbMapper;
import jakarta.annotation.Resource;
import org.jeecg.modules.jmreport.common.vo.Result;
import org.jeecg.modules.jmreport.desreport.entity.JimuReport;
import org.jeecg.modules.jmreport.desreport.service.IJimuReportService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.BBBM_NOT_SUPPORT;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.BB_ZX_FAIL;

@Service
public class BbsjServiceImpl implements BbsjService {

    @Resource
    private JmBbMapper jmBbMapper;
    @Resource
    private IJimuReportService jimuReportService;

    @Override
    public BbsjRespVO hqBbsj(String bbbm, Map<String, Object> cxcs) {
        JmBbDO bb = jmBbMapper.selectByBbbm(bbbm);
        if (bb == null) {
            throw exception(BBBM_NOT_SUPPORT);
        }

        Map<String, Object> sjcxcs = cxcs == null ? new LinkedHashMap<>() : new LinkedHashMap<>(cxcs);
        Result<JimuReport> zxjg = jimuReportService.show(bb.getId(), JsonUtils.toJsonString(sjcxcs), List.of());
        if (zxjg == null || !zxjg.isSuccess() || zxjg.getResult() == null) {
            throw exception(BB_ZX_FAIL, StrUtil.blankToDefault(zxjg == null ? null : zxjg.getMessage(), "积木报表执行失败"));
        }

        JimuReport jmBb = zxjg.getResult();
        BbsjRespVO respVO = new BbsjRespVO();
        respVO.setBbbm(bb.getCode());
        respVO.setBbmc(StrUtil.blankToDefault(jmBb.getName(), bb.getName()));
        respVO.setCxcs(sjcxcs);
        respVO.setSj(jmBb.getDataList() == null ? new LinkedHashMap<>() : new LinkedHashMap<>(jmBb.getDataList()));
        return respVO;
    }
}
