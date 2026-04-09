package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo.GhHjXejf24PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo.GhHjXejf24ResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo.GhHjXejf24SaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf24.GhHjXejf24DO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xejf24.GhHjXejf24Service;
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

@Tag(name = "管理后台 - 24年小额确认")
@RestController
@RequestMapping("/lghjft/xejf/xejf24")
@Validated
public class GhHjXejf24Controller {

    @Resource
    private GhHjXejf24Service ghHjXejf24Service;

    @PostMapping("/create")
    @Operation(summary = "创建24年小额确认")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf24:create')")
    public CommonResult<String> create(@Valid @RequestBody GhHjXejf24SaveReqVO createReqVO) {
        return success(ghHjXejf24Service.createGhHjXejf24(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新24年小额确认")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf24:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody GhHjXejf24SaveReqVO updateReqVO) {
        ghHjXejf24Service.updateGhHjXejf24(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除24年小额确认")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf24:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") String id) {
        ghHjXejf24Service.deleteGhHjXejf24(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除24年小额确认")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf24:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<String> ids) {
        ghHjXejf24Service.deleteGhHjXejf24ListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得24年小额确认")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf24:query')")
    public CommonResult<GhHjXejf24ResVO> get(@RequestParam("id") String id) {
        GhHjXejf24DO data = ghHjXejf24Service.getGhHjXejf24(id);
        return success(BeanUtils.toBean(data, GhHjXejf24ResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得24年小额确认分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf24:query')")
    public CommonResult<PageResult<GhHjXejf24ResVO>> page(@Valid GhHjXejf24PageReqVO pageReqVO) {
        PageResult<GhHjXejf24DO> pageResult = ghHjXejf24Service.getGhHjXejf24Page(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhHjXejf24ResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出24年小额确认 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf24:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid GhHjXejf24PageReqVO pageReqVO,
                            HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhHjXejf24DO> list = ghHjXejf24Service.getGhHjXejf24Page(pageReqVO).getList();
        ExcelUtils.write(response, "24年小额确认.xls", "数据", GhHjXejf24ResVO.class,
                BeanUtils.toBean(list, GhHjXejf24ResVO.class));
    }
}
