package cn.iocoder.yudao.module.lghjft.service.jfcl.tsjfcl;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.TsjfclDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.tsjfcl.TsjfclMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class TsjfclServiceImpl implements TsjfclService {

    @Resource
    private TsjfclMapper tsjfclMapper;

    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTsjfcl(TsjfclSaveReqVO createReqVO) {
        TsjfclDO entity = BeanUtils.toBean(createReqVO, TsjfclDO.class);
        tsjfclMapper.insert(entity);
        return entity.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTsjfcl(TsjfclSaveReqVO updateReqVO) {
        validateTsjfclExists(updateReqVO.getGhjfId());
        TsjfclDO updateObj = BeanUtils.toBean(updateReqVO, TsjfclDO.class);
        tsjfclMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTsjfcl(Long id) {
        validateTsjfclExists(id);
        tsjfclMapper.deleteById(id);
    }

    @Override
    public TsjfclDO getTsjfcl(Long id) {
        return tsjfclMapper.selectById(id);
    }

    @Override
    public PageResult<TsjfclDO> getTsjfclPage(TsjfclPageReqVO pageReqVO) {
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
        return tsjfclMapper.selectPage(pageReqVO);
    }

    private void validateTsjfclExists(Long id) {
        if (tsjfclMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}
