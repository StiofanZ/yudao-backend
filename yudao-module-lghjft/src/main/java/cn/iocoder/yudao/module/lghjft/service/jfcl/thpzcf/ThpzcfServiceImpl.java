package cn.iocoder.yudao.module.lghjft.service.jfcl.thpzcf;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.thpzcf.ThpzcfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.thpzcf.ThpzcfMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class ThpzcfServiceImpl implements ThpzcfService {

    @Resource
    private ThpzcfMapper thpzcfMapper;

    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createThpzcf(ThpzcfSaveReqVO createReqVO) {
        ThpzcfDO entity = BeanUtils.toBean(createReqVO, ThpzcfDO.class);
        entity.setCreateBy(getLoginUserNickname());
        entity.setCreateTime(LocalDateTime.now());
        thpzcfMapper.insert(entity);
        return entity.getHkxxId();
    }

    /**
     * V1 update: only xzh, xhm, xhh + thbj, thyy, xgbj, bz, dept_id, audit fields
     * Most fields are commented out in V1 XML updateThpzcf
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateThpzcf(ThpzcfSaveReqVO updateReqVO) {
        validateThpzcfExists(updateReqVO.getHkxxId());
        // v1: restricted update
        ThpzcfDO updateObj = new ThpzcfDO();
        updateObj.setHkxxId(updateReqVO.getHkxxId());
        // v1: only xzh, xhm, xhh (new account details)
        updateObj.setXzh(updateReqVO.getXzh());
        updateObj.setXhm(updateReqVO.getXhm());
        updateObj.setXhh(updateReqVO.getXhh());
        // v1 also allows: thbj, thyy, xgbj, bz, dept_id
        updateObj.setThbj(updateReqVO.getThbj());
        updateObj.setThrq(updateReqVO.getThrq());
        updateObj.setThyy(updateReqVO.getThyy());
        updateObj.setXgbj(updateReqVO.getXgbj());
        updateObj.setBz(updateReqVO.getBz());
        updateObj.setDeptId(updateReqVO.getDeptId());
        updateObj.setUpdateBy(getLoginUserNickname());
        updateObj.setUpdateTime(LocalDateTime.now());
        thpzcfMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteThpzcf(Long id) {
        validateThpzcfExists(id);
        thpzcfMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteThpzcfBatch(List<Long> ids) {
        thpzcfMapper.deleteByIds(ids);
    }

    @Override
    public ThpzcfDO getThpzcf(Long id) {
        return thpzcfMapper.selectById(id);
    }

    @Override
    public PageResult<ThpzcfDO> getThpzcfPage(ThpzcfPageReqVO pageReqVO) {
        // v1 deptId filtering
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
        return thpzcfMapper.selectPage(pageReqVO);
    }

    private void validateThpzcfExists(Long id) {
        if (thpzcfMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
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
