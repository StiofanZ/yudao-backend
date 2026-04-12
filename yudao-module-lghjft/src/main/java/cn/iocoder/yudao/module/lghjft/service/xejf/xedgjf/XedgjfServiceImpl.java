package cn.iocoder.yudao.module.lghjft.service.xejf.xedgjf;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfDeptScopeResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfQrszItemVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xedgjf.XedgjfMapper;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Validated
public class XedgjfServiceImpl implements XedgjfService {

    @Resource
    private XedgjfMapper xedgjfMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public XedgjfResVO getXedgjf(Long id) {
        XedgjfResVO resVO = xedgjfMapper.selectByHkxxId(id);
        if (resVO == null) {
            return null;
        }
        resVO.setQrszList(xedgjfMapper.selectQrszListByHkxxId(id));
        return resVO;
    }

    @Override
    public XedgjfDeptScopeResVO getDeptScope(Long deptId) {
        XedgjfDeptScopeResVO scope = new XedgjfDeptScopeResVO();
        Long normalizedDeptId = normalizeDeptId(deptId);
        if (normalizedDeptId == null) {
            return scope;
        }
        Map<String, Object> deptRow = queryDeptScopeRow(normalizedDeptId);
        scope.setDeptId(String.valueOf(normalizedDeptId));
        if (deptRow != null) {
            scope.setYhzh(asText(deptRow.get("yhzh")));
            scope.setYhzh1(asText(deptRow.get("yhzh1")));
            scope.setYhzh2(asText(deptRow.get("yhzh2")));
            scope.setYhzh3(asText(deptRow.get("yhzh3")));
        }
        return scope;
    }

    @Override
    public PageResult<XedgjfResVO> getXedgjfPage(XedgjfPageReqVO pageReqVO) {
        fillDefaultScope(pageReqVO);
        if (!StringUtils.hasText(pageReqVO.getLx())) {
            pageReqVO.setLx("3");
        }
        return xedgjfMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateXedgjf(XedgjfSaveReqVO updateReqVO) {
        XedgjfResVO existing = xedgjfMapper.selectByHkxxId(updateReqVO.getHkxxId());
        if (existing == null) {
            throw new ServiceException(400, "小额代管经费记录不存在");
        }
        String nickname = SecurityFrameworkUtils.getLoginUserNickname();
        LocalDateTime now = LocalDateTime.now();
        xedgjfMapper.touchMainRow(updateReqVO.getHkxxId(), nickname);
        xedgjfMapper.deleteQrszByHkxxId(updateReqVO.getHkxxId());
        if (CollectionUtils.isEmpty(updateReqVO.getQrszList())) {
            return;
        }
        for (XedgjfQrszItemVO item : updateReqVO.getQrszList()) {
            item.setHkxxId(updateReqVO.getHkxxId());
            item.setGhHkxxJym(existing.getJym());
            item.setCreateBy(nickname);
            item.setCreateTime(now);
            item.setUpdateBy(nickname);
            item.setUpdateTime(now);
        }
        xedgjfMapper.batchInsertQrsz(updateReqVO.getQrszList());
    }

    private void fillDefaultScope(XedgjfPageReqVO pageReqVO) {
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (StringUtils.hasText(pageReqVO.getDeptId())) {
            XedgjfDeptScopeResVO deptScope = getDeptScope(parseLong(pageReqVO.getDeptId()));
            if (!StringUtils.hasText(pageReqVO.getZh())) {
                pageReqVO.setZh(deptScope.getYhzh());
                pageReqVO.setZh1(deptScope.getYhzh1());
                pageReqVO.setZh2(deptScope.getYhzh2());
                pageReqVO.setZh3(deptScope.getYhzh3());
            }
            pageReqVO.setDeptId(deptScope.getDeptId());
        } else if (loginDeptId != null) {
            XedgjfDeptScopeResVO deptScope = getDeptScope(loginDeptId);
            if (!StringUtils.hasText(pageReqVO.getZh())) {
                pageReqVO.setZh(deptScope.getYhzh());
                pageReqVO.setZh1(deptScope.getYhzh1());
                pageReqVO.setZh2(deptScope.getYhzh2());
                pageReqVO.setZh3(deptScope.getYhzh3());
            }
            pageReqVO.setDeptId(String.valueOf(loginDeptId));
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
    }

    private Long normalizeDeptId(Long deptId) {
        if (deptId == null || deptId == 100000L) {
            return null;
        }
        Map<String, Object> deptRow = queryDeptScopeRow(deptId);
        if (deptRow == null) {
            return deptId;
        }
        Integer currentCount = jdbcTemplate.queryForObject(
                "select count(1) from gh_hkxxxejf where lx = '3' and dept_id = ?",
                Integer.class,
                deptId
        );
        if (currentCount != null && currentCount > 0) {
            return deptId;
        }
        Long parentId = asLong(deptRow.get("parent_id"));
        if (parentId == null || parentId == 0L) {
            return deptId;
        }
        Map<String, Object> parentRow = queryDeptScopeRow(parentId);
        if (parentRow == null) {
            return deptId;
        }
        Integer parentCount = jdbcTemplate.queryForObject(
                "select count(1) from gh_hkxxxejf where lx = '3' and dept_id = ?",
                Integer.class,
                parentId
        );
        if (parentCount != null
                && parentCount > 0
                && Objects.equals(asText(deptRow.get("dept_name")), asText(parentRow.get("dept_name")))) {
            return parentId;
        }
        return deptId;
    }

    private Map<String, Object> queryDeptScopeRow(Long deptId) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "select dept_id, parent_id, dept_name, yhzh, yhzh1, yhzh2, yhzh3 from sys_dept where dept_id = ?",
                deptId
        );
        return rows.isEmpty() ? null : rows.get(0);
    }

    private Long parseLong(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private Long asLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number number) {
            return number.longValue();
        }
        try {
            return Long.parseLong(String.valueOf(value));
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private String asText(Object value) {
        return value == null ? null : String.valueOf(value);
    }
}
