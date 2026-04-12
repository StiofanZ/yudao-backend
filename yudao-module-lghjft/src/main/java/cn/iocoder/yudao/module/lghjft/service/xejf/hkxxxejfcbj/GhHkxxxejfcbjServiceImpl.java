package cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejfcbj;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjQrszItemVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfcbj.GhHkxxxejfcbjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejfcbj.GhHkxxxejfcbjMapper;
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

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserNickname;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXXXEJFCBJ_NOT_EXISTS;

@Service
@Validated
public class GhHkxxxejfcbjServiceImpl implements GhHkxxxejfcbjService {

    @Resource
    private GhHkxxxejfcbjMapper ghHkxxxejfcbjMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createGhHkxxxejfcbj(GhHkxxxejfcbjSaveReqVO createReqVO) {
        GhHkxxxejfcbjDO entity = BeanUtils.toBean(createReqVO, GhHkxxxejfcbjDO.class);
        ghHkxxxejfcbjMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGhHkxxxejfcbj(GhHkxxxejfcbjSaveReqVO updateReqVO) {
        GhHkxxxejfcbjResVO existing = ghHkxxxejfcbjMapper.selectByHkxxId(updateReqVO.getHkxxId());
        if (existing == null) {
            throw new ServiceException(400, "小额筹备金做账记录不存在");
        }
        String nickname = getLoginUserNickname();
        LocalDateTime now = LocalDateTime.now();
        ghHkxxxejfcbjMapper.touchMainRow(updateReqVO.getHkxxId(), nickname);
        ghHkxxxejfcbjMapper.deleteQrszByHkxxId(updateReqVO.getHkxxId());
        if (CollectionUtils.isEmpty(updateReqVO.getCbjqrszList())) {
            return;
        }
        for (GhHkxxxejfcbjQrszItemVO item : updateReqVO.getCbjqrszList()) {
            item.setHkxxId(updateReqVO.getHkxxId());
            item.setGhHkxxJym(existing.getJym());
            item.setCreateBy(nickname);
            item.setCreateTime(now);
            item.setUpdateBy(nickname);
            item.setUpdateTime(now);
        }
        ghHkxxxejfcbjMapper.batchInsertQrsz(updateReqVO.getCbjqrszList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHkxxxejfcbj(Long id) {
        validateExists(id);
        ghHkxxxejfcbjMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHkxxxejfcbjListByIds(List<Long> ids) {
        ghHkxxxejfcbjMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (ghHkxxxejfcbjMapper.selectById(id) == null) {
            throw exception(HKXXXEJFCBJ_NOT_EXISTS);
        }
    }

    @Override
    public GhHkxxxejfcbjResVO getGhHkxxxejfcbj(Long id) {
        GhHkxxxejfcbjResVO resVO = ghHkxxxejfcbjMapper.selectByHkxxId(id);
        if (resVO == null) {
            return null;
        }
        resVO.setCbjqrszList(ghHkxxxejfcbjMapper.selectQrszListByHkxxId(id));
        return resVO;
    }

    @Override
    public PageResult<GhHkxxxejfcbjResVO> getGhHkxxxejfcbjPage(GhHkxxxejfcbjPageReqVO pageReqVO) {
        fillDefaultScope(pageReqVO);
        return ghHkxxxejfcbjMapper.selectPage(pageReqVO);
    }

    private void fillDefaultScope(GhHkxxxejfcbjPageReqVO pageReqVO) {
        Long loginDeptId = getLoginUserDeptId();
        if (!StringUtils.hasText(pageReqVO.getZh()) && loginDeptId != null) {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                    "select yhzh, yhzh1, yhzh2, yhzh3 from sys_dept where dept_id = ?",
                    loginDeptId);
            if (!rows.isEmpty()) {
                Map<String, Object> row = rows.get(0);
                pageReqVO.setZh(asText(row.get("yhzh")));
                pageReqVO.setZh1(asText(row.get("yhzh1")));
                pageReqVO.setZh2(asText(row.get("yhzh2")));
                pageReqVO.setZh3(asText(row.get("yhzh3")));
            }
        }
        if (!StringUtils.hasText(pageReqVO.getDeptId()) && loginDeptId != null) {
            pageReqVO.setDeptId(String.valueOf(loginDeptId));
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
    }

    private String asText(Object value) {
        return value == null ? null : String.valueOf(value);
    }
}
