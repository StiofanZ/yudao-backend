package cn.iocoder.yudao.module.lghjft.service.xejf.xejfhbsbyxg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo.XejfhbsbyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo.XejfhbsbyxgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfhbsbyxg.XejfhbsbyxgDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejfhbsbyxg.XejfhbsbyxgMapper;
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
public class XejfhbsbyxgServiceImpl implements XejfhbsbyxgService {

    private static final DateTimeFormatter XGSJ_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private XejfhbsbyxgMapper xejfhbsbyxgMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Resource
    private AdminUserService adminUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createXejfhbsbyxg(XejfhbsbyxgSaveReqVO createReqVO) {
        XejfhbsbyxgDO entity = BeanUtils.toBean(createReqVO, XejfhbsbyxgDO.class);
        xejfhbsbyxgMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateXejfhbsbyxg(XejfhbsbyxgSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        XejfhbsbyxgDO updateObj = new XejfhbsbyxgDO();
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
        xejfhbsbyxgMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteXejfhbsbyxg(Long id) {
        validateExists(id);
        xejfhbsbyxgMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteXejfhbsbyxgListByIds(List<Long> ids) {
        xejfhbsbyxgMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (xejfhbsbyxgMapper.selectById(id) == null) {
            throw exception(HKXXXEJFJCDZ_NOT_EXISTS);
        }
    }

    @Override
    public XejfhbsbyxgDO getXejfhbsbyxg(Long id) {
        return xejfhbsbyxgMapper.selectById(id);
    }

    @Override
    public PageResult<XejfhbsbyxgDO> getXejfhbsbyxgPage(XejfhbsbyxgPageReqVO pageReqVO) {
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return xejfhbsbyxgMapper.selectPage(pageReqVO);
    }
}
