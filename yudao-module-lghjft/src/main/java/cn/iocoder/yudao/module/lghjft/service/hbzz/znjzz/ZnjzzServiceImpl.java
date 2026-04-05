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

    private void fillZhFromDept(ZnjzzPageReqVO req) {
        if (StrUtil.isEmpty(req.getZh())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    Map<String, Object> dept = jdbcTemplate.queryForMap(
                            "SELECT yhzh FROM sys_dept WHERE dept_id = ?",
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
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    @Override
    public ZnjzzDO getZnjzz(Long id) {
        return znjzzMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createZnjzz(ZnjzzSaveReqVO createReqVO) {
        ZnjzzDO znjzz = BeanUtils.toBean(createReqVO, ZnjzzDO.class);
        znjzzMapper.insert(znjzz);
        return znjzz.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZnjzz(ZnjzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        ZnjzzDO updateObj = BeanUtils.toBean(updateReqVO, ZnjzzDO.class);
        znjzzMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZnjzz(Long id) {
        validateExists(id);
        znjzzMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZnjzzListByIds(List<Long> ids) {
        znjzzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (znjzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }
}
