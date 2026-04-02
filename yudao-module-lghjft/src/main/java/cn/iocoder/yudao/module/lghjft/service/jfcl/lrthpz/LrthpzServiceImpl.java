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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class LrthpzServiceImpl implements LrthpzService {

    @Resource
    private LrthpzMapper lrthpzMapper;

    @Resource
    private AdminUserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createLrthpz(LrthpzSaveReqVO createReqVO) {
        LrthpzDO entity = BeanUtils.toBean(createReqVO, LrthpzDO.class);
        lrthpzMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateLrthpz(LrthpzSaveReqVO updateReqVO) {
        validateLrthpzExists(updateReqVO.getHkxxId());
        LrthpzDO updateObj = BeanUtils.toBean(updateReqVO, LrthpzDO.class);
        lrthpzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLrthpz(Long id) {
        validateLrthpzExists(id);
        lrthpzMapper.deleteById(id);
    }

    @Override
    public LrthpzDO getLrthpz(Long id) {
        return lrthpzMapper.selectById(id);
    }

    @Override
    public PageResult<LrthpzDO> getLrthpzPage(LrthpzPageReqVO pageReqVO) {
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
        return lrthpzMapper.selectPage(pageReqVO);
    }

    private void validateLrthpzExists(Long id) {
        if (lrthpzMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importLrthpz(List<LrthpzImportVO> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (LrthpzImportVO importVO : list) {
            LrthpzDO entity = BeanUtils.toBean(importVO, LrthpzDO.class);
            lrthpzMapper.insert(entity);
            count++;
        }
        return count;
    }
}
