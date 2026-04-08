package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.znjzz.ZnjzzService;
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

@Tag(name = "管理后台 - 滞纳金做账")
@RestController
@RequestMapping("/lghjft/hbzz/znjzz")
@Validated
public class ZnjzzController {

    @Resource
    private ZnjzzService znjzzService;

    @PostMapping("/create")
    @Operation(summary = "创建滞纳金做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjzz:create')")
    public CommonResult<Long> createZnjzz(@Valid @RequestBody ZnjzzSaveReqVO createReqVO) {
        return success(znjzzService.createZnjzz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新滞纳金做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjzz:update')")
    public CommonResult<Boolean> updateZnjzz(@Valid @RequestBody ZnjzzSaveReqVO updateReqVO) {
        znjzzService.updateZnjzz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除滞纳金做账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjzz:delete')")
    public CommonResult<Boolean> deleteZnjzz(@RequestParam("id") Long id) {
        znjzzService.deleteZnjzz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除滞纳金做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjzz:delete')")
    public CommonResult<Boolean> deleteZnjzzList(@RequestParam("ids") List<Long> ids) {
        znjzzService.deleteZnjzzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得滞纳金做账（含确认���账子表）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjzz:query')")
    public CommonResult<ZnjzzResVO> getZnjzz(@RequestParam("id") Long id) {
        return success(znjzzService.getZnjzz(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得滞纳金做账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjzz:query')")
    public CommonResult<PageResult<ZnjzzResVO>> getZnjzzPage(@Valid ZnjzzPageReqVO pageReqVO) {
        return success(znjzzService.getZnjzzPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出滞纳金做账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjzz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportZnjzzExcel(@Valid ZnjzzPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ZnjzzResVO> list = znjzzService.getZnjzzPage(pageReqVO).getList();
        ExcelUtils.write(response, "滞纳金做账.xls", "数据", ZnjzzResVO.class, list);
    }
}
