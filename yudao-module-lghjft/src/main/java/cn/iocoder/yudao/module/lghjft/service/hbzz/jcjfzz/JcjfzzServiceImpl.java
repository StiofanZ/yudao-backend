package cn.iocoder.yudao.module.lghjft.service.hbzz.jcjfzz;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.JcjfzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jcjfzz.JcjfzzMapper;
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
public class JcjfzzServiceImpl implements JcjfzzService {

    @Resource
    private JcjfzzMapper jcjfzzMapper;

    @Resource
    private AdminUserService userService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * v1 核心逻辑：如果 deptId 为空，从登录用户获取部门ID；
     * 如果 deptId == "100000"，则置 null（查看全部）
     */
    private void fillDeptId(JcjfzzPageReqVO req) {
        if (StrUtil.isEmpty(req.getDeptId())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    req.setDeptId(user.getDeptId().toString());
                }
            } catch (Exception e) {
                // 获取失败不阻断
            }
        }
        if ("100000".equals(req.getDeptId())) {
            req.setDeptId(null);
        }
    }

    @Override
    public PageResult<JcjfzzResVO> getJcjfzzPage(JcjfzzPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        List<JcjfzzResVO> records = jcjfzzMapper.selectLegacyList(pageReqVO);
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    @Override
    public JcjfzzResVO getJcjfzz(Long id) {
        return jcjfzzMapper.selectLegacyById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createJcjfzz(JcjfzzSaveReqVO createReqVO) {
        JcjfzzDO jcjfzz = BeanUtils.toBean(createReqVO, JcjfzzDO.class);
        jcjfzzMapper.insert(jcjfzz);
        // v1: 批量插入子表 gh_hkxx_qrsz
        insertJcjfdz(jcjfzz.getHkxxId(), jcjfzz.getJym(), createReqVO.getJcjfdzList());
        return jcjfzz.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJcjfzz(JcjfzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        // v1: 先删除旧子表记录，再批量插入新子表记录
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", updateReqVO.getHkxxId());
        JcjfzzDO mainRecord = jcjfzzMapper.selectById(updateReqVO.getHkxxId());
        insertJcjfdz(updateReqVO.getHkxxId(), mainRecord != null ? mainRecord.getJym() : null,
                updateReqVO.getJcjfdzList());
        // v1: 更新主表
        JcjfzzDO updateObj = BeanUtils.toBean(updateReqVO, JcjfzzDO.class);
        jcjfzzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJcjfzz(Long id) {
        validateExists(id);
        // v1: 先删子表再删主表
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id = ?", id);
        jcjfzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJcjfzzListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // v1: 先删子表再删主表
        String placeholders = String.join(",", ids.stream().map(i -> "?").toList());
        jdbcTemplate.update("delete from gh_hkxx_qrsz where hkxx_id in (" + placeholders + ")",
                ids.toArray());
        jcjfzzMapper.deleteByIds(ids);
    }

    /**
     * v1: 批量插入 gh_hkxx_qrsz 子表
     */
    private void insertJcjfdz(Long hkxxId, String jym, List<JcjfzzSaveReqVO.JcjfdzItem> items) {
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
        for (JcjfzzSaveReqVO.JcjfdzItem item : items) {
            jdbcTemplate.update(
                    "insert into gh_hkxx_qrsz (hkxx_id, gh_hkxx_jym, dzbj, qrrq, yhhdh, bz, create_by, create_time, update_by, update_time) " +
                            "values (?, ?, ?, ?, ?, ?, ?, sysdate(), ?, sysdate())",
                    hkxxId, jym, item.getDzbj(), item.getQrrq(), item.getYhhdh(), item.getBz(),
                    nickName, item.getUpdateBy()
            );
        }
    }

    private void validateExists(Long id) {
        if (jcjfzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }
}
