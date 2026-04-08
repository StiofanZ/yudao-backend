package cn.iocoder.yudao.module.lghjft.service.hbzz.fjcjfzz;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo.FjcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo.FjcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo.FjcjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.fjcjfzz.FjcjfzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.fjcjfzz.FjcjfzzMapper;
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
public class FjcjfzzServiceImpl implements FjcjfzzService {

    @Resource
    private FjcjfzzMapper fjcjfzzMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * v1 逻辑：如果 zh 为空，从 sys_dept 读取登录用户部门的银行账号；
     * deptId 始终置 null（按银行账号过滤，不按部门过滤）
     */
    private void fillZhFromDept(FjcjfzzPageReqVO req) {
        if (StrUtil.isEmpty(req.getZh())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    Map<String, Object> dept = jdbcTemplate.queryForMap(
                            "select yhzh, yhzh1, yhzh2, yhzh3 from sys_dept where dept_id = ?",
                            user.getDeptId());
                    req.setZh(dept.get("yhzh") != null ? dept.get("yhzh").toString() : null);
                    req.setZh1(dept.get("yhzh1") != null ? dept.get("yhzh1").toString() : null);
                    req.setZh2(dept.get("yhzh2") != null ? dept.get("yhzh2").toString() : null);
                    req.setZh3(dept.get("yhzh3") != null ? dept.get("yhzh3").toString() : null);
                }
            } catch (Exception e) {
                // 获取失败不阻断
            }
        }
        // v1: deptId 始终置 null
        if (req.getDeptId() != null) {
            req.setDeptId(null);
        }
    }

    @Override
    public PageResult<FjcjfzzResVO> getFjcjfzzPage(FjcjfzzPageReqVO pageReqVO) {
        fillZhFromDept(pageReqVO);
        List<FjcjfzzResVO> records = fjcjfzzMapper.selectLegacyList(pageReqVO);
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    @Override
    public FjcjfzzResVO getFjcjfzz(Long id) {
        return fjcjfzzMapper.selectLegacyById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createFjcjfzz(FjcjfzzSaveReqVO createReqVO) {
        FjcjfzzDO fjcjfzz = BeanUtils.toBean(createReqVO, FjcjfzzDO.class);
        fjcjfzzMapper.insert(fjcjfzz);
        // v1: 批量插入子表 gh_hkxx_qrsz
        insertCbjQrsz(fjcjfzz.getHkxxId(), fjcjfzz.getJym(), createReqVO.getCbjQrszList());
        return fjcjfzz.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateFjcjfzz(FjcjfzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        // v1: 先删除旧子表记录，再批量插入新子表记录
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", updateReqVO.getHkxxId());
        FjcjfzzDO mainRecord = fjcjfzzMapper.selectById(updateReqVO.getHkxxId());
        insertCbjQrsz(updateReqVO.getHkxxId(), mainRecord != null ? mainRecord.getJym() : null,
                updateReqVO.getCbjQrszList());
        // v1: 更新主表
        FjcjfzzDO updateObj = BeanUtils.toBean(updateReqVO, FjcjfzzDO.class);
        fjcjfzzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFjcjfzz(Long id) {
        validateExists(id);
        // v1: 先删子表再删主表
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", id);
        fjcjfzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFjcjfzzListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // v1: 先删子表再删主表
        String placeholders = String.join(",", ids.stream().map(i -> "?").toList());
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id in (" + placeholders + ")",
                ids.toArray());
        fjcjfzzMapper.deleteByIds(ids);
    }

    /**
     * v1: 批量插入 gh_hkxx_qrsz 子表
     */
    private void insertCbjQrsz(Long hkxxId, String jym, List<FjcjfzzSaveReqVO.CbjQrszItem> items) {
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
        for (FjcjfzzSaveReqVO.CbjQrszItem item : items) {
            jdbcTemplate.update(
                    "insert into gh_hkxx_qrsz (hkxx_id, gh_hkxx_jym, qrrq, yhhdh, bz, create_by, create_time, update_by, update_time) " +
                            "values (?, ?, ?, ?, ?, ?, sysdate(), ?, sysdate())",
                    hkxxId, jym, item.getQrrq(), item.getYhhdh(), item.getBz(),
                    nickName, item.getUpdateBy()
            );
        }
    }

    private void validateExists(Long id) {
        if (fjcjfzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }
}
