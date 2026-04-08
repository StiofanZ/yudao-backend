package cn.iocoder.yudao.module.lghjft.service.hbzz.cbjzz;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.cbjzz.CbjzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.cbjzz.CbjzzMapper;
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
public class CbjzzServiceImpl implements CbjzzService {

    @Resource
    private CbjzzMapper cbjzzMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * v1 核心逻辑：如果 deptId 为空，从登录用户获取部门ID；
     * 如果 deptId == 100000，置 null（不过滤部门）
     */
    private void fillDeptId(CbjzzPageReqVO req) {
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
    public PageResult<CbjzzResVO> getCbjzzPage(CbjzzPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        List<CbjzzResVO> records = cbjzzMapper.selectLegacyList(pageReqVO);
        // v1: database-level paging via startPage() — replicate with manual sub-list
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    @Override
    public List<CbjzzResVO> getCbjzzListCbj(CbjzzPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        return cbjzzMapper.selectLegacyListCbj(pageReqVO);
    }

    @Override
    public CbjzzResVO getCbjzz(Long id) {
        return cbjzzMapper.selectLegacyById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createCbjzz(CbjzzSaveReqVO createReqVO) {
        CbjzzDO cbjzz = BeanUtils.toBean(createReqVO, CbjzzDO.class);
        cbjzzMapper.insert(cbjzz);
        // v1: batch insert cascade subtable
        insertCbjQrsz(cbjzz.getHkxxId(), cbjzz.getJym(), createReqVO.getCbjQrszList());
        return cbjzz.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCbjzz(CbjzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        // v1: delete old qrsz, re-insert
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", updateReqVO.getHkxxId());
        CbjzzDO mainRecord = cbjzzMapper.selectById(updateReqVO.getHkxxId());
        insertCbjQrsz(updateReqVO.getHkxxId(), mainRecord != null ? mainRecord.getJym() : null,
                updateReqVO.getCbjQrszList());
        // v1: update main table
        CbjzzDO updateObj = BeanUtils.toBean(updateReqVO, CbjzzDO.class);
        cbjzzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCbjzz(Long id) {
        validateExists(id);
        // v1: delete subtable first, then main
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", id);
        cbjzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCbjzzListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // v1: delete subtable first, then main
        String placeholders = String.join(",", ids.stream().map(i -> "?").toList());
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id in (" + placeholders + ")",
                ids.toArray());
        cbjzzMapper.deleteByIds(ids);
    }

    /**
     * v1: batch insert gh_hkxx_qrsz subtable
     */
    private void insertCbjQrsz(Long hkxxId, String jym, List<CbjzzSaveReqVO.CbjQrszItem> items) {
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
        for (CbjzzSaveReqVO.CbjQrszItem item : items) {
            jdbcTemplate.update(
                    "insert into gh_hkxx_qrsz (hkxx_id, gh_hkxx_jym, qrrq, yhhdh, bz, create_by, create_time, update_by, update_time) " +
                            "values (?, ?, ?, ?, ?, ?, sysdate(), ?, sysdate())",
                    hkxxId, jym, item.getQrrq(), item.getYhhdh(), item.getBz(),
                    nickName, item.getUpdateBy()
            );
        }
    }

    private void validateExists(Long id) {
        if (cbjzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }
}
