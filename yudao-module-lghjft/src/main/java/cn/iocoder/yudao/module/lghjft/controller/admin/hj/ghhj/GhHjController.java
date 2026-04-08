package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjAllocationReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjVO;
import cn.iocoder.yudao.module.lghjft.service.hjgl.Xwgl.GhHjService;
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
import java.util.Map;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 户籍管理Controller
 *
 * @author lyb
 * @date 2026-03-26
 */
@Tag(name = "管理后台 - 户籍管理")
@RestController
@RequestMapping("/lghjft/hjgl")
@Validated
public class GhHjController {

    @Resource
    private GhHjService ghHjService;

    /**
     * 查询户籍管理列表（分页）
     */
    @GetMapping("/list")
    @Operation(summary = "查询户籍管理列表")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:query')")
    public CommonResult<PageResult<GhHjVO>> list(@Valid GhHjPageReqVO pageReqVO) {
        PageResult<GhHjVO> pageResult = ghHjService.selectGhHjPage(pageReqVO);
        return success(pageResult);
    }

    /**
     * 获取户籍管理详细信息
     */
    @GetMapping("/{djxh}")
    @Operation(summary = "获取户籍管理详细信息")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:query')")
    public CommonResult<GhHjVO> getInfo(@PathVariable String djxh) {
        return success(ghHjService.selectGhHjBydjxh(djxh));
    }

    /**
     * 新增户籍管理
     */
    @PostMapping("/save")
    @Operation(summary = "新增户籍管理")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:create')")
    public CommonResult<Boolean> save(@Valid @RequestBody GhHjSaveReqVO saveReqVO) {
        ghHjService.insertGhHj(saveReqVO);
        return success(true);
    }

    /**
     * 删除户籍管理
     */
    @DeleteMapping("/{djxhs}")
    @Operation(summary = "删除户籍管理")
    @Parameter(name = "djxhs", description = "登记序号数组", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:delete')")
    public CommonResult<Boolean> remove(@PathVariable String[] djxhs) {
        ghHjService.deleteGhHjBydjxhs(djxhs);
        return success(true);
    }

    /**
     * 导出户籍管理列表
     */
    @PostMapping("/export")
    @Operation(summary = "导出户籍管理列表")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void export(HttpServletResponse response, @Valid GhHjPageReqVO pageReqVO) throws IOException {
        List<GhHjVO> list = ghHjService.selectGhHjList(pageReqVO);
        ExcelUtils.write(response, "户籍管理数据.xls", "数据", GhHjVO.class, list);
    }

    /**
     * 批量复审 (v1: GET /fushenPl/{djxhs})
     */
    @GetMapping("/fushenPl/{djxhs}")
    @Operation(summary = "批量复审")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:update')")
    public CommonResult<Boolean> fushenPl(@PathVariable String[] djxhs) {
        ghHjService.fushenPlBydjxhs(java.util.Arrays.asList(djxhs));
        return success(true);
    }

    /**
     * 查询纳税人信息（新增户籍用）
     */
    @GetMapping("/getDjNsrxxInfo")
    @Operation(summary = "查询纳税人信息")
    @Parameter(name = "searchNsrKey", description = "纳税人关键字", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:query')")
    public CommonResult<Map<String, Object>> getDjNsrxxInfo(@RequestParam("searchNsrKey") String searchNsrKey) {
        Map<String, Object> result = ghHjService.getDjNsrxxInfo(searchNsrKey);
        return success(result);
    }

    /**
     * 查询纳税人信息（更新户籍用）
     */
    @GetMapping("/getDjNsrxxInfoForUpdateHj")
    @Operation(summary = "查询纳税人信息(更新用)")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:query')")
    public CommonResult<Map<String, Object>> getDjNsrxxInfoForUpdateHj(
            @RequestParam(required = false) String djxh,
            @RequestParam(required = false) String shxydm) {
        Map<String, Object> result = ghHjService.getDjNsrxxInfoForUpdateHj(djxh, shxydm);
        return success(result);
    }

    /**
     * 户籍调拨
     */
    @PostMapping("/allocation")
    @Operation(summary = "户籍调拨")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl:update')")
    public CommonResult<Boolean> allocation(@Valid @RequestBody GhHjAllocationReqVO reqVO) {
        ghHjService.allocationGhHj(reqVO);
        return success(true);
    }
}
