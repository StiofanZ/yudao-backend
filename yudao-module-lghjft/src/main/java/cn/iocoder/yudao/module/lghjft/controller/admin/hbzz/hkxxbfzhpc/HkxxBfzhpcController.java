package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo.HkxxBfzhpcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo.HkxxBfzhpcResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo.HkxxBfzhpcSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.hkxxbfzhpc.HkxxBfzhpcService;
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

@Tag(name = "管理后台 - 拨付信息排除解除")
@RestController
@RequestMapping("/lghjft/hkxx-bfzhpc")
@Validated
public class HkxxBfzhpcController {

    @Resource
    private HkxxBfzhpcService hkxxBfzhpcService;

    // ========== 账户排除（v1 /sjwh/bfzhpc/list） ==========

    @GetMapping("/page")
    @Operation(summary = "账户排除分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:query')")
    public CommonResult<PageResult<HkxxBfzhpcResVO>> getBfzhpcPage(@Valid HkxxBfzhpcPageReqVO pageReqVO) {
        return success(hkxxBfzhpcService.getBfzhpcPage(pageReqVO));
    }

    @GetMapping("/jcghzh/{jcghzh}")
    @Operation(summary = "按基层工会账号查账户排除详情")
    @Parameter(name = "jcghzh", description = "基层工会账号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:query')")
    public CommonResult<HkxxBfzhpcResVO> getByJcghzh(@PathVariable String jcghzh) {
        return success(hkxxBfzhpcService.getHkxxBfzhpcByJcghzh(jcghzh));
    }

    @PutMapping("/update")
    @Operation(summary = "更新账户排除信息")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:update')")
    public CommonResult<Boolean> updateBfzhpc(@Valid @RequestBody HkxxBfzhpcSaveReqVO updateReqVO) {
        hkxxBfzhpcService.updateBfzhpc(updateReqVO);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出账户排除 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBfzhpcExcel(@Valid HkxxBfzhpcPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        List<HkxxBfzhpcResVO> list = hkxxBfzhpcService.getBfzhpcList(pageReqVO);
        ExcelUtils.write(response, "拨付账户排除解除.xls", "数据", HkxxBfzhpcResVO.class, list);
    }

    // ========== 单位排除（v1 /sjwh/bfzhpc/listbfdw） ==========

    @GetMapping("/page-bfdw")
    @Operation(summary = "单位排除分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:query')")
    public CommonResult<PageResult<HkxxBfzhpcResVO>> getBfdwPage(@Valid HkxxBfzhpcPageReqVO pageReqVO) {
        return success(hkxxBfzhpcService.getBfdwPage(pageReqVO));
    }

    @GetMapping("/djxh/{djxh}")
    @Operation(summary = "按登记序号查单位排除详情")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:query')")
    public CommonResult<HkxxBfzhpcResVO> getBfdwByDjxh(@PathVariable String djxh) {
        return success(hkxxBfzhpcService.getBfdwpcByDjxh(djxh));
    }

    @PutMapping("/bfdw")
    @Operation(summary = "更新单位排除信息")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:update')")
    public CommonResult<Boolean> updateBfdwpc(@Valid @RequestBody HkxxBfzhpcSaveReqVO updateReqVO) {
        hkxxBfzhpcService.updateBfdwpc(updateReqVO);
        return success(true);
    }

    @PostMapping("/exportdw")
    @Operation(summary = "导出单位排除 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBfdwExcel(@Valid HkxxBfzhpcPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        List<HkxxBfzhpcResVO> list = hkxxBfzhpcService.getBfdwList(pageReqVO);
        ExcelUtils.write(response, "拨付单位排除解除.xls", "数据", HkxxBfzhpcResVO.class, list);
    }

    // ========== 经费排除（v1 /sjwh/bfzhpc/listbfjf） ==========

    @GetMapping("/page-bfjf")
    @Operation(summary = "经费排除分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:query')")
    public CommonResult<PageResult<HkxxBfzhpcResVO>> getBfjfPage(@Valid HkxxBfzhpcPageReqVO pageReqVO) {
        return success(hkxxBfzhpcService.getBfjfPage(pageReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "按 spuuid 查经费排除详情")
    @Parameter(name = "spuuid", description = "税票UUID", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:query')")
    public CommonResult<HkxxBfzhpcResVO> getHkxxBfzhpc(@RequestParam("spuuid") String spuuid) {
        return success(hkxxBfzhpcService.getHkxxBfzhpc(spuuid));
    }

    @PutMapping("/update-bfjf")
    @Operation(summary = "更新经费排除信息")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:update')")
    public CommonResult<Boolean> updateHkxxBfzhpc(@Valid @RequestBody HkxxBfzhpcSaveReqVO updateReqVO) {
        hkxxBfzhpcService.updateHkxxBfzhpc(updateReqVO);
        return success(true);
    }

    @PostMapping("/exportjf")
    @Operation(summary = "导出经费排除 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBfjfExcel(@Valid HkxxBfzhpcPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        List<HkxxBfzhpcResVO> list = hkxxBfzhpcService.getBfjfList(pageReqVO);
        ExcelUtils.write(response, "拨付经费排除解除.xls", "数据", HkxxBfzhpcResVO.class, list);
    }

    // ========== 通用操作 ==========

    @PostMapping("/create")
    @Operation(summary = "新增拨付排除")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:create')")
    public CommonResult<Boolean> createHkxxBfzhpc(@Valid @RequestBody HkxxBfzhpcSaveReqVO createReqVO) {
        hkxxBfzhpcService.createHkxxBfzhpc(createReqVO);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除拨付信息")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-bfzhpc:delete')")
    public CommonResult<Boolean> deleteHkxxBfzhpcList(@RequestParam("ids") List<Integer> ids) {
        hkxxBfzhpcService.deleteHkxxBfzhpcListByIds(ids);
        return success(true);
    }
}
