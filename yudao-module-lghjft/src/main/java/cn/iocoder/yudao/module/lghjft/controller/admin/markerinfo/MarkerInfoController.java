package cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo;

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

import cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.markerinfo.MarkerInfoDO;
import cn.iocoder.yudao.module.lghjft.service.markerinfo.MarkerInfoService;

@Tag(name = "管理后台 - 高德地图标注点信息")
@RestController
@RequestMapping("/lghjft/marker-info")
@Validated
public class MarkerInfoController {

    @Resource
    private MarkerInfoService markerInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建高德地图标注点信息")
    @PreAuthorize("@ss.hasPermission('lghjft:marker-info:create')")
    public CommonResult<Long> createMarkerInfo(@Valid @RequestBody MarkerInfoSaveReqVO createReqVO) {
        return success(markerInfoService.createMarkerInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新高德地图标注点信息")
    @PreAuthorize("@ss.hasPermission('lghjft:marker-info:update')")
    public CommonResult<Boolean> updateMarkerInfo(@Valid @RequestBody MarkerInfoSaveReqVO updateReqVO) {
        markerInfoService.updateMarkerInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除高德地图标注点信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:marker-info:delete')")
    public CommonResult<Boolean> deleteMarkerInfo(@RequestParam("id") Long id) {
        markerInfoService.deleteMarkerInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除高德地图标注点信息")
                @PreAuthorize("@ss.hasPermission('lghjft:marker-info:delete')")
    public CommonResult<Boolean> deleteMarkerInfoList(@RequestParam("ids") List<Long> ids) {
        markerInfoService.deleteMarkerInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得高德地图标注点信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<MarkerInfoRespVO> getMarkerInfo(@RequestParam("id") Long id) {
        MarkerInfoDO markerInfo = markerInfoService.getMarkerInfo(id);
        return success(BeanUtils.toBean(markerInfo, MarkerInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得高德地图标注点信息分页")
    public CommonResult<PageResult<MarkerInfoRespVO>> getMarkerInfoPage(@Valid MarkerInfoPageReqVO pageReqVO) {
        PageResult<MarkerInfoDO> pageResult = markerInfoService.getMarkerInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MarkerInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出高德地图标注点信息 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:marker-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMarkerInfoExcel(@Valid MarkerInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MarkerInfoDO> list = markerInfoService.getMarkerInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "高德地图标注点信息.xls", "数据", MarkerInfoRespVO.class,
                        BeanUtils.toBean(list, MarkerInfoRespVO.class));
    }

}