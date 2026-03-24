package cn.iocoder.yudao.module.report.framework.jmreport.core.aop;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.report.framework.jmreport.core.web.BbhcRequestHolder;
import cn.iocoder.yudao.module.report.service.bbhc.BbhcService;
import cn.iocoder.yudao.module.report.service.bbhc.bo.BbhcShowJgBO;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jeecg.modules.jmreport.common.vo.Result;
import org.jeecg.modules.jmreport.desreport.entity.JimuReport;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Component
public class JmBbhcAspect {

    @Resource
    private BbhcService bbhcService;

    @Around("execution(* org.jeecg.modules.jmreport.desreport.service.IJimuReportService.show(..))")
    public Object aroundShow(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length < 2 || !(args[0] instanceof String bbid)) {
            return joinPoint.proceed();
        }
        Map<String, Object> cxcs = hqCxcs(args[1]);
        BbhcShowJgBO jg = bbhcService.hqOrScShow(bbid, cxcs,
                () -> (Result<JimuReport>) joinPoint.proceed());
        if (jg.getHc() != null) {
            BbhcRequestHolder.set(jg.getHc());
        } else {
            BbhcRequestHolder.clear();
        }
        return jg.getJg();
    }

    private Map<String, Object> hqCxcs(Object params) {
        if (params == null) {
            return new LinkedHashMap<>();
        }
        if (params instanceof String paramsStr) {
            if (StrUtil.isBlank(paramsStr)) {
                return new LinkedHashMap<>();
            }
            Map<String, Object> map = JsonUtils.parseObjectQuietly(paramsStr, new TypeReference<Map<String, Object>>() {
            });
            return map == null ? new LinkedHashMap<>() : new LinkedHashMap<>(map);
        }
        return new LinkedHashMap<>();
    }
}
