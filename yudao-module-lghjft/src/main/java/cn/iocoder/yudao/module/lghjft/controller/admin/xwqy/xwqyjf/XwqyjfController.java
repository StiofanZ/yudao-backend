package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;
import cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjf.XwqyjfService;
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

@Tag(name = "管理后台 - 小微企业经费明细")
@RestController
@RequestMapping("/lghjft/xwqy/xwqyjf")
@Validated
public class XwqyjfController {

    @Resource
    private XwqyjfService xwqyjfService;

    @PostMapping("/create")
    @Operation(summary = "创建小微企业经费明细")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:create')")
    public CommonResult<Long> createXwqyjf(@Valid @RequestBody XwqyjfSaveReqVO createReqVO) {
        return success(xwqyjfService.createXwqyjf(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小微企业经费明细")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:update')")
    public CommonResult<Boolean> updateXwqyjf(@Valid @RequestBody XwqyjfSaveReqVO updateReqVO) {
        xwqyjfService.updateXwqyjf(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小微企业经费明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:delete')")
    public CommonResult<Boolean> deleteXwqyjf(@RequestParam("id") Long id) {
        xwqyjfService.deleteXwqyjf(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除小微企业经费明细")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:delete')")
    public CommonResult<Boolean> deleteXwqyjfList(@RequestParam("ids") List<Long> ids) {
        xwqyjfService.deleteXwqyjfListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小微企业经费明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:query')")
    public CommonResult<XwqyjfDO> getXwqyjf(@RequestParam("id") Long id) {
        return success(xwqyjfService.getXwqyjf(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小微企业经费95%明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:query')")
    public CommonResult<PageResult<XwqyjfDO>> getXwqyjfPage(@Valid XwqyjfPageReqVO pageReqVO) {
        return success(xwqyjfService.getXwqyjfPage(pageReqVO));
    }

    @GetMapping("/page-yf")
    @Operation(summary = "获得小微企业经费60%明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:query')")
    public CommonResult<PageResult<XwqyjfDO>> getXwqyjfPageYf(@Valid XwqyjfPageReqVO pageReqVO) {
        return success(xwqyjfService.getXwqyjfPageYf(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小微企业经费95%明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyjfExcel(@Valid XwqyjfPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        List<XwqyjfDO> list = xwqyjfService.getXwqyjfList(pageReqVO);
        ExcelUtils.write(response, "小微企业经费95%明细.xls", "数据", XwqyjfResVO.class,
                BeanUtils.toBean(list, XwqyjfResVO.class));
    }

    @GetMapping("/export-excel-yf")
    @Operation(summary = "导出小微企业经费60%明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqyjf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyjfYfExcel(@Valid XwqyjfPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setOffset(null); // no pagination for export
        List<XwqyjfDO> list = xwqyjfService.getXwqyjfPageYf(pageReqVO).getList();
        ExcelUtils.write(response, "小微企业经费60%明细.xls", "数据", XwqyjfResVO.class,
                BeanUtils.toBean(list, XwqyjfResVO.class));
    }
}
