package cn.iocoder.yudao.module.lghjft.controller.app.qx.dlzh;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhResetPasswordReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

@Tag(name = "用户 App - 登录账号")
@RestController
@RequestMapping("/lghjft/app/qx/dlzh")
@Validated
public class DlzhAppController {

    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @PutMapping("/update")
    @Operation(summary = "更新登录账号")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> updateDlzh(@Valid @RequestBody DlzhSaveReqVO updateReqVO) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        updateReqVO.setId(loginUserId);
        validateUnique(loginUserId, updateReqVO);
        AdminUserDO update = fromSaveReqVO(updateReqVO);
        if (StringUtils.isNotBlank(updateReqVO.getPassword())) {
            update.setPassword(passwordEncoder.encode(updateReqVO.getPassword()));
        } else {
            update.setPassword(null);
        }
        adminUserMapper.updateById(update);
        return success(true);
    }

    @PutMapping("/reset-password")
    @Operation(summary = "重置登录账号密码")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> resetPassword(@Valid @RequestBody DlzhResetPasswordReqVO reqVO) {
        reqVO.setId(SecurityFrameworkUtils.getLoginUserId());
        AdminUserDO update = new AdminUserDO();
        update.setId(reqVO.getId());
        update.setPassword(passwordEncoder.encode(reqVO.getPassword()));
        adminUserMapper.updateById(update);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得登录账号")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<DlzhResVO> getDlzh() {
        return success(convertToResVO(adminUserMapper.selectById(SecurityFrameworkUtils.getLoginUserId())));
    }

    // ---------------------------------------------------------------- 私有方法

    private void validateUnique(Long id, DlzhSaveReqVO reqVO) {
        if (StringUtils.isNotBlank(reqVO.getYhzh())) {
            AdminUserDO exist = adminUserMapper.selectByUsername(reqVO.getYhzh());
            if (exist != null && !Objects.equals(exist.getId(), id)) {
                throw exception(DLZH_YHZH_EXISTS);
            }
        }
        if (StringUtils.isNotBlank(reqVO.getLxdh())) {
            AdminUserDO exist = adminUserMapper.selectByMobile(reqVO.getLxdh());
            if (exist != null && !Objects.equals(exist.getId(), id)) {
                throw exception(DLZH_LXDH_EXISTS);
            }
        }
        if (StringUtils.isNotBlank(reqVO.getYhyx())) {
            AdminUserDO exist = adminUserMapper.selectByEmail(reqVO.getYhyx());
            if (exist != null && !Objects.equals(exist.getId(), id)) {
                throw exception(DLZH_YHYX_EXISTS);
            }
        }
        if (StringUtils.isNotBlank(reqVO.getShxydm())) {
            AdminUserDO exist = adminUserMapper.selectByShxydm(reqVO.getShxydm());
            if (exist != null && !Objects.equals(exist.getId(), id)) {
                throw exception(DLZH_SHXYDM_EXISTS);
            }
        }
    }

    private AdminUserDO fromSaveReqVO(DlzhSaveReqVO vo) {
        AdminUserDO user = new AdminUserDO();
        user.setId(vo.getId());
        user.setUsername(vo.getYhzh());
        user.setNickname(vo.getYhxm());
        user.setRemark(vo.getYhbz());
        user.setMobile(vo.getLxdh());
        user.setEmail(vo.getYhyx());
        user.setShxydm(vo.getShxydm());
        user.setSex(vo.getYhxb());
        user.setAvatar(vo.getTxdz());
        user.setStatus(vo.getStatus());
        return user;
    }

    private DlzhResVO convertToResVO(AdminUserDO user) {
        if (user == null) {
            return null;
        }
        DlzhResVO vo = new DlzhResVO();
        vo.setId(user.getId());
        vo.setYhzh(user.getUsername());
        vo.setYhxm(user.getNickname());
        vo.setYhbz(user.getRemark());
        vo.setLxdh(user.getMobile());
        vo.setYhyx(user.getEmail());
        vo.setShxydm(user.getShxydm());
        vo.setYhxb(user.getSex());
        vo.setTxdz(user.getAvatar());
        vo.setStatus(user.getStatus());
        vo.setLoginIp(user.getLoginIp());
        vo.setLoginDate(user.getLoginDate());
        vo.setCreator(user.getCreator());
        vo.setCreateTime(user.getCreateTime());
        vo.setUpdater(user.getUpdater());
        vo.setUpdateTime(user.getUpdateTime());
        return vo;
    }
}
