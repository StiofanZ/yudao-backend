package cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jf.ghjf.GhJfDO;
import cn.iocoder.yudao.module.lghjft.service.jf.ghjf.GhJfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 税务入库")
@RestController
@RequestMapping("/lghjft/gh-jf")
@Validated
public class GhJfController {

    @Resource
    private GhJfService ghJfService;

    @PostMapping("/create")
    @Operation(summary = "创建税务入库")
    @PreAuthorize("@ss.hasPermission('lghjft:gh-jf:create')")
    public CommonResult<Integer> createGhJf(@Valid @RequestBody GhJfCreateReqVO createReqVO) {
        return success(ghJfService.createGhJf(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新税务入库")
    @PreAuthorize("@ss.hasPermission('lghjft:gh-jf:update')")
    public CommonResult<Boolean> updateGhJf(@Valid @RequestBody GhJfUpdateReqVO updateReqVO) {
        ghJfService.updateGhJf(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除税务入库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:gh-jf:delete')")
    public CommonResult<Boolean> deleteGhJf(@RequestParam("id") Integer id) {
        ghJfService.deleteGhJf(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得税务入库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:gh-jf:query')")
    public CommonResult<GhJfResVO> getGhJf(@RequestParam("id") Integer id) {
        GhJfDO ghJf = ghJfService.getGhJf(id);
        return success(BeanUtils.toBean(ghJf, GhJfResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得税务入库分页")
    @PreAuthorize("@ss.hasPermission('lghjft:gh-jf:query')")
    public CommonResult<PageResult<GhJfResVO>> getGhJfPage(@Valid GhJfReqVO pageReqVO) {
        PageResult<GhJfDO> pageResult = ghJfService.getGhJfPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhJfResVO.class));
    }

}
