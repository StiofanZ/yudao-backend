package cn.iocoder.yudao.module.report.service.bbhc;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.report.controller.admin.bbhc.vo.BbhcPageReqVO;
import cn.iocoder.yudao.module.report.controller.admin.bbhc.vo.BbhcRespVO;
import cn.iocoder.yudao.module.report.dal.dataobject.bbhc.GhBbsjHcDO;
import cn.iocoder.yudao.module.report.dal.dataobject.bbhc.GhBbsjHcLsDO;
import cn.iocoder.yudao.module.report.dal.mysql.bbhc.GhBbsjHcLsMapper;
import cn.iocoder.yudao.module.report.dal.mysql.bbhc.GhBbsjHcMapper;
import cn.iocoder.yudao.module.report.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.report.service.bbhc.bo.BbhcShowJgBO;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.jeecg.modules.jmreport.common.vo.Result;
import org.jeecg.modules.jmreport.desreport.entity.JimuReport;
import org.jeecg.modules.jmreport.desreport.service.IJimuReportService;
import org.springframework.aop.framework.Advised;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

@Service
@Validated
public class BbhcServiceImpl implements BbhcService {

    private static final String ZXLX_SHOW = "show";
    private static final String REDIS_KEY = "report:bbhc:dq:%s:%s:%s:%s:%s";
    private static final Duration REDIS_TTL = Duration.ofDays(7);
    private static final Set<String> HC_KEY_IGNORE = Set.of(
            "token", "tenantId", "pcbh", "ywrq", "pageNo", "pageSize",
            "jmViewFirstLoad", "currentPageNo", "currentPageSize", "isCurrentPage", "bigDataMode"
    );
    private static final Set<String> BC_CS_IGNORE = Set.of("token", "tenantId", "pcbh", "ywrq");

    @Resource
    private GhBbsjHcMapper ghBbsjHcMapper;
    @Resource
    private GhBbsjHcLsMapper ghBbsjHcLsMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private IJimuReportService jimuReportService;

    @Override
    public BbhcShowJgBO hqOrScShow(String bbid, Map<String, Object> cxcs, ShowZxq zxq) throws Throwable {
        return hqOrScShow(bbid, cxcs, zxq, false);
    }

    @Override
    public PageResult<BbhcRespVO> getDqPage(BbhcPageReqVO reqVO) {
        return zcPage(ghBbsjHcMapper.selectPage(reqVO, hqTenantId()));
    }

    @Override
    public PageResult<BbhcRespVO> getLsPage(BbhcPageReqVO reqVO) {
        return zcLsPage(ghBbsjHcLsMapper.selectPage(reqVO, hqTenantId()));
    }

    @Override
    public BbhcRespVO getDq(Long id) {
        GhBbsjHcDO hc = hqDq(id);
        return zcResp(hc, null);
    }

    @Override
    public BbhcRespVO getLs(Long id) {
        GhBbsjHcLsDO hc = hqLs(id);
        return zcResp(null, hc);
    }

    @Override
    public void cqscDq(Long id, HttpServletRequest request) {
        GhBbsjHcDO hc = hqDq(id);
        Map<String, Object> cxcs = hqCxcsJson(hc.getCxcsJson());
        if (hc.getYwrq() != null) {
            cxcs.put("ywrq", hc.getYwrq().toString());
        }
        try {
            hqOrScShow(hc.getBbid(), cxcs,
                    () -> hqNativeJmService().show(hc.getBbid(), JsonUtils.toJsonString(cxcs), List.of()), true);
        } catch (Throwable e) {
            throw new ServiceException(ErrorCodeConstants.BBHC_CQSC_FAIL.getCode(), e.getMessage());
        }
    }

    @Override
    public void scDq(Long id) {
        GhBbsjHcDO hc = hqDq(id);
        ghBbsjHcMapper.deleteById(id);
        scRedis(hc);
    }

    @Override
    public void scLs(Long id) {
        hqLs(id);
        ghBbsjHcLsMapper.deleteById(id);
    }

