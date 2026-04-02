package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqfmx.vo.CbjqfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqfmx.vo.CbjqfmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqfmx.vo.CbjqfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjqfmx.CbjqfmxDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.cbjqfmx.CbjqfmxService;
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

@Tag(name = "管理后台 - 筹备金全返明细")
@RestController
@RequestMapping("/lghjft/sjwh/cbjqfmx")
@Validated
public class CbjqfmxController {

    @Resource
    private CbjqfmxService cbjqfmxService;

    @PostMapping("/create")
    @Operation(summary = "创建筹备金全返明细")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqfmx:create')")
    public CommonResult<Long> createCbjqfmx(@Valid @RequestBody CbjqfmxSaveReqVO createReqVO) {
        return success(cbjqfmxService.createCbjqfmx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新筹备金全返明细")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqfmx:update')")
    public CommonResult<Boolean> updateCbjqfmx(@Valid @RequestBody CbjqfmxSaveReqVO updateReqVO) {
        cbjqfmxService.updateCbjqfmx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除筹备金全返明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqfmx:delete')")
    public CommonResult<Boolean> deleteCbjqfmx(@RequestParam("id") Long id) {
        cbjqfmxService.deleteCbjqfmx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除筹备金全返明细")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqfmx:delete')")
    public CommonResult<Boolean> deleteCbjqfmxList(@RequestParam("ids") List<Long> ids) {
        cbjqfmxService.deleteCbjqfmxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得筹备金全返明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqfmx:query')")
    public CommonResult<CbjqfmxResVO> getCbjqfmx(@RequestParam("id") Long id) {
        CbjqfmxDO cbjqfmx = cbjqfmxService.getCbjqfmx(id);
        return success(BeanUtils.toBean(cbjqfmx, CbjqfmxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得筹备金全返明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqfmx:query')")
    public CommonResult<PageResult<CbjqfmxResVO>> getCbjqfmxPage(@Valid CbjqfmxPageReqVO pageReqVO) {
        PageResult<CbjqfmxDO> pageResult = cbjqfmxService.getCbjqfmxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CbjqfmxResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金全返明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjqfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjqfmxExcel(@Valid CbjqfmxPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CbjqfmxDO> list = cbjqfmxService.getCbjqfmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "筹备金全返明细.xls", "数据", CbjqfmxResVO.class,
                BeanUtils.toBean(list, CbjqfmxResVO.class));
    }
}
