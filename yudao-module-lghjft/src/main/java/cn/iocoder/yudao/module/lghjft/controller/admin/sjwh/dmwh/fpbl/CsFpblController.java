package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.fpbl.CsFpblDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.fpbl.CsFpblService;
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

@Tag(name = "管理后台 - 分配比例")
@RestController
@RequestMapping("/lghjft/sjwh/dmwh/fpbl")
@Validated
public class CsFpblController {

    @Resource
    private CsFpblService csFpblService;

    @PostMapping("/create")
    @Operation(summary = "创建分配比例")
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-fpbl:create')")
    public CommonResult<Long> createCsFpbl(@Valid @RequestBody CsFpblSaveReqVO createReqVO) {
        return success(csFpblService.createCsFpbl(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分配比例")
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-fpbl:update')")
    public CommonResult<Boolean> updateCsFpbl(@Valid @RequestBody CsFpblSaveReqVO updateReqVO) {
        csFpblService.updateCsFpbl(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分配比例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-fpbl:delete')")
    public CommonResult<Boolean> deleteCsFpbl(@RequestParam("id") Long id) {
        csFpblService.deleteCsFpbl(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除分配比例")
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-fpbl:delete')")
    public CommonResult<Boolean> deleteCsFpblList(@RequestParam("ids") List<Long> ids) {
        csFpblService.deleteCsFpblListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分配比例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-fpbl:query')")
    public CommonResult<CsFpblResVO> getCsFpbl(@RequestParam("id") Long id) {
        CsFpblDO obj = csFpblService.getCsFpbl(id);
        return success(BeanUtils.toBean(obj, CsFpblResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分配比例分页")
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-fpbl:query')")
    public CommonResult<PageResult<CsFpblResVO>> getCsFpblPage(@Valid CsFpblPageReqVO pageReqVO) {
        PageResult<CsFpblDO> pageResult = csFpblService.getCsFpblPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CsFpblResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分配比例 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-fpbl:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCsFpblExcel(@Valid CsFpblPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CsFpblDO> list = csFpblService.getCsFpblPage(pageReqVO).getList();
        ExcelUtils.write(response, "分配比例.xls", "数据", CsFpblResVO.class,
                BeanUtils.toBean(list, CsFpblResVO.class));
    }
}