    private BbhcShowJgBO hqOrScShow(String bbid, Map<String, Object> cxcs, ShowZxq zxq, boolean qzsc) throws Throwable {
        Long tenantId = hqTenantId();
        Map<String, Object> yxCxcs = hqYxCxcs(cxcs);
        String pcbh = ObjectUtil.defaultIfNull(yxCxcs.get("pcbh"), "").toString();
        if (StrUtil.isNotBlank(pcbh) && !qzsc) {
            return hqLsByPcbh(tenantId, pcbh);
        }

        LocalDate ywrq = hqYwrq(yxCxcs);
        JimuReport bb = hqBb(bbid);
        String cszy = hqCszy(yxCxcs);
        String bbgxbs = hqBbgxbs(bb);
        String redisKey = hqRedisKey(tenantId, bbid, ZXLX_SHOW, ywrq, cszy);
        if (!qzsc) {
            GhBbsjHcDO redisHc = hqRedis(redisKey, bbgxbs);
            if (redisHc != null) {
                return new BbhcShowJgBO(redisHc, hqJg(redisHc.getJgJson()));
            }
            GhBbsjHcDO dqHc = ghBbsjHcMapper.selectByZy(tenantId, bbid, ZXLX_SHOW, ywrq, cszy);
            if (dqHc != null && StrUtil.equals(dqHc.getBbgxbs(), bbgxbs)) {
                bcRedis(redisKey, dqHc);
                return new BbhcShowJgBO(dqHc, hqJg(dqHc.getJgJson()));
            }
        }

        Result<JimuReport> zxjg = zxq.zx();
        if (zxjg == null || !zxjg.isSuccess() || zxjg.getResult() == null) {
            return new BbhcShowJgBO(null, zxjg);
        }

        String cxcsJson = JsonUtils.toJsonString(hqBcCxcs(yxCxcs));
        String jgJson = JsonUtils.toJsonString(zxjg);
        GhBbsjHcDO old = ghBbsjHcMapper.selectByZy(tenantId, bbid, ZXLX_SHOW, ywrq, cszy);
        if (old != null) {
            bcLs(old);
            old.setBbbm(bb.getCode());
            old.setBbmc(StrUtil.blankToDefault(bb.getName(), old.getBbmc()));
            old.setCxcsJson(cxcsJson);
            old.setPcbh(hqPcbh());
            old.setBbgxbs(bbgxbs);
            old.setJgJson(jgJson);
            old.setScsj(LocalDateTime.now());
            old.setScr(hqScr());
            ghBbsjHcMapper.updateById(old);
            bcRedis(redisKey, old);
            return new BbhcShowJgBO(old, zxjg);
        }

        GhBbsjHcDO hc = GhBbsjHcDO.builder()
                .tenantId(tenantId)
                .bbid(bb.getId())
                .bbbm(bb.getCode())
                .bbmc(StrUtil.blankToDefault(bb.getName(), bb.getCode()))
                .zxlx(ZXLX_SHOW)
                .ywrq(ywrq)
                .cxcsJson(cxcsJson)
                .cszy(cszy)
                .pcbh(hqPcbh())
                .bbgxbs(bbgxbs)
                .jgJson(jgJson)
                .scsj(LocalDateTime.now())
                .scr(hqScr())
                .build();
        ghBbsjHcMapper.insert(hc);
        bcRedis(redisKey, hc);
        return new BbhcShowJgBO(hc, zxjg);
    }

    private BbhcShowJgBO hqLsByPcbh(Long tenantId, String pcbh) {
        GhBbsjHcDO dqHc = ghBbsjHcMapper.selectByPcbh(tenantId, pcbh);
        if (dqHc != null) {
            return new BbhcShowJgBO(dqHc, hqJg(dqHc.getJgJson()));
        }
        GhBbsjHcLsDO lsHc = ghBbsjHcLsMapper.selectByPcbh(tenantId, pcbh);
        if (lsHc != null) {
            GhBbsjHcDO tmp = GhBbsjHcDO.builder()
                    .id(lsHc.getHcId())
                    .tenantId(lsHc.getTenantId())
                    .bbid(lsHc.getBbid())
                    .bbbm(lsHc.getBbbm())
                    .bbmc(lsHc.getBbmc())
                    .zxlx(lsHc.getZxlx())
                    .ywrq(lsHc.getYwrq())
                    .cxcsJson(lsHc.getCxcsJson())
                    .cszy(lsHc.getCszy())
                    .pcbh(lsHc.getPcbh())
                    .bbgxbs(lsHc.getBbgxbs())
                    .jgJson(lsHc.getJgJson())
                    .scsj(lsHc.getScsj())
                    .scr(lsHc.getScr())
                    .build();
            return new BbhcShowJgBO(tmp, hqJg(lsHc.getJgJson()));
        }
        throw exception(ErrorCodeConstants.BBHC_NOT_EXISTS);
    }

    private void bcLs(GhBbsjHcDO hc) {
        GhBbsjHcLsDO ls = GhBbsjHcLsDO.builder()
                .hcId(hc.getId())
                .tenantId(hc.getTenantId())
                .bbid(hc.getBbid())
                .bbbm(hc.getBbbm())
                .bbmc(hc.getBbmc())
                .zxlx(hc.getZxlx())
                .ywrq(hc.getYwrq())
                .cxcsJson(hc.getCxcsJson())
                .cszy(hc.getCszy())
                .pcbh(hc.getPcbh())
                .bbgxbs(hc.getBbgxbs())
                .jgJson(hc.getJgJson())
                .scsj(hc.getScsj())
                .scr(hc.getScr())
                .gdsj(LocalDateTime.now())
                .build();
        ghBbsjHcLsMapper.insert(ls);
    }

