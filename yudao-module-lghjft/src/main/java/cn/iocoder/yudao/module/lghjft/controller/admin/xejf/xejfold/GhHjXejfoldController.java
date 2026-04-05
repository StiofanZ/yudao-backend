package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold.vo.GhHjXejfoldPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold.vo.GhHjXejfoldResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold.vo.GhHjXejfoldSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfold.GhHjXejfoldDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xejfold.GhHjXejfoldService;
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

@Tag(name = "管理后台 - 23年小额确认")
@RestController
@RequestMapping("/lghjft/xejf/xejfold")
@Validated
public class GhHjXejfoldController {

    @Resource
    private GhHjXejfoldService ghHjXejfoldService;

    @PostMapping("/create")
    @Operation(summary = "创建23年小额确认")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfold:create')")
    public CommonResult<String> create(@Valid @RequestBody GhHjXejfoldSaveReqVO createReqVO) {
        return success(ghHjXejfoldService.createGhHjXejfold(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新23年小额确认")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfold:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody GhHjXejfoldSaveReqVO updateReqVO) {
        ghHjXejfoldService.updateGhHjXejfold(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除23年小额确认")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfold:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") String id) {
        ghHjXejfoldService.deleteGhHjXejfold(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除23年小额确认")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfold:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<String> ids) {
        ghHjXejfoldService.deleteGhHjXejfoldListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得23年小额确认")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfold:query')")
    public CommonResult<GhHjXejfoldResVO> get(@RequestParam("id") String id) {
        GhHjXejfoldDO data = ghHjXejfoldService.getGhHjXejfold(id);
        return success(BeanUtils.toBean(data, GhHjXejfoldResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得23年小额确认分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfold:query')")
    public CommonResult<PageResult<GhHjXejfoldResVO>> page(@Valid GhHjXejfoldPageReqVO pageReqVO) {
        PageResult<GhHjXejfoldDO> pageResult = ghHjXejfoldService.getGhHjXejfoldPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhHjXejfoldResVO.class));
    }
}
