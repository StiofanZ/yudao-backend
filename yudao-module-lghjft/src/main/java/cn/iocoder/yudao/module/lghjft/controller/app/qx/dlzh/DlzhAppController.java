package cn.iocoder.yudao.module.lghjft.controller.app.qx.dlzh;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhResetPasswordReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.service.qx.dlzh.GhQxDlzhService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 登录账号")
@RestController
@RequestMapping("/lghjft/qx/dlzh")
@Validated
public class DlzhAppController {

    @Resource
    private GhQxDlzhService ghQxDlzhService;

    @PutMapping("/update")
    @Operation(summary = "更新登录账号")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> updateDlzh(@Valid @RequestBody DlzhSaveReqVO updateReqVO) {
        updateReqVO.setId(SecurityFrameworkUtils.getLoginUserId());
        ghQxDlzhService.updateDlzh(updateReqVO);
        return success(true);
    }

    @PutMapping("/reset-password")
    @Operation(summary = "重置登录账号密码")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> resetPassword(@Valid @RequestBody DlzhResetPasswordReqVO reqVO) {
        reqVO.setId(SecurityFrameworkUtils.getLoginUserId());
        ghQxDlzhService.resetPassword(reqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得登录账号")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<DlzhResVO> getDlzh() {
        GhQxDlzhDO dlzh = ghQxDlzhService.getDlzh(SecurityFrameworkUtils.getLoginUserId());
        return success(BeanUtils.toBean(dlzh, DlzhResVO.class));
    }

}