    private PageResult<BbhcRespVO> zcPage(PageResult<GhBbsjHcDO> pageResult) {
        return new PageResult<>(pageResult.getList().stream().map(item -> zcJbResp(item, null)).toList(), pageResult.getTotal());
    }

    private PageResult<BbhcRespVO> zcLsPage(PageResult<GhBbsjHcLsDO> pageResult) {
        return new PageResult<>(pageResult.getList().stream().map(item -> zcJbResp(null, item)).toList(), pageResult.getTotal());
    }

    private BbhcRespVO zcResp(GhBbsjHcDO dq, GhBbsjHcLsDO ls) {
        BbhcRespVO respVO = new BbhcRespVO();
        if (dq != null) {
            respVO.setId(dq.getId());
            respVO.setBbid(dq.getBbid());
            respVO.setBbbm(dq.getBbbm());
            respVO.setBbmc(dq.getBbmc());
            respVO.setZxlx(dq.getZxlx());
            respVO.setYwrq(dq.getYwrq());
            respVO.setPcbh(dq.getPcbh());
            respVO.setBbgxbs(dq.getBbgxbs());
            respVO.setCszy(dq.getCszy());
            respVO.setCxcs(hqCxcsJson(dq.getCxcsJson()));
            respVO.setJgJson(dq.getJgJson());
            respVO.setScsj(dq.getScsj());
            respVO.setScr(dq.getScr());
            return respVO;
        }
        respVO.setId(ls.getId());
        respVO.setHcId(ls.getHcId());
        respVO.setBbid(ls.getBbid());
        respVO.setBbbm(ls.getBbbm());
        respVO.setBbmc(ls.getBbmc());
        respVO.setZxlx(ls.getZxlx());
        respVO.setYwrq(ls.getYwrq());
        respVO.setPcbh(ls.getPcbh());
        respVO.setBbgxbs(ls.getBbgxbs());
        respVO.setCszy(ls.getCszy());
        respVO.setCxcs(hqCxcsJson(ls.getCxcsJson()));
        respVO.setJgJson(ls.getJgJson());
        respVO.setScsj(ls.getScsj());
        respVO.setScr(ls.getScr());
        respVO.setGdsj(ls.getGdsj());
        return respVO;
    }

    private BbhcRespVO zcJbResp(GhBbsjHcDO dq, GhBbsjHcLsDO ls) {
        BbhcRespVO respVO = zcResp(dq, ls);
        respVO.setCxcs(new LinkedHashMap<>());
        respVO.setJgJson(null);
        return respVO;
    }

    private GhBbsjHcDO hqDq(Long id) {
        GhBbsjHcDO hc = ghBbsjHcMapper.selectById(id);
        if (hc == null || Boolean.TRUE.equals(hc.getDeleted()) || !Objects.equals(hc.getTenantId(), hqTenantId())) {
            throw exception(ErrorCodeConstants.BBHC_NOT_EXISTS);
        }
        return hc;
    }

    private GhBbsjHcLsDO hqLs(Long id) {
        GhBbsjHcLsDO hc = ghBbsjHcLsMapper.selectById(id);
        if (hc == null || Boolean.TRUE.equals(hc.getDeleted()) || !Objects.equals(hc.getTenantId(), hqTenantId())) {
            throw exception(ErrorCodeConstants.BBHC_LS_NOT_EXISTS);
        }
        return hc;
    }

    private GhBbsjHcDO hqRedis(String redisKey, String bbgxbs) {
        Object cache = redisTemplate.opsForValue().get(redisKey);
        if (!(cache instanceof String cacheStr) || StrUtil.isBlank(cacheStr)) {
            return null;
        }
        GhBbsjHcDO hc = JsonUtils.parseObjectQuietly(cacheStr, new TypeReference<GhBbsjHcDO>() {
        });
        if (hc == null || !StrUtil.equals(hc.getBbgxbs(), bbgxbs)) {
            redisTemplate.delete(redisKey);
            return null;
        }
        return hc;
    }

