package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.sxfzz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.sxfzz.vo.SxfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.sxfzz.vo.SxfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.sxfzz.vo.SxfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.sxfzz.SxfzzDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.sxfzz.SxfzzService;
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

@Tag(name = "管理后台 - 手续费做账")
@RestController
@RequestMapping("/lghjft/sjwh/sxfzz")
@Validated
public class SxfzzController {

    @Resource
    private SxfzzService sxfzzService;

    @PostMapping("/create")
    @Operation(summary = "创建手续费做账")
    @PreAuthorize("@ss.hasPermission('lghjft:sxfzz:create')")
    public CommonResult<Long> createSxfzz(@Valid @RequestBody SxfzzSaveReqVO createReqVO) {
        return success(sxfzzService.createSxfzz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新手续费做账")
    @PreAuthorize("@ss.hasPermission('lghjft:sxfzz:update')")
    public CommonResult<Boolean> updateSxfzz(@Valid @RequestBody SxfzzSaveReqVO updateReqVO) {
        sxfzzService.updateSxfzz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除手续费做账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sxfzz:delete')")
    public CommonResult<Boolean> deleteSxfzz(@RequestParam("id") Long id) {
        sxfzzService.deleteSxfzz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除手续费做账")
    @PreAuthorize("@ss.hasPermission('lghjft:sxfzz:delete')")
    public CommonResult<Boolean> deleteSxfzzList(@RequestParam("ids") List<Long> ids) {
        sxfzzService.deleteSxfzzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得手续费做账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:sxfzz:query')")
    public CommonResult<SxfzzResVO> getSxfzz(@RequestParam("id") Long id) {
        SxfzzDO sxfzz = sxfzzService.getSxfzz(id);
        return success(BeanUtils.toBean(sxfzz, SxfzzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得手续费做账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sxfzz:query')")
    public CommonResult<PageResult<SxfzzResVO>> getSxfzzPage(@Valid SxfzzPageReqVO pageReqVO) {
        PageResult<SxfzzDO> pageResult = sxfzzService.getSxfzzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SxfzzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出手续费做账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sxfzz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSxfzzExcel(@Valid SxfzzPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SxfzzDO> list = sxfzzService.getSxfzzPage(pageReqVO).getList();
        ExcelUtils.write(response, "手续费做账.xls", "数据", SxfzzResVO.class,
                BeanUtils.toBean(list, SxfzzResVO.class));
    }
}
