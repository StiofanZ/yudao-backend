package cn.iocoder.yudao.module.lghjft.service.qx.dlzh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhResetPasswordReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.dlzh.GhQxDlzhMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

@Service
@Validated
public class GhQxDlzhServiceImpl implements GhQxDlzhService {

    @Resource
    private GhQxDlzhMapper ghQxDlzhMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminUserServiceImpl adminUserService;

    @Override
    public Long createDlzh(DlzhSaveReqVO createReqVO) {
        if (StringUtils.isBlank(createReqVO.getPassword())) {
            throw exception(DLZH_PASSWORD_REQUIRED);
        }
        validateUnique(null, createReqVO);
        GhQxDlzhDO dlzh = BeanUtils.toBean(createReqVO, GhQxDlzhDO.class);
        dlzh.setPassword(passwordEncoder.encode(createReqVO.getPassword()));
        if (dlzh.getStatus() == null) {
            dlzh.setStatus(0);
        }
        ghQxDlzhMapper.insert(dlzh);
        return dlzh.getId();
    }

    @Override
    public void updateDlzh(DlzhSaveReqVO updateReqVO) {
        validateDlzhExists(updateReqVO.getId());
        validateUnique(updateReqVO.getId(), updateReqVO);
        GhQxDlzhDO updateObj = BeanUtils.toBean(updateReqVO, GhQxDlzhDO.class);
        if (StringUtils.isNotBlank(updateReqVO.getPassword())) {
            updateObj.setPassword(passwordEncoder.encode(updateReqVO.getPassword()));
        } else {
            updateObj.setPassword(null);
        }
        ghQxDlzhMapper.updateById(updateObj);
    }

    @Override
    public void resetPassword(DlzhResetPasswordReqVO reqVO) {
        validateDlzhExists(reqVO.getId());
        ghQxDlzhMapper.updateById(GhQxDlzhDO.builder()
                .id(reqVO.getId())
                .password(passwordEncoder.encode(reqVO.getPassword()))
                .build());
    }

    @Override
    public void deleteDlzh(Long id) {
        validateDlzhExists(id);
        ghQxDlzhMapper.deleteById(id);
    }

    @Override
    public void deleteDlzhListByIds(List<Long> ids) {
        ghQxDlzhMapper.deleteByIds(ids);
    }

    @Override
    public GhQxDlzhDO getDlzh(Long id) {
        return ghQxDlzhMapper.selectById(id);
    }

    @Override
    public PageResult<GhQxDlzhDO> getDlzhPage(DlzhReqVO pageReqVO) {
        return ghQxDlzhMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GhQxDlzhDO> getDlzhList(java.util.Collection<Long> ids) {
        return ghQxDlzhMapper.selectBatchIds(ids);
    }

    private void validateDlzhExists(Long id) {
        if (id == null || ghQxDlzhMapper.selectById(id) == null) {
            throw exception(DLZH_NOT_EXISTS);
        }
    }

    private void validateUnique(Long id, DlzhSaveReqVO reqVO) {
        if (StringUtils.isNotBlank(reqVO.getYhzh())) {
            GhQxDlzhDO exist = ghQxDlzhMapper.selectByYhzh(reqVO.getYhzh());
            if (exist != null && !Objects.equals(exist.getId(), id)) {
                throw exception(DLZH_YHZH_EXISTS);
            }
            AdminUserDO userDO = adminUserService.getUserByUsername(reqVO.getYhzh());
            if(userDO!=null){
                throw exception(DLZH_YHZH_EXISTS);
            }
        }
        if (StringUtils.isNotBlank(reqVO.getLxdh())) {
            GhQxDlzhDO exist = ghQxDlzhMapper.selectByLxdh(reqVO.getLxdh());
            if (exist != null && !Objects.equals(exist.getId(), id)) {
                throw exception(DLZH_LXDH_EXISTS);
            }
        }
        if (StringUtils.isNotBlank(reqVO.getYhyx())) {
            GhQxDlzhDO exist = ghQxDlzhMapper.selectByYhyx(reqVO.getYhyx());
            if (exist != null && !Objects.equals(exist.getId(), id)) {
                throw exception(DLZH_YHYX_EXISTS);
            }
        }
        if (StringUtils.isNotBlank(reqVO.getShxydm())) {
            GhQxDlzhDO exist = ghQxDlzhMapper.selectByShxydm(reqVO.getShxydm());
            if (exist != null && !Objects.equals(exist.getId(), id)) {
                throw exception(DLZH_SHXYDM_EXISTS);
            }
        }
    }

}
