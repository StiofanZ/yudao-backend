package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzBatchReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjtz.CbjtzDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.cbjtz.CbjtzService;
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

@Tag(name = "管理后台 - 筹备金台账")
@RestController
@RequestMapping("/lghjft/sjwh/cbjtz")
@Validated
public class CbjtzController {

    @Resource
    private CbjtzService cbjtzService;

    @PostMapping("/create")
    @Operation(summary = "创建筹备金台账")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:create')")
    public CommonResult<Long> createCbjtz(@Valid @RequestBody CbjtzSaveReqVO createReqVO) {
        return success(cbjtzService.createCbjtz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新筹备金台账")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:update')")
    public CommonResult<Boolean> updateCbjtz(@Valid @RequestBody CbjtzSaveReqVO updateReqVO) {
        cbjtzService.updateCbjtz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除筹备金台账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:delete')")
    public CommonResult<Boolean> deleteCbjtz(@RequestParam("id") Long id) {
        cbjtzService.deleteCbjtz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除筹备金台账")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:delete')")
    public CommonResult<Boolean> deleteCbjtzList(@RequestParam("ids") List<Long> ids) {
        cbjtzService.deleteCbjtzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得筹备金台账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:query')")
    public CommonResult<CbjtzResVO> getCbjtz(@RequestParam("id") Long id) {
        CbjtzDO cbjtz = cbjtzService.getCbjtz(id);
        return success(BeanUtils.toBean(cbjtz, CbjtzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得筹备金台账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:query')")
    public CommonResult<PageResult<CbjtzResVO>> getCbjtzPage(@Valid CbjtzPageReqVO pageReqVO) {
        PageResult<CbjtzDO> pageResult = cbjtzService.getCbjtzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CbjtzResVO.class));
    }

    @GetMapping("/page-dgjftz")
    @Operation(summary = "代管经费台账明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:query')")
    public CommonResult<PageResult<CbjtzResVO>> getDgjftzPage(@Valid CbjtzPageReqVO pageReqVO) {
        PageResult<CbjtzDO> pageResult = cbjtzService.getDgjftzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CbjtzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金台账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjtzExcel(@Valid CbjtzPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CbjtzDO> list = cbjtzService.getCbjtzPage(pageReqVO).getList();
        ExcelUtils.write(response, "筹备金台账.xls", "数据", CbjtzResVO.class,
                BeanUtils.toBean(list, CbjtzResVO.class));
    }

    @PostMapping("/batch-cbjqrfbpl")
    @Operation(summary = "批量确认返拨")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjtz:update')")
    public CommonResult<Boolean> batchCbjqrfbpl(@Valid @RequestBody List<CbjtzBatchReqVO> records) {
        cbjtzService.batchCbjqrfbpl(records);
        return success(true);
    }
}
