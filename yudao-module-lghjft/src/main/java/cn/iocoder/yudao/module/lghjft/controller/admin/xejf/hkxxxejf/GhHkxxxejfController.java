package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejf.GhHkxxxejfDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejf.GhHkxxxejfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 小额拨付记账凭证")
@RestController
@RequestMapping("/lghjft/xejf/hkxxxejf")
@Validated
public class GhHkxxxejfController {

    @Resource
    private GhHkxxxejfService ghHkxxxejfService;

    @PostMapping("/create")
    @Operation(summary = "创建小额拨付记账凭证")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:create')")
    public CommonResult<Long> create(@Valid @RequestBody GhHkxxxejfSaveReqVO createReqVO) {
        return success(ghHkxxxejfService.createGhHkxxxejf(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额拨付记账凭证")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody GhHkxxxejfSaveReqVO updateReqVO) {
        ghHkxxxejfService.updateGhHkxxxejf(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额拨付记账凭证")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        ghHkxxxejfService.deleteGhHkxxxejf(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额拨付记账凭证")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        ghHkxxxejfService.deleteGhHkxxxejfListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额拨付记账凭证")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:query')")
    public CommonResult<GhHkxxxejfResVO> get(@RequestParam("id") Long id) {
        GhHkxxxejfDO data = ghHkxxxejfService.getGhHkxxxejf(id);
        return success(BeanUtils.toBean(data, GhHkxxxejfResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额拨付记账凭证分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:query')")
    public CommonResult<PageResult<GhHkxxxejfResVO>> page(@Valid GhHkxxxejfPageReqVO pageReqVO) {
        PageResult<GhHkxxxejfDO> pageResult = ghHkxxxejfService.getGhHkxxxejfPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhHkxxxejfResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额拨付记账凭证 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid GhHkxxxejfPageReqVO pageReqVO,
                            HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhHkxxxejfDO> list = ghHkxxxejfService.getGhHkxxxejfPage(pageReqVO).getList();
        ExcelUtils.write(response, "小额拨付记账凭证.xls", "数据", GhHkxxxejfResVO.class,
                BeanUtils.toBean(list, GhHkxxxejfResVO.class));
    }
}
