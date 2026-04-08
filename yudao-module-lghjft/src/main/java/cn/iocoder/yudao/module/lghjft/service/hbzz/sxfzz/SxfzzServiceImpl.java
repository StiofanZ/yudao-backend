package cn.iocoder.yudao.module.lghjft.service.hbzz.sxfzz;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.sxfzz.SxfzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.sxfzz.SxfzzMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class SxfzzServiceImpl implements SxfzzService {

    @Resource
    private SxfzzMapper sxfzzMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * v1 核心逻辑：���果 deptId 为空，从登录用户获取部门ID；
     * 如果 deptId == 100000，置 null（不过滤部门）
     */
    private void fillDeptId(SxfzzPageReqVO req) {
        if (StrUtil.isEmpty(req.getDeptId())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    req.setDeptId(user.getDeptId().toString());
                }
            } catch (Exception e) {
                // ignore
            }
        }
        if ("100000".equals(req.getDeptId())) {
            req.setDeptId(null);
        }
    }

    @Override
    public PageResult<SxfzzResVO> getSxfzzPage(SxfzzPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        List<SxfzzResVO> records = sxfzzMapper.selectLegacyList(pageReqVO);
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    @Override
    public SxfzzResVO getSxfzz(Long id) {
        return sxfzzMapper.selectLegacyById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createSxfzz(SxfzzSaveReqVO createReqVO) {
        SxfzzDO sxfzz = BeanUtils.toBean(createReqVO, SxfzzDO.class);
        sxfzzMapper.insert(sxfzz);
        // v1: batch insert cascade subtable (note: sxfzz does NOT set ghHkxxJym from jym)
        insertSwsxfdz(sxfzz.getHkxxId(), createReqVO.getSwsxfdzList());
        return sxfzz.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSxfzz(SxfzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        // v1: delete old qrsz, re-insert
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", updateReqVO.getHkxxId());
        insertSwsxfdz(updateReqVO.getHkxxId(), updateReqVO.getSwsxfdzList());
        // v1: update main table
        SxfzzDO updateObj = BeanUtils.toBean(updateReqVO, SxfzzDO.class);
        sxfzzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSxfzz(Long id) {
        validateExists(id);
        // v1: delete subtable first, then main
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", id);
        sxfzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSxfzzListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        String placeholders = String.join(",", ids.stream().map(i -> "?").toList());
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id in (" + placeholders + ")",
                ids.toArray());
        sxfzzMapper.deleteByIds(ids);
    }

    /**
     * v1: batch insert gh_hkxx_qrsz subtable for sxfzz
     * Note: v1 SxfzzServiceImpl does NOT set ghHkxxJym (unlike cbjzz/znjzz)
     */
    private void insertSwsxfdz(Long hkxxId, List<SxfzzSaveReqVO.SwsxfdzItem> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        String nickName = null;
        try {
            AdminUserDO user = userService.getUser(getLoginUserId());
            if (user != null) {
                nickName = user.getNickname();
            }
        } catch (Exception e) {
            // ignore
        }
        for (SxfzzSaveReqVO.SwsxfdzItem item : items) {
            jdbcTemplate.update(
                    "insert into gh_hkxx_qrsz (hkxx_id, gh_hkxx_jym, dzbj, qrrq, yhhdh, bz, create_by, create_time, update_by, update_time) " +
                            "values (?, ?, ?, ?, ?, ?, ?, sysdate(), ?, sysdate())",
                    hkxxId, item.getGhHkxxJym(), item.getDzbj(), item.getQrrq(), item.getYhhdh(), item.getBz(),
                    nickName, item.getUpdateBy()
            );
        }
    }

    private void validateExists(Long id) {
        if (sxfzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }
}
