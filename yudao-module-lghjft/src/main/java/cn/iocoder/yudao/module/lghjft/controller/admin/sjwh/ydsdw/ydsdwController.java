package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw.vo.ydsdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw.vo.ydsdwRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw.vo.ydsdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.ydsdw.ydsdwDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.ydsdw.ydsdwService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

@Tag(name = "管理后台 - 应代收单位")
@RestController
@RequestMapping("/lghjft/ydsdw")
@Validated
public class ydsdwController {

    @Resource
    private ydsdwService ydsdwService;

    @PostMapping("/create")
    @Operation(summary = "创建应代收单位")
    @PreAuthorize("@ss.hasPermission('lghjft:ydsdw:create')")
    public CommonResult<Integer> createydsdw(@Valid @RequestBody ydsdwSaveReqVO createReqVO) {
        return success(ydsdwService.createydsdw(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应代收单位")
    @PreAuthorize("@ss.hasPermission('lghjft:ydsdw:update')")
    public CommonResult<Boolean> updateydsdw(@Valid @RequestBody ydsdwSaveReqVO updateReqVO) {
        ydsdwService.updateydsdw(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应代收单位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:ydsdw:delete')")
    public CommonResult<Boolean> deleteydsdw(@RequestParam("id") Integer id) {
        ydsdwService.deleteydsdw(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除应代收单位")
                @PreAuthorize("@ss.hasPermission('lghjft:ydsdw:delete')")
    public CommonResult<Boolean> deleteydsdwList(@RequestParam("ids") List<Integer> ids) {
        ydsdwService.deleteydsdwListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应代收单位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:ydsdw:query')")
    public CommonResult<ydsdwRespVO> getydsdw(@RequestParam("id") Integer id) {
        ydsdwDO ydsdw = ydsdwService.getydsdw(id);
        return success(BeanUtils.toBean(ydsdw, ydsdwRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应代收单位分页")
    @PreAuthorize("@ss.hasPermission('lghjft:ydsdw:query')")
    public CommonResult<PageResult<ydsdwRespVO>> getydsdwPage(@Valid ydsdwPageReqVO pageReqVO) {
        PageResult<ydsdwDO> pageResult = ydsdwService.getydsdwPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ydsdwRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应代收单位 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:ydsdw:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportydsdwExcel(@Valid ydsdwPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ydsdwDO> list = ydsdwService.getydsdwPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应代收单位.xls", "数据", ydsdwRespVO.class,
                        BeanUtils.toBean(list, ydsdwRespVO.class));
    }

}