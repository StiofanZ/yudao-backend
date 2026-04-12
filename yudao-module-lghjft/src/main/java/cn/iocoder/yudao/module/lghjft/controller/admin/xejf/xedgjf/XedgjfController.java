package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfDeptScopeResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xedgjf.XedgjfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 小额代管经费做账")
@RestController
@RequestMapping("/lghjft/xejf/xedgjf")
@Validated
public class XedgjfController {

    @Resource
    private XedgjfService xedgjfService;

    @GetMapping("/get")
    @Operation(summary = "获得小额代管经费做账详情")
    @Parameter(name = "id", description = "划款信息ID", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xedgjf:query')")
    public CommonResult<XedgjfResVO> get(@RequestParam("id") Long id) {
        return success(xedgjfService.getXedgjf(id));
    }

    @GetMapping("/dept-scope")
    @Operation(summary = "获得小额代管经费做账部门查询范围")
    @Parameter(name = "deptId", description = "部门 ID", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xedgjf:query')")
    public CommonResult<XedgjfDeptScopeResVO> getDeptScope(@RequestParam("deptId") Long deptId) {
        return success(xedgjfService.getDeptScope(deptId));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额代管经费做账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xedgjf:query')")
    public CommonResult<PageResult<XedgjfResVO>> page(@Valid XedgjfPageReqVO pageReqVO) {
        return success(xedgjfService.getXedgjfPage(pageReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额代管经费做账")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xedgjf:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody XedgjfSaveReqVO updateReqVO) {
        xedgjfService.updateXedgjf(updateReqVO);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额代管经费做账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xedgjf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid XedgjfPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XedgjfResVO> list = xedgjfService.getXedgjfPage(pageReqVO).getList();
        ExcelUtils.write(response, "小额代管经费做账.xls", "数据", XedgjfResVO.class, list);
    }
}
