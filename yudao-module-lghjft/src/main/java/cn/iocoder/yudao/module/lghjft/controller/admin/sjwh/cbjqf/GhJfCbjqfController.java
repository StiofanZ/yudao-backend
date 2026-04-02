package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf.vo.GhJfCbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf.vo.GhJfCbjqfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf.vo.GhJfCbjqfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjqf.GhJfCbjqfDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.cbjqf.GhJfCbjqfService;
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

@Tag(name = "管理后台 - 筹备金全返")
@RestController
@RequestMapping("/lghjft/sjwh/cbjqf")
@Validated
public class GhJfCbjqfController {

    @Resource
    private GhJfCbjqfService ghJfCbjqfService;

    @PostMapping("/create")
    @Operation(summary = "创建筹备金全返")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqf:create')")
    public CommonResult<Long> createGhJfCbjqf(@Valid @RequestBody GhJfCbjqfSaveReqVO createReqVO) {
        return success(ghJfCbjqfService.createGhJfCbjqf(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新筹备金全返")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqf:update')")
    public CommonResult<Boolean> updateGhJfCbjqf(@Valid @RequestBody GhJfCbjqfSaveReqVO updateReqVO) {
        ghJfCbjqfService.updateGhJfCbjqf(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除筹备金全返")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqf:delete')")
    public CommonResult<Boolean> deleteGhJfCbjqf(@RequestParam("id") Long id) {
        ghJfCbjqfService.deleteGhJfCbjqf(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除筹备金全返")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqf:delete')")
    public CommonResult<Boolean> deleteGhJfCbjqfList(@RequestParam("ids") List<Long> ids) {
        ghJfCbjqfService.deleteGhJfCbjqfListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得筹备金全返")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqf:query')")
    public CommonResult<GhJfCbjqfResVO> getGhJfCbjqf(@RequestParam("id") Long id) {
        GhJfCbjqfDO ghJfCbjqf = ghJfCbjqfService.getGhJfCbjqf(id);
        return success(BeanUtils.toBean(ghJfCbjqf, GhJfCbjqfResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得筹备金全返分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqf:query')")
    public CommonResult<PageResult<GhJfCbjqfResVO>> getGhJfCbjqfPage(@Valid GhJfCbjqfPageReqVO pageReqVO) {
        PageResult<GhJfCbjqfDO> pageResult = ghJfCbjqfService.getGhJfCbjqfPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhJfCbjqfResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金全返 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGhJfCbjqfExcel(@Valid GhJfCbjqfPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhJfCbjqfDO> list = ghJfCbjqfService.getGhJfCbjqfPage(pageReqVO).getList();
        ExcelUtils.write(response, "筹备金全返.xls", "数据", GhJfCbjqfResVO.class,
                BeanUtils.toBean(list, GhJfCbjqfResVO.class));
    }
}
