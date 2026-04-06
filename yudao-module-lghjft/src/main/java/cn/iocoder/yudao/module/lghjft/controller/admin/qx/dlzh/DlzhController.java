package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhReqVO;
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

import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

@Tag(name = "管理后台 - 登录账号")
@RestController
@RequestMapping("/lghjft/qx/dlzh")
@Validated
public class DlzhController {

    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    @Operation(summary = "创建登录账号")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:create')")
    public CommonResult<Long> createDlzh(@Valid @RequestBody DlzhSaveReqVO createReqVO) {
        if (StringUtils.isBlank(createReqVO.getPassword())) {
            throw exception(DLZH_PASSWORD_REQUIRED);
        }
        validateUnique(null, createReqVO);
        AdminUserDO user = fromSaveReqVO(createReqVO);
        user.setPassword(passwordEncoder.encode(createReqVO.getPassword()));
        if (user.getStatus() == null) {
            user.setStatus(0);
        }
        adminUserMapper.insert(user);
        return success(user.getId());
    }

    @PutMapping("/update")
    @Operation(summary = "更新登录账号")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:update')")
    public CommonResult<Boolean> updateDlzh(@Valid @RequestBody DlzhSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        validateUnique(updateReqVO.getId(), updateReqVO);
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
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:reset-password')")
    public CommonResult<Boolean> resetPassword(@Valid @RequestBody DlzhResetPasswordReqVO reqVO) {
        validateExists(reqVO.getId());
        AdminUserDO update = new AdminUserDO();
        update.setId(reqVO.getId());
        update.setPassword(passwordEncoder.encode(reqVO.getPassword()));
        adminUserMapper.updateById(update);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除登录账号")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:delete')")
    public CommonResult<Boolean> deleteDlzh(@RequestParam("id") Long id) {
        validateExists(id);
        adminUserMapper.deleteById(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除登录账号")
    @Parameter(name = "ids", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:delete')")
    public CommonResult<Boolean> deleteDlzhList(@RequestParam("ids") List<Long> ids) {
        adminUserMapper.deleteByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得登录账号")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:query')")
    public CommonResult<DlzhResVO> getDlzh(@RequestParam("id") Long id) {
        return success(convertToResVO(adminUserMapper.selectById(id)));
    }

    @GetMapping("/page")
    @Operation(summary = "获得登录账号分页")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:query')")
    public CommonResult<PageResult<DlzhResVO>> getDlzhPage(@Valid DlzhReqVO pageReqVO) {
        LambdaQueryWrapperX<AdminUserDO> wrapper = new LambdaQueryWrapperX<AdminUserDO>()
                .eqIfPresent(AdminUserDO::getUsername, pageReqVO.getYhzh())
                .eqIfPresent(AdminUserDO::getNickname, pageReqVO.getYhxm())
                .eqIfPresent(AdminUserDO::getMobile, pageReqVO.getLxdh())
                .eqIfPresent(AdminUserDO::getEmail, pageReqVO.getYhyx())
                .eqIfPresent(AdminUserDO::getShxydm, pageReqVO.getShxydm())
                .eqIfPresent(AdminUserDO::getStatus, pageReqVO.getStatus())
                .betweenIfPresent(AdminUserDO::getCreateTime, pageReqVO.getCreateTime())
                .orderByDesc(AdminUserDO::getId);
        wrapper.isNull(AdminUserDO::getDeptId);
        PageResult<AdminUserDO> page = adminUserMapper.selectPage(pageReqVO, wrapper);
        return success(new PageResult<>(page.getList().stream().map(this::convertToResVO).toList(), page.getTotal()));
    }

    // ---------------------------------------------------------------- 私有方法

    private void validateExists(Long id) {
        if (id == null || adminUserMapper.selectById(id) == null) {
            throw exception(DLZH_NOT_EXISTS);
        }
    }

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