    private void bcRedis(String redisKey, GhBbsjHcDO hc) {
        redisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(hc), REDIS_TTL);
    }

    private void scRedis(GhBbsjHcDO hc) {
        redisTemplate.delete(hqRedisKey(hc.getTenantId(), hc.getBbid(), hc.getZxlx(), hc.getYwrq(), hc.getCszy()));
    }

    private Result<JimuReport> hqJg(String jgJson) {
        return JsonUtils.parseObject(jgJson, new TypeReference<Result<JimuReport>>() {
        });
    }

    private JimuReport hqBb(String bbid) {
        JimuReport bb = jimuReportService.getById(bbid);
        if (bb == null) {
            throw exception(ErrorCodeConstants.BBHC_BB_NOT_EXISTS);
        }
        return bb;
    }

    private String hqBbgxbs(JimuReport bb) {
        if (bb.getUpdateCount() != null) {
            return String.valueOf(bb.getUpdateCount());
        }
        if (bb.getUpdateTime() != null) {
            return String.valueOf(bb.getUpdateTime().getTime());
        }
        return "0";
    }

    private String hqPcbh() {
        return IdUtil.getSnowflakeNextIdStr();
    }

    private Long hqTenantId() {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null && loginUser.getTenantId() != null) {
            return loginUser.getTenantId();
        }
        Long tenantId = TenantContextHolder.getTenantId();
        if (tenantId != null) {
            return tenantId;
        }
        HttpServletRequest request = hqRequest();
        if (request != null) {
            tenantId = WebFrameworkUtils.getTenantId(request);
            if (tenantId != null) {
                return tenantId;
            }
        }
        return 0L;
    }

    private String hqScr() {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null) {
            return String.valueOf(loginUser.getId());
        }
        return "system";
    }

    private HttpServletRequest hqRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs == null ? null : attrs.getRequest();
    }

    private Map<String, Object> hqYxCxcs(Map<String, Object> cxcs) {
        return cxcs == null ? new LinkedHashMap<>() : new LinkedHashMap<>(cxcs);
    }

    private Map<String, Object> hqBcCxcs(Map<String, Object> cxcs) {
        return zcMap(cxcs, BC_CS_IGNORE);
    }

    private String hqCszy(Map<String, Object> cxcs) {
        return DigestUtil.md5Hex(JsonUtils.toJsonString(zcSxMap(zcMap(cxcs, HC_KEY_IGNORE))));
    }

    private Map<String, Object> zcMap(Map<String, Object> cxcs, Set<String> ignoreKeys) {
        Map<String, Object> result = new LinkedHashMap<>();
        cxcs.forEach((key, value) -> {
            if (!ignoreKeys.contains(key)) {
                result.put(key, value);
            }
        });
        return result;
    }

    @SuppressWarnings("unchecked")
    private Object zcSxObj(Object obj) {
        if (obj instanceof Map<?, ?> map) {
            TreeMap<String, Object> result = new TreeMap<>();
            map.forEach((key, value) -> result.put(String.valueOf(key), zcSxObj(value)));
            return result;
        }
        if (obj instanceof List<?> list) {
            List<Object> result = new ArrayList<>();
            list.forEach(item -> result.add(zcSxObj(item)));
            return result;
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> zcSxMap(Map<String, Object> cxcs) {
        return (Map<String, Object>) zcSxObj(cxcs);
    }

    private LocalDate hqYwrq(Map<String, Object> cxcs) {
        Object ywrq = cxcs.get("ywrq");
        if (ywrq == null) {
            return LocalDate.now();
        }
        if (ywrq instanceof LocalDate localDate) {
            return localDate;
        }
        if (ywrq instanceof LocalDateTime localDateTime) {
            return localDateTime.toLocalDate();
        }
        if (ywrq instanceof Date date) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        String value = String.valueOf(ywrq);
        if (StrUtil.isBlank(value)) {
            return LocalDate.now();
        }
        if (value.length() >= 10) {
            value = value.substring(0, 10);
        }
        return LocalDate.parse(value);
    }

    private Map<String, Object> hqCxcsJson(String cxcsJson) {
        Map<String, Object> map = JsonUtils.parseObjectQuietly(cxcsJson, new TypeReference<Map<String, Object>>() {
        });
        return map == null ? new LinkedHashMap<>() : new LinkedHashMap<>(map);
    }

    private String hqRedisKey(Long tenantId, String bbid, String zxlx, LocalDate ywrq, String cszy) {
        return String.format(REDIS_KEY, tenantId, bbid, zxlx, ywrq, cszy);
    }

    private IJimuReportService hqNativeJmService() {
        try {
            if (jimuReportService instanceof Advised advised) {
                Object target = advised.getTargetSource().getTarget();
                if (target instanceof IJimuReportService service) {
                    return service;
                }
            }
            return jimuReportService;
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeConstants.BBHC_CQSC_FAIL.getCode(), e.getMessage());
        }
    }
}
