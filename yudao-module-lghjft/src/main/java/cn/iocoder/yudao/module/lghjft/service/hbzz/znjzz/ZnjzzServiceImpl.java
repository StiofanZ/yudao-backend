package cn.iocoder.yudao.module.lghjft.service.hbzz.znjzz;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.znjzz.ZnjzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.znjzz.ZnjzzMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class ZnjzzServiceImpl implements ZnjzzService {

    @Resource
    private ZnjzzMapper znjzzMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * v1 核心逻辑：如果 zh 为空，从 sys_dept 读取登录用户部门���银行账号；
     * deptId 始终置 null（按银行账号过滤，不按部门过滤）
     */
    private void fillZhFromDept(ZnjzzPageReqVO req) {
        if (StrUtil.isEmpty(req.getZh())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    Map<String, Object> dept = jdbcTemplate.queryForMap(
                            "select yhzh from sys_dept where dept_id = ?",
                            user.getDeptId());
                    req.setZh(dept.get("yhzh") != null ? dept.get("yhzh").toString() : null);
                }
            } catch (Exception e) {
                // ignore
            }
        }
        if (req.getDeptId() != null) {
            req.setDeptId(null);
        }
    }

    @Override
    public PageResult<ZnjzzResVO> getZnjzzPage(ZnjzzPageReqVO pageReqVO) {
        fillZhFromDept(pageReqVO);
        List<ZnjzzResVO> records = znjzzMapper.selectLegacyList(pageReqVO);
        // PAGE_SIZE_NONE (-1) means return all records without pagination
        if (pageReqVO.getPageSize() == null || pageReqVO.getPageSize() < 0) {
            return new PageResult<>(records, (long) records.size());
        }
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    @Override
    public ZnjzzResVO getZnjzz(Long id) {
        return znjzzMapper.selectLegacyById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createZnjzz(ZnjzzSaveReqVO createReqVO) {
        ZnjzzDO znjzz = BeanUtils.toBean(createReqVO, ZnjzzDO.class);
        znjzzMapper.insert(znjzz);
        // v1: batch insert cascade subtable
        insertGhjfQrsz(znjzz.getHkxxId(), znjzz.getJym(), createReqVO.getGhjfQrszList());
        return znjzz.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZnjzz(ZnjzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        // v1: delete old qrsz, re-insert
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", updateReqVO.getHkxxId());
        ZnjzzDO mainRecord = znjzzMapper.selectById(updateReqVO.getHkxxId());
        insertGhjfQrsz(updateReqVO.getHkxxId(), mainRecord != null ? mainRecord.getJym() : null,
                updateReqVO.getGhjfQrszList());
        // v1: update main table
        ZnjzzDO updateObj = BeanUtils.toBean(updateReqVO, ZnjzzDO.class);
        znjzzMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZnjzz(Long id) {
        validateExists(id);
        // v1: delete subtable first, then main
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", id);
        znjzzMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZnjzzListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        String placeholders = String.join(",", ids.stream().map(i -> "?").toList());
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id in (" + placeholders + ")",
                ids.toArray());
        znjzzMapper.deleteByIds(ids);
    }

    /**
     * v1: batch insert gh_hkxx_qrsz subtable
     */
    private void insertGhjfQrsz(Long hkxxId, String jym, List<ZnjzzSaveReqVO.GhjfQrszItem> items) {
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
        for (ZnjzzSaveReqVO.GhjfQrszItem item : items) {
            jdbcTemplate.update(
                    "insert into gh_hkxx_qrsz (hkxx_id, gh_hkxx_jym, qrrq, yhhdh, bz, create_by, create_time, update_by, update_time) " +
                            "values (?, ?, ?, ?, ?, ?, sysdate(), ?, sysdate())",
                    hkxxId, jym, item.getQrrq(), item.getYhhdh(), item.getBz(),
                    nickName, item.getUpdateBy()
            );
        }
    }

    private void validateExists(Long id) {
        if (znjzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }
}
