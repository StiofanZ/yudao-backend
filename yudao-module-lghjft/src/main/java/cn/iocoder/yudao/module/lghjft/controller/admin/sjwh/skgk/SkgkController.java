package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo.SkgkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo.SkgkRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo.SkgkSaveReqVO;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.skgk.SkgkDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.skgk.SkgkService;
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

@Tag(name = "管理后台 - 收款国库")
@RestController
@RequestMapping("/dm/skgk")
@Validated
public class SkgkController {

    @Resource
    private SkgkService skgkService;

    @PostMapping("/create")
    @Operation(summary = "创建收款国库")
    @PreAuthorize("@ss.hasPermission('dm:skgk:create')")
    public CommonResult<Integer> createSkgk(@Valid @RequestBody SkgkSaveReqVO createReqVO) {
        return success(skgkService.createSkgk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收款国库")
    @PreAuthorize("@ss.hasPermission('dm:skgk:update')")
    public CommonResult<Boolean> updateSkgk(@Valid @RequestBody SkgkSaveReqVO updateReqVO) {
        skgkService.updateSkgk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收款国库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('dm:skgk:delete')")
    public CommonResult<Boolean> deleteSkgk(@RequestParam("id") Integer id) {
        skgkService.deleteSkgk(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除收款国库")
                @PreAuthorize("@ss.hasPermission('dm:skgk:delete')")
    public CommonResult<Boolean> deleteSkgkList(@RequestParam("ids") List<Integer> ids) {
        skgkService.deleteSkgkListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收款国库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('dm:skgk:query')")
    public CommonResult<SkgkRespVO> getSkgk(@RequestParam("id") Integer id) {
        SkgkDO skgk = skgkService.getSkgk(id);
        return success(BeanUtils.toBean(skgk, SkgkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收款国库分页")
    @PreAuthorize("@ss.hasPermission('dm:skgk:query')")
    public CommonResult<PageResult<SkgkRespVO>> getSkgkPage(@Valid SkgkPageReqVO pageReqVO) {
        PageResult<SkgkDO> pageResult = skgkService.getSkgkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SkgkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收款国库 Excel")
    @PreAuthorize("@ss.hasPermission('dm:skgk:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSkgkExcel(@Valid SkgkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SkgkDO> list = skgkService.getSkgkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "收款国库.xls", "数据", SkgkRespVO.class,
                        BeanUtils.toBean(list, SkgkRespVO.class));
    }

}