package cn.iocoder.yudao.module.lghjft.controller.admin.rws;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.lghjft.controller.admin.rws.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.rws.RwsDO;
import cn.iocoder.yudao.module.lghjft.service.rws.RwsService;

@Tag(name = "管理后台 - 年度任务")
@RestController
@RequestMapping("/lghjft/rws")
@Validated
public class RwsController {

    @Resource
    private RwsService rwsService;

    @PostMapping("/create")
    @Operation(summary = "创建年度任务")
    @PreAuthorize("@ss.hasPermission('lghjft:rws:create')")
    public CommonResult<Integer> createRws(@Valid @RequestBody RwsSaveReqVO createReqVO) {
        return success(rwsService.createRws(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新年度任务")
    @PreAuthorize("@ss.hasPermission('lghjft:rws:update')")
    public CommonResult<Boolean> updateRws(@Valid @RequestBody RwsSaveReqVO updateReqVO) {
        rwsService.updateRws(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除年度任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:rws:delete')")
    public CommonResult<Boolean> deleteRws(@RequestParam("id") Integer id) {
        rwsService.deleteRws(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除年度任务")
                @PreAuthorize("@ss.hasPermission('lghjft:rws:delete')")
    public CommonResult<Boolean> deleteRwsList(@RequestParam("ids") List<Integer> ids) {
        rwsService.deleteRwsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得年度任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:rws:query')")
    public CommonResult<RwsRespVO> getRws(@RequestParam("id") Integer id) {
        RwsDO rws = rwsService.getRws(id);
        return success(BeanUtils.toBean(rws, RwsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得年度任务分页")
    @PreAuthorize("@ss.hasPermission('lghjft:rws:query')")
    public CommonResult<PageResult<RwsRespVO>> getRwsPage(@Valid RwsPageReqVO pageReqVO) {
        PageResult<RwsDO> pageResult = rwsService.getRwsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RwsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出年度任务 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:rws:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRwsExcel(@Valid RwsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RwsDO> list = rwsService.getRwsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "年度任务.xls", "数据", RwsRespVO.class,
                        BeanUtils.toBean(list, RwsRespVO.class));
    }

}