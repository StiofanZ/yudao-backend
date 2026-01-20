package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.cjwt.CjwtDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.cjwt.CjwtService;
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

@Tag(name = "管理后台 - 常见问题")
@RestController
@RequestMapping("/lghjft/nrgl/cjwt")
@Validated
public class CjwtController {

    @Resource
    private CjwtService cjwtService;

    @Resource
    private DeptApi deptApi;

    @PostMapping("/create")
    @Operation(summary = "创建常见问题")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:create')")
    public CommonResult<Long> createCjwt(@Valid @RequestBody CjwtCreateReqVO createReqVO) {
        return success(cjwtService.createCjwt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新常见问题")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:update')")
    public CommonResult<Boolean> updateCjwt(@Valid @RequestBody CjwtUpdateReqVO updateReqVO) {
        cjwtService.updateCjwt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除常见问题")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:delete')")
    public CommonResult<Boolean> deleteCjwt(@RequestParam("id") Long id) {
        cjwtService.deleteCjwt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得常见问题")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:query')")
    public CommonResult<CjwtRespVO> getCjwt(@RequestParam("id") Long id) {
        CjwtDO cjwt = cjwtService.getCjwt(id);
        return success(BeanUtils.toBean(cjwt, CjwtRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得常见问题列表")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:query')")
    public CommonResult<List<CjwtRespVO>> getCjwtList(@Valid CjwtListReqVO listReqVO) {
        List<CjwtDO> list = cjwtService.getCjwtList(listReqVO);
        List<CjwtRespVO> result = BeanUtils.toBean(list, CjwtRespVO.class);
        
        // 填充部门名称
        if (!result.isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result, CjwtRespVO::getDeptId));
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
    @Operation(summary = "发布常见问题")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:update')")
    public CommonResult<Boolean> publishCjwt(@RequestParam("id") Long id) {
        cjwtService.publishCjwt(id);
        return success(true);
    }

    @PutMapping("/off-shelf")
    @Operation(summary = "下架常见问题")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:update')")
    public CommonResult<Boolean> offShelfCjwt(@RequestParam("id") Long id, @RequestParam("reason") String reason) {
        cjwtService.offShelfCjwt(id, reason);
        return success(true);
    }

    @PutMapping("/audit")
    @Operation(summary = "审核常见问题")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:update')")
    public CommonResult<Boolean> auditCjwt(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        cjwtService.auditCjwt(id, status);
        return success(true);
    }

    @GetMapping("/public/list")
    @Operation(summary = "获得公开常见问题列表")
    @Parameter(name = "deptId", description = "部门编号", required = true)
    public CommonResult<List<CjwtRespVO>> getPublicCjwtList(@RequestParam("deptId") Long deptId) {
        List<CjwtDO> list = cjwtService.getPublicCjwtList(deptId);
        List<CjwtRespVO> result = BeanUtils.toBean(list, CjwtRespVO.class);
        
        // 填充部门名称
        if (!result.isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result, CjwtRespVO::getDeptId));
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
