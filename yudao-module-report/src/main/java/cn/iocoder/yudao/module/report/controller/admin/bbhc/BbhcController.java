package cn.iocoder.yudao.module.report.controller.admin.bbhc;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.report.controller.admin.bbhc.vo.BbhcPageReqVO;
import cn.iocoder.yudao.module.report.controller.admin.bbhc.vo.BbhcRespVO;
import cn.iocoder.yudao.module.report.service.bbhc.BbhcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 报表快照")
@RestController
@RequestMapping("/report/hc")
@Validated
public class BbhcController {

    @Resource
    private BbhcService bbhcService;

    @GetMapping("/page")
    @Operation(summary = "获得当前快照分页")
    @PreAuthorize("@ss.hasPermission('report:bbhc:query')")
    public CommonResult<PageResult<BbhcRespVO>> getDqPage(@Valid BbhcPageReqVO reqVO) {
        return success(bbhcService.getDqPage(reqVO));
    }

    @GetMapping("/ls-page")
    @Operation(summary = "获得历史快照分页")
    @PreAuthorize("@ss.hasPermission('report:bbhc:query')")
    public CommonResult<PageResult<BbhcRespVO>> getLsPage(@Valid BbhcPageReqVO reqVO) {
        return success(bbhcService.getLsPage(reqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得当前快照详情")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('report:bbhc:query')")
    public CommonResult<BbhcRespVO> getDq(@RequestParam("id") Long id) {
        return success(bbhcService.getDq(id));
    }

    @GetMapping("/get-ls")
    @Operation(summary = "获得历史快照详情")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('report:bbhc:query')")
    public CommonResult<BbhcRespVO> getLs(@RequestParam("id") Long id) {
        return success(bbhcService.getLs(id));
    }

    @PostMapping("/cqsc")
    @Operation(summary = "重生成当前快照")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('report:bbhc:update')")
    public CommonResult<Boolean> cqsc(@RequestParam("id") Long id, HttpServletRequest request) {
        bbhcService.cqscDq(id, request);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "清理当前快照")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('report:bbhc:delete')")
    public CommonResult<Boolean> scDq(@RequestParam("id") Long id) {
        bbhcService.scDq(id);
        return success(true);
    }

    @DeleteMapping("/delete-ls")
    @Operation(summary = "清理历史快照")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('report:bbhc:delete')")
    public CommonResult<Boolean> scLs(@RequestParam("id") Long id) {
        bbhcService.scLs(id);
        return success(true);
    }
}
