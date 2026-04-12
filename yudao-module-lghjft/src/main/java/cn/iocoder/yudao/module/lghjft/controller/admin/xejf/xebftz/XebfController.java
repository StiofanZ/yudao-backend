package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xebftz.XebfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 小额缴费拨付台账")
@RestController
@RequestMapping("/lghjft/xejf/xebftz")
@Validated
public class XebfController {

    @Resource
    private XebfService xebfService;

    @PostMapping("/create")
    @Operation(summary = "创建小额缴费拨付台账")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:create')")
    public CommonResult<Long> create(@Valid @RequestBody XebfSaveReqVO createReqVO) {
        return success(xebfService.createXebf(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额缴费拨付台账")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody XebfSaveReqVO updateReqVO) {
        xebfService.updateXebf(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额缴费拨付台账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        xebfService.deleteXebf(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额缴费拨付台账")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        xebfService.deleteXebfList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额缴费拨付台账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:query')")
    public CommonResult<XebfResVO> get(@RequestParam("id") Long id) {
        return success(xebfService.getXebf(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额缴费拨付台账分页")
    @PreAuthorize("@ss.hasAnyPermissions('lghjft:xejf-xebftz:query', 'lghjft:xejf-hkxxxejf:query')")
    public CommonResult<PageResult<XebfResVO>> page(@Valid XebfPageReqVO pageReqVO) {
        return success(xebfService.getXebfPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额缴费拨付台账 Excel")
    @PreAuthorize("@ss.hasAnyPermissions('lghjft:xejf-xebftz:export', 'lghjft:xejf-hkxxxejf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXebfExcel(@Valid XebfPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XebfResVO> list = xebfService.getXebfPage(pageReqVO).getList();
        ExcelUtils.write(response, "小额缴费拨付台账.xls", "数据", XebfResVO.class, list);
    }

    /**
     * 查询小额拨付省总列表 (v1: listsz)
     */
    @GetMapping("/list-sz")
    @Operation(summary = "获得小额拨付省总列表")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:query')")
    public CommonResult<List<GhhkxxxejfszResVO>> listSz(@Valid GhhkxxxejfszReqVO reqVO) {
        List<GhhkxxxejfszResVO> list = xebfService.getGhHkxxxejfszList(reqVO.getJfqj());
        return success(list);
    }

    /**
     * 导出小额拨付省总 Excel (v1: exportsz)
     */
    @GetMapping("/export-excel-sz")
    @Operation(summary = "导出小额拨付省总 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSzExcel(@Valid GhhkxxxejfszReqVO reqVO,
                              HttpServletResponse response) throws IOException {
        List<GhhkxxxejfszResVO> list = xebfService.getGhHkxxxejfszList(reqVO.getJfqj());
        ExcelUtils.write(response, "小额缴费拨付省总.xls", "数据",
                GhhkxxxejfszResVO.class, list);
    }
}
