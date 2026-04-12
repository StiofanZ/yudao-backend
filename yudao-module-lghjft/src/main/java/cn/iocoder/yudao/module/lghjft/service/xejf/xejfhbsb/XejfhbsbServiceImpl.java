package cn.iocoder.yudao.module.lghjft.service.xejf.xejfhbsb;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsb.vo.XejfhbsbPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsb.vo.XejfhbsbSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfhbsb.XejfhbsbDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejfhbsb.XejfhbsbMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXXXEJFJCDZ_NOT_EXISTS;

@Service
@Validated
public class XejfhbsbServiceImpl implements XejfhbsbService {

    private static final DateTimeFormatter XGSJ_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private XejfhbsbMapper xejfhbsbMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Resource
    private AdminUserService adminUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createXejfhbsb(XejfhbsbSaveReqVO createReqVO) {
        XejfhbsbDO entity = BeanUtils.toBean(createReqVO, XejfhbsbDO.class);
        xejfhbsbMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateXejfhbsb(XejfhbsbSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        XejfhbsbDO updateObj = new XejfhbsbDO();
        updateObj.setHkxxId(updateReqVO.getHkxxId());
        updateObj.setXzh(updateReqVO.getXzh());
        updateObj.setXhm(updateReqVO.getXhm());
        updateObj.setXhh(updateReqVO.getXhh());
        updateObj.setXgbj(updateReqVO.getXgbj());
        updateObj.setBz(updateReqVO.getBz());
        AdminUserDO user = adminUserService.getUser(getLoginUserId());
        if (user != null) {
            updateObj.setXgr(user.getNickname());
        }
        updateObj.setXgsj(LocalDateTime.now().format(XGSJ_FORMATTER));
        xejfhbsbMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteXejfhbsb(Long id) {
        validateExists(id);
        xejfhbsbMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteXejfhbsbListByIds(List<Long> ids) {
        xejfhbsbMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (xejfhbsbMapper.selectById(id) == null) {
            throw exception(HKXXXEJFJCDZ_NOT_EXISTS);
        }
    }

    @Override
    public XejfhbsbDO getXejfhbsb(Long id) {
        return xejfhbsbMapper.selectById(id);
    }

    @Override
    public PageResult<XejfhbsbDO> getXejfhbsbPage(XejfhbsbPageReqVO pageReqVO) {
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return xejfhbsbMapper.selectPage(pageReqVO);
    }
}
