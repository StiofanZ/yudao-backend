package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejfcbj.GhHkxxxejfcbjService;
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

@Tag(name = "管理后台 - 小额筹备金做账")
@RestController
@RequestMapping("/lghjft/xejf/hkxxxejfcbj")
@Validated
public class GhHkxxxejfcbjController {

    @Resource
    private GhHkxxxejfcbjService ghHkxxxejfcbjService;

    @PostMapping("/create")
    @Operation(summary = "创建小额筹备金做账")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfcbj:create')")
    public CommonResult<Long> create(@Valid @RequestBody GhHkxxxejfcbjSaveReqVO createReqVO) {
        return success(ghHkxxxejfcbjService.createGhHkxxxejfcbj(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额筹备金做账")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfcbj:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody GhHkxxxejfcbjSaveReqVO updateReqVO) {
        ghHkxxxejfcbjService.updateGhHkxxxejfcbj(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额筹备金做账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfcbj:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        ghHkxxxejfcbjService.deleteGhHkxxxejfcbj(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额筹备金做账")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfcbj:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        ghHkxxxejfcbjService.deleteGhHkxxxejfcbjListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额筹备金做账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfcbj:query')")
    public CommonResult<GhHkxxxejfcbjResVO> get(@RequestParam("id") Long id) {
        return success(ghHkxxxejfcbjService.getGhHkxxxejfcbj(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额筹备金做账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfcbj:query')")
    public CommonResult<PageResult<GhHkxxxejfcbjResVO>> page(@Valid GhHkxxxejfcbjPageReqVO pageReqVO) {
        return success(ghHkxxxejfcbjService.getGhHkxxxejfcbjPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额筹备金做账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejfcbj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid GhHkxxxejfcbjPageReqVO pageReqVO,
                            HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhHkxxxejfcbjResVO> list = ghHkxxxejfcbjService.getGhHkxxxejfcbjPage(pageReqVO).getList();
        ExcelUtils.write(response, "小额筹备金做账.xls", "数据", GhHkxxxejfcbjResVO.class, list);
    }
}
