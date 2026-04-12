package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.rws;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.rws.vo.RwsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.rws.vo.RwsResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.rws.vo.RwsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.rws.RwsDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.rws.RwsService;
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


@Tag(name = "管理后台 - 年度任务")
@RestController
@RequestMapping("/lghjft/sjwh/rws")
@Validated
public class RwsController {

    @Resource
    private RwsService rwsService;

    @PostMapping("/create")
    @Operation(summary = "创建年度任务")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-rws:create')")
    public CommonResult<Long> createRws(@Valid @RequestBody RwsSaveReqVO createReqVO) {
        return success(rwsService.createRws(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新年度任务")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-rws:update')")
    public CommonResult<Boolean> updateRws(@Valid @RequestBody RwsSaveReqVO updateReqVO) {
        rwsService.updateRws(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除年度任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-rws:delete')")
    public CommonResult<Boolean> deleteRws(@RequestParam("id") Long id) {
        rwsService.deleteRws(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除年度任务")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-rws:delete')")
    public CommonResult<Boolean> deleteRwsList(@RequestParam("ids") List<Long> ids) {
        rwsService.deleteRwsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得年度任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-rws:query')")
    public CommonResult<RwsResVO> getRws(@RequestParam("id") Long id) {
        RwsDO rws = rwsService.getRws(id);
        return success(BeanUtils.toBean(rws, RwsResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得年度任务分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-rws:query')")
    public CommonResult<PageResult<RwsResVO>> getRwsPage(@Valid RwsPageReqVO pageReqVO) {
        PageResult<RwsDO> pageResult = rwsService.getRwsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RwsResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出年度任务 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-rws:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRwsExcel(@Valid RwsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RwsDO> list = rwsService.getRwsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "年度任务.xls", "数据", RwsResVO.class,
                BeanUtils.toBean(list, RwsResVO.class));
    }

}
