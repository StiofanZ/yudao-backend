package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcjd.ZcjdDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.zcjd.ZcjdService;
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
@Tag(name = "管理后台 - 政策解读")
@RestController
@RequestMapping("/lghjft/nrgl/zcjd")
@Validated
public class ZcjdController {

    @Resource
    private ZcjdService zcjdService;

    @Resource
    private DeptApi deptApi;

    @PostMapping("/create")
    @Operation(summary = "创建政策解读")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:create')")
    public CommonResult<Long> createZcjd(@Valid @RequestBody ZcjdCreateReqVO createReqVO) {
        return success(zcjdService.createZcjd(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策解读")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:update')")
    public CommonResult<Boolean> updateZcjd(@Valid @RequestBody ZcjdUpdateReqVO updateReqVO) {
        zcjdService.updateZcjd(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策解读")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:delete')")
    public CommonResult<Boolean> deleteZcjd(@RequestParam("id") Long id) {
        zcjdService.deleteZcjd(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策解读")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:query')")
    public CommonResult<ZcjdRespVO> getZcjd(@RequestParam("id") Long id) {
        ZcjdDO zcjd = zcjdService.getZcjd(id);
        return success(BeanUtils.toBean(zcjd, ZcjdRespVO.class));
    }


// ...

    @GetMapping("/list-page")
    @Operation(summary = "获得政策解读分页列表")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:query')")
    public CommonResult<PageResult<ZcjdRespVO>> getZcjdPage(@Validated ZcjdPageReqVO pageReqVO) {
        PageResult<ZcjdDO> pageResult = zcjdService.getZcjdPage(pageReqVO);
        List<ZcjdRespVO> result = BeanUtils.toBean(pageResult.getList(), ZcjdRespVO.class);
        
        // 填充部门名称
        if (!result.isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result, ZcjdRespVO::getDeptId));
            result.forEach(item -> {
                DeptRespDTO dept = deptMap.get(item.getDeptId());
                if (dept != null) {
                    item.setDeptName(dept.getName());
                }
            });
        }

        return success(new PageResult<>(result, pageResult.getTotal()));
    }

    @PutMapping("/publish")
    @Operation(summary = "发布政策解读")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:update')")
    public CommonResult<Boolean> publishZcjd(@RequestParam("id") Long id) {
        zcjdService.publishZcjd(id);
        return success(true);
    }

    @PutMapping("/off-shelf")
    @Operation(summary = "下架政策解读")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:update')")
    public CommonResult<Boolean> offShelfZcjd(@RequestParam("id") Long id, @RequestParam("reason") String reason) {
        zcjdService.offShelfZcjd(id, reason);
        return success(true);
    }

    @PutMapping("/audit")
    @Operation(summary = "审核政策解读")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:update')")
    public CommonResult<Boolean> auditZcjd(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        zcjdService.auditZcjd(id, status);
        return success(true);
    }

}
