package cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.GhQxSfxxService;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 身份信息")
@RestController
@RequestMapping("/lghjft/qx/sfxx")
@Validated
public class SfxxController {

    @Resource
    private GhQxSfxxService ghQxSfxxService;
    @Resource
    private DeptApi deptApi;

    @PostMapping("/create")
    @Operation(summary = "创建身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:create')")
    public CommonResult<Long> createSfxx(@Valid @RequestBody SfxxSaveReqVO createReqVO) {
        return success(ghQxSfxxService.createSfxx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:update')")
    public CommonResult<Boolean> updateSfxx(@Valid @RequestBody SfxxSaveReqVO updateReqVO) {
        ghQxSfxxService.updateSfxx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除身份信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:delete')")
    public CommonResult<Boolean> deleteSfxx(@RequestParam("id") Long id) {
        ghQxSfxxService.deleteSfxx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除身份信息")
    @Parameter(name = "ids", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:delete')")
    public CommonResult<Boolean> deleteSfxxList(@RequestParam("ids") List<Long> ids) {
        ghQxSfxxService.deleteSfxxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得身份信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:query')")
    public CommonResult<SfxxRespVO> getSfxx(@RequestParam("id") Long id) {
        GhQxSfxxDO sfxx = ghQxSfxxService.getSfxx(id);
        SfxxRespVO respVO = BeanUtils.toBean(sfxx, SfxxRespVO.class);
        if (respVO != null && respVO.getDeptId() != null) {
            DeptRespDTO dept = deptApi.getDept(respVO.getDeptId());
            if (dept != null) {
                respVO.setDeptName(dept.getName());
            }
        }
        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得身份信息分页")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:query')")
    public CommonResult<PageResult<SfxxRespVO>> getSfxxPage(@Valid SfxxPageReqVO pageReqVO) {
        PageResult<GhQxSfxxDO> pageResult = ghQxSfxxService.getSfxxPage(pageReqVO);
        PageResult<SfxxRespVO> result = BeanUtils.toBean(pageResult, SfxxRespVO.class);
        if (!result.getList().isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result.getList(), SfxxRespVO::getDeptId));
            result.getList().forEach(item -> {
                DeptRespDTO dept = deptMap.get(item.getDeptId());
                if (dept != null) {
                    item.setDeptName(dept.getName());
                }
            });
        }
        return success(result);
    }

    @PutMapping("/audit")
    @Operation(summary = "审核身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:audit')")
    public CommonResult<Boolean> auditSfxx(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        ghQxSfxxService.auditSfxx(id, status);
        return success(true);
    }

}
