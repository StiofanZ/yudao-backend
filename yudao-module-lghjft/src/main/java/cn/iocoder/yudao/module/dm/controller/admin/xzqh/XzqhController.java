package cn.iocoder.yudao.module.dm.controller.admin.xzqh;

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

import cn.iocoder.yudao.module.dm.controller.admin.xzqh.vo.*;
import cn.iocoder.yudao.module.dm.dal.dataobject.xzqh.XzqhDO;
import cn.iocoder.yudao.module.dm.service.xzqh.XzqhService;

@Tag(name = "管理后台 - 行政区划")
@RestController
@RequestMapping("/dm/xzqh")
@Validated
public class XzqhController {

    @Resource
    private XzqhService xzqhService;
//
//    @PostMapping("/create")
//    @Operation(summary = "创建行政区划")
//    @PreAuthorize("@ss.hasPermission('dm:xzqh:create')")
//    public CommonResult<Long> createXzqh(@Valid @RequestBody XzqhSaveReqVO createReqVO) {
//        return success(xzqhService.createXzqh(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新行政区划")
//    @PreAuthorize("@ss.hasPermission('dm:xzqh:update')")
//    public CommonResult<Boolean> updateXzqh(@Valid @RequestBody XzqhSaveReqVO updateReqVO) {
//        xzqhService.updateXzqh(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除行政区划")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('dm:xzqh:delete')")
//    public CommonResult<Boolean> deleteXzqh(@RequestParam("id") Long id) {
//        xzqhService.deleteXzqh(id);
//        return success(true);
//    }
//
//
//    @GetMapping("/get")
//    @Operation(summary = "获得行政区划")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('dm:xzqh:query')")
//    public CommonResult<XzqhRespVO> getXzqh(@RequestParam("id") Long id) {
//        XzqhDO xzqh = xzqhService.getXzqh(id);
//        return success(BeanUtils.toBean(xzqh, XzqhRespVO.class));
//    }

    @GetMapping("/list")
    @Operation(summary = "获得行政区划列表")
    @PreAuthorize("@ss.hasPermission('dm:xzqh:query')")
    public CommonResult<List<XzqhRespVO>> getXzqhList(@Valid XzqhListReqVO listReqVO) {
        List<XzqhDO> list = xzqhService.getXzqhList(listReqVO);
        return success(BeanUtils.toBean(list, XzqhRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行政区划 Excel")
    @PreAuthorize("@ss.hasPermission('dm:xzqh:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXzqhExcel(@Valid XzqhListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<XzqhDO> list = xzqhService.getXzqhList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "行政区划.xls", "数据", XzqhRespVO.class,
                        BeanUtils.toBean(list, XzqhRespVO.class));
    }

}