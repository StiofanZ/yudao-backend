package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023ResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023SaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.XejftjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf2023.Xejf2023DO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xejf2023.Xejf2023Service;
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

@Tag(name = "管理后台 - 小额缴费明细")
@RestController
@RequestMapping("/lghjft/xejf/xejf2023")
@Validated
public class Xejf2023Controller {

    @Resource
    private Xejf2023Service xejf2023Service;

    @PostMapping("/create")
    @Operation(summary = "创建小额缴费明细")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:create')")
    public CommonResult<Long> create(@Valid @RequestBody Xejf2023SaveReqVO createReqVO) {
        return success(xejf2023Service.createXejf2023(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额缴费明细")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody Xejf2023SaveReqVO updateReqVO) {
        xejf2023Service.updateXejf2023(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额缴费明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        xejf2023Service.deleteXejf2023(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额缴费明细")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        xejf2023Service.deleteXejf2023ListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额缴费明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:query')")
    public CommonResult<Xejf2023ResVO> get(@RequestParam("id") Long id) {
        Xejf2023DO data = xejf2023Service.getXejf2023(id);
        return success(BeanUtils.toBean(data, Xejf2023ResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额缴费明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:query')")
    public CommonResult<PageResult<Xejf2023ResVO>> page(@Valid Xejf2023PageReqVO pageReqVO) {
        PageResult<Xejf2023ResVO> pageResult = xejf2023Service.getXejf2023Page(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/page-tz")
    @Operation(summary = "小额缴费台账")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:query')")
    public CommonResult<List<Xejf2023ResVO>> getXejf2023PageTz(@Valid Xejf2023PageReqVO pageReqVO) {
        List<Xejf2023DO> list = xejf2023Service.getXejf2023PageTz(pageReqVO);
        return success(BeanUtils.toBean(list, Xejf2023ResVO.class));
    }

    @GetMapping("/page-xetj")
    @Operation(summary = "小额缴费统计")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:query')")
    public CommonResult<List<XejftjResVO>> getXejf2023Xetj(@Valid Xejf2023PageReqVO pageReqVO) {
        return success(xejf2023Service.getXejf2023Xetj(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额缴费明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid Xejf2023PageReqVO pageReqVO,
                            HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Xejf2023ResVO> list = xejf2023Service.getXejf2023Page(pageReqVO).getList();
        ExcelUtils.write(response, "小额缴费明细.xls", "数据", Xejf2023ResVO.class, list);
    }

    @GetMapping("/export-xetj")
    @Operation(summary = "导出小额缴费统计 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejf2023:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXetjExcel(@Valid Xejf2023PageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        List<XejftjResVO> list = xejf2023Service.getXejf2023Xetj(pageReqVO);
        ExcelUtils.write(response, "小额缴费统计.xlsx", "数据", XejftjResVO.class, list);
    }
}
