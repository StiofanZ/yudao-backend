package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bszn.BsznDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.bszn.BsznService;
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

@Tag(name = "管理后台 - 办事指南")
@RestController
@RequestMapping("/lghjft/nrgl/bszn")
@Validated
public class BsznController {

    @Resource
    private BsznService bsznService;

    @Resource
    private DeptApi deptApi;

    @PostMapping("/create")
    @Operation(summary = "创建办事指南")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bszn:create')")
    public CommonResult<Long> createBszn(@Valid @RequestBody BsznCreateReqVO createReqVO) {
        return success(bsznService.createBszn(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新办事指南")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bszn:update')")
    public CommonResult<Boolean> updateBszn(@Valid @RequestBody BsznUpdateReqVO updateReqVO) {
        bsznService.updateBszn(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除办事指南")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bszn:delete')")
    public CommonResult<Boolean> deleteBszn(@RequestParam("id") Long id) {
        bsznService.deleteBszn(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得办事指南")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bszn:query')")
    public CommonResult<BsznRespVO> getBszn(@RequestParam("id") Long id) {
        BsznDO bszn = bsznService.getBszn(id);
        return success(BeanUtils.toBean(bszn, BsznRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得办事指南列表")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bszn:query')")
    public CommonResult<List<BsznRespVO>> getBsznList(@Valid BsznListReqVO listReqVO) {
        List<BsznDO> list = bsznService.getBsznList(listReqVO);
        List<BsznRespVO> result = BeanUtils.toBean(list, BsznRespVO.class);
        
        // 填充部门名称
        if (!result.isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result, BsznRespVO::getDeptId));
            result.forEach(item -> {
                DeptRespDTO dept = deptMap.get(item.getDeptId());
                if (dept != null) {
                    item.setDeptName(dept.getName());
                }
            });
        }
        
        return success(result);
    }

    @PutMapping("/publish")
    @Operation(summary = "发布办事指南")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bszn:update')")
    public CommonResult<Boolean> publishBszn(@RequestParam("id") Long id) {
        bsznService.publishBszn(id);
        return success(true);
    }

    @PutMapping("/off-shelf")
    @Operation(summary = "下架办事指南")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bszn:update')")
    public CommonResult<Boolean> offShelfBszn(@RequestParam("id") Long id, @RequestParam("reason") String reason) {
        bsznService.offShelfBszn(id, reason);
        return success(true);
    }

    @PutMapping("/audit")
    @Operation(summary = "审核办事指南")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bszn:update')")
    public CommonResult<Boolean> auditBszn(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        bsznService.auditBszn(id, status);
        return success(true);
    }

    @GetMapping("/public/list")
    @Operation(summary = "获得公开办事指南列表")
    @Parameter(name = "deptId", description = "部门编号", required = true)
    public CommonResult<List<BsznRespVO>> getPublicBsznList(@RequestParam("deptId") Long deptId) {
        List<BsznDO> list = bsznService.getPublicBsznList(deptId);
        List<BsznRespVO> result = BeanUtils.toBean(list, BsznRespVO.class);
        
        // 填充部门名称
        if (!result.isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result, BsznRespVO::getDeptId));
            result.forEach(item -> {
                DeptRespDTO dept = deptMap.get(item.getDeptId());
                if (dept != null) {
                    item.setDeptName(dept.getName());
                }
            });
        }
        
        return success(result);
    }

}
