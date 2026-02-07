package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhResetPasswordReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhResVO;
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

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 登录账号")
@RestController
@RequestMapping("/lghjft/qx/dlzh")
@Validated
public class DlzhController {

    @Resource
    private GhQxDlzhService ghQxDlzhService;

    @PostMapping("/create")
    @Operation(summary = "创建登录账号")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:create')")
    public CommonResult<Long> createDlzh(@Valid @RequestBody DlzhSaveReqVO createReqVO) {
        return success(ghQxDlzhService.createDlzh(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新登录账号")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:update')")
    public CommonResult<Boolean> updateDlzh(@Valid @RequestBody DlzhSaveReqVO updateReqVO) {
        ghQxDlzhService.updateDlzh(updateReqVO);
        return success(true);
    }

    @PutMapping("/reset-password")
    @Operation(summary = "重置登录账号密码")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:reset-password')")
    public CommonResult<Boolean> resetPassword(@Valid @RequestBody DlzhResetPasswordReqVO reqVO) {
        ghQxDlzhService.resetPassword(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除登录账号")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:delete')")
    public CommonResult<Boolean> deleteDlzh(@RequestParam("id") Long id) {
        ghQxDlzhService.deleteDlzh(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除登录账号")
    @Parameter(name = "ids", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:delete')")
    public CommonResult<Boolean> deleteDlzhList(@RequestParam("ids") List<Long> ids) {
        ghQxDlzhService.deleteDlzhListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得登录账号")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:query')")
    public CommonResult<DlzhResVO> getDlzh(@RequestParam("id") Long id) {
        GhQxDlzhDO dlzh = ghQxDlzhService.getDlzh(id);
        return success(BeanUtils.toBean(dlzh, DlzhResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得登录账号分页")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dlzh:query')")
    public CommonResult<PageResult<DlzhResVO>> getDlzhPage(@Valid DlzhReqVO pageReqVO) {
        PageResult<GhQxDlzhDO> pageResult = ghQxDlzhService.getDlzhPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DlzhResVO.class));
    }

}
