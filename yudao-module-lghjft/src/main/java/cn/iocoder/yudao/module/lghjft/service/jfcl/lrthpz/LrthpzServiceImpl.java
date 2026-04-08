package cn.iocoder.yudao.module.lghjft.service.jfcl.lrthpz;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzImportVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.lrthpz.LrthpzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.lrthpz.LrthpzMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
@Slf4j
public class LrthpzServiceImpl implements LrthpzService {

    @Resource
    private LrthpzMapper lrthpzMapper;

    @Resource
    private AdminUserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createLrthpz(LrthpzSaveReqVO createReqVO) {
        LrthpzDO entity = BeanUtils.toBean(createReqVO, LrthpzDO.class);
        entity.setCreateBy(getLoginUserNickname());
        entity.setCreateTime(LocalDateTime.now());
        lrthpzMapper.insert(entity);
        return entity.getHkxxId();
    }

    /**
     * V1 update: only thbj, thrq, thyy, xgbj, hkxxidgl, bz, audit fields
     * All other fields are commented out in V1 XML updateLrthpz
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateLrthpz(LrthpzSaveReqVO updateReqVO) {
        validateLrthpzExists(updateReqVO.getHkxxId());
        // v1: restricted update - only certain fields
        LrthpzDO updateObj = new LrthpzDO();
        updateObj.setHkxxId(updateReqVO.getHkxxId());
        updateObj.setThbj(updateReqVO.getThbj());
        updateObj.setThrq(updateReqVO.getThrq());
        updateObj.setThyy(updateReqVO.getThyy());
        updateObj.setXgbj(updateReqVO.getXgbj());
        updateObj.setHkxxidgl(updateReqVO.getHkxxidgl());
        updateObj.setBz(updateReqVO.getBz());
        updateObj.setUpdateBy(getLoginUserNickname());
        updateObj.setUpdateTime(LocalDateTime.now());
        lrthpzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLrthpz(Long id) {
        validateLrthpzExists(id);
        lrthpzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLrthpzBatch(List<Long> ids) {
        lrthpzMapper.deleteByIds(ids);
    }

    @Override
    public LrthpzDO getLrthpz(Long id) {
        return lrthpzMapper.selectById(id);
    }

    @Override
    public PageResult<LrthpzDO> getLrthpzPage(LrthpzPageReqVO pageReqVO) {
        // v1 deptId filtering: if empty, use current user's deptId; if root(100000), clear
        if (StringUtils.isEmpty(pageReqVO.getDeptId())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    pageReqVO.setDeptId(user.getDeptId().toString());
                }
            } catch (Exception e) { /* ignore */ }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return lrthpzMapper.selectPage(pageReqVO);
    }

    private void validateLrthpzExists(Long id) {
        if (lrthpzMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }

    /**
     * V1 import logic:
     * 1. For each imported row, use hkxxId as bfid to look up gh_hkxx via gh_hkxx_yhbfmx join
     * 2. If found, update the row: set thbj='Y', xgbj='N', thrq, thyy, bz, updateBy, updateTime
     * 3. If not found, count as failure
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importLrthpz(List<LrthpzImportVO> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        String operUser = getLoginUserNickname();
        int successCount = 0;
        for (LrthpzImportVO importVO : list) {
            try {
                if (importVO.getHkxxId() == null) {
                    continue;
                }
                // v1: SELECT t.* FROM gh_hkxx t LEFT JOIN gh_hkxx_yhbfmx yh on t.HKXX_ID=yh.hkxx_id WHERE yh.bfid = #{hkxxId} and t.yxbj='Y'
                List<LrthpzDO> selectList = lrthpzMapper.selectByBfid(importVO.getHkxxId());
                if (selectList != null && !selectList.isEmpty()) {
                    LrthpzDO existing = selectList.get(0);
                    // v1: set thbj='Y', xgbj='N', thrq, thyy, bz
                    existing.setThbj("Y");
                    existing.setXgbj("N");
                    if (importVO.getThrq() != null) {
                        existing.setThrq(importVO.getThrq().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    }
                    existing.setThyy(importVO.getThyy());
                    existing.setBz(importVO.getBz());
                    existing.setUpdateBy(operUser);
                    existing.setUpdateTime(LocalDateTime.now());
                    lrthpzMapper.updateById(existing);
                    successCount++;
                }
            } catch (Exception e) {
                log.error("导入退回凭证失败: {}", e.getMessage(), e);
            }
        }
        return successCount;
    }

    private String getLoginUserNickname() {
        try {
            AdminUserDO user = userService.getUser(getLoginUserId());
            return user != null ? user.getNickname() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
