package cn.iocoder.yudao.module.lghjft.service.sjwh.ghjfzz;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ghjfzz.vo.GhjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ghjfzz.vo.GhjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ghjfzz.vo.GhjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.HkxxQrszDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.ghjfzz.GhjfzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jcjfzz.HkxxQrszMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.ghjfzz.GhjfzzMapper;
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
public class GhjfzzServiceImpl implements GhjfzzService {

    @Resource
    private GhjfzzMapper ghjfzzMapper;

    @Resource
    private HkxxQrszMapper hkxxQrszMapper;

    @Resource
    private AdminUserService userService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * v1 核心逻辑：如果 zh 为空，从 sys_dept 读取登录用户部门的银行账号；
     * deptId 始终置 null（按银行账号过滤，不按部门过滤）
     */
    private void fillZhFromDept(GhjfzzPageReqVO req) {
        if (StrUtil.isEmpty(req.getZh())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    Map<String, Object> dept = jdbcTemplate.queryForMap(
                            "SELECT yhzh, yhzh1, yhzh2, yhzh3 FROM sys_dept WHERE dept_id = ?",
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
    public PageResult<GhjfzzResVO> getGhjfzzPage(GhjfzzPageReqVO pageReqVO) {
        fillZhFromDept(pageReqVO);
        List<GhjfzzResVO> records = ghjfzzMapper.selectLegacyList(pageReqVO);
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    @Override
    public GhjfzzResVO getGhjfzz(Long id) {
        return ghjfzzMapper.selectLegacyById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createGhjfzz(GhjfzzSaveReqVO createReqVO) {
        GhjfzzDO ghjfzz = BeanUtils.toBean(createReqVO, GhjfzzDO.class);
        ghjfzzMapper.insert(ghjfzz);
        return ghjfzz.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGhjfzz(GhjfzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        GhjfzzDO mainRecord = ghjfzzMapper.selectById(updateReqVO.getHkxxId());
        HkxxQrszDO qrsz = hkxxQrszMapper.selectLatestByHkxxId(Math.toIntExact(updateReqVO.getHkxxId()));
        if (qrsz == null) {
            qrsz = new HkxxQrszDO();
            qrsz.setHkxxId(Math.toIntExact(updateReqVO.getHkxxId()));
            qrsz.setGhHkxxJym(mainRecord.getJym());
            qrsz.setDzbj(updateReqVO.getDzbj());
            qrsz.setQrrq(updateReqVO.getQrrq());
            qrsz.setYhhdh(updateReqVO.getYhhdh());
            qrsz.setBz(updateReqVO.getBz());
            hkxxQrszMapper.insert(qrsz);
            return;
        }
        qrsz.setDzbj(updateReqVO.getDzbj() != null ? updateReqVO.getDzbj() : qrsz.getDzbj());
        qrsz.setQrrq(updateReqVO.getQrrq());
        qrsz.setYhhdh(updateReqVO.getYhhdh());
        qrsz.setBz(updateReqVO.getBz());
        hkxxQrszMapper.updateById(qrsz);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhjfzz(Long id) {
        validateExists(id);
        ghjfzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhjfzzListByIds(List<Long> ids) {
        ghjfzzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (ghjfzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }
}
