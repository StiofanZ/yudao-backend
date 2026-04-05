package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfjcdz.GhHkxxxejfjcdzDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejfjcdz.GhHkxxxejfjcdzService;
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

@Tag(name = "管理后台 - 小额缴费基层到账")
@RestController
@RequestMapping("/lghjft/xejf/hkxxxejfjcdz")
@Validated
public class GhHkxxxejfjcdzController {

    @Resource
    private GhHkxxxejfjcdzService ghHkxxxejfjcdzService;

    @PostMapping("/create")
    @Operation(summary = "创建小额缴费基层到账")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfjcdz:create')")
    public CommonResult<Long> create(@Valid @RequestBody GhHkxxxejfjcdzSaveReqVO createReqVO) {
        return success(ghHkxxxejfjcdzService.createGhHkxxxejfjcdz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额缴费基层到账")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfjcdz:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody GhHkxxxejfjcdzSaveReqVO updateReqVO) {
        ghHkxxxejfjcdzService.updateGhHkxxxejfjcdz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额缴费基层到账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfjcdz:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        ghHkxxxejfjcdzService.deleteGhHkxxxejfjcdz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额缴费基层到账")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfjcdz:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        ghHkxxxejfjcdzService.deleteGhHkxxxejfjcdzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额缴费基层到账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfjcdz:query')")
    public CommonResult<GhHkxxxejfjcdzResVO> get(@RequestParam("id") Long id) {
        GhHkxxxejfjcdzDO data = ghHkxxxejfjcdzService.getGhHkxxxejfjcdz(id);
        return success(BeanUtils.toBean(data, GhHkxxxejfjcdzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额缴费基层到账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfjcdz:query')")
    public CommonResult<PageResult<GhHkxxxejfjcdzResVO>> page(@Valid GhHkxxxejfjcdzPageReqVO pageReqVO) {
        PageResult<GhHkxxxejfjcdzDO> pageResult = ghHkxxxejfjcdzService.getGhHkxxxejfjcdzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhHkxxxejfjcdzResVO.class));
    }
}
