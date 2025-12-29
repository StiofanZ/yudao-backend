package cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq;

import cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqPageReqVO;
import cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqRespVO;
import cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqSaveReqVO;
import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqDO;
import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqmxDO;
import cn.gszs.yudao.module.lghjft.service.wfsqtfsq.WfSqTfsqService;
import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
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

@Tag(name = "管理后台 - 申请-退费申请")
@RestController
@RequestMapping("/lghjft/wf-sq-tfsq")
@Validated
public class WfSqTfsqController {

    @Resource
    private WfSqTfsqService wfSqTfsqService;

    @PostMapping("/create")
    @Operation(summary = "创建申请-退费申请")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-sq-tfsq:create')")
    public CommonResult<Long> createWfSqTfsq(@Valid @RequestBody WfSqTfsqSaveReqVO createReqVO) {
        return success(wfSqTfsqService.createWfSqTfsq(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新申请-退费申请")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-sq-tfsq:update')")
    public CommonResult<Boolean> updateWfSqTfsq(@Valid @RequestBody WfSqTfsqSaveReqVO updateReqVO) {
        wfSqTfsqService.updateWfSqTfsq(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除申请-退费申请")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:wf-sq-tfsq:delete')")
    public CommonResult<Boolean> deleteWfSqTfsq(@RequestParam("id") Long id) {
        wfSqTfsqService.deleteWfSqTfsq(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除申请-退费申请")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-sq-tfsq:delete')")
    public CommonResult<Boolean> deleteWfSqTfsqList(@RequestParam("ids") List<Long> ids) {
        wfSqTfsqService.deleteWfSqTfsqListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得申请-退费申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-sq-tfsq:query')")
    public CommonResult<WfSqTfsqRespVO> getWfSqTfsq(@RequestParam("id") Long id) {
        WfSqTfsqDO wfSqTfsq = wfSqTfsqService.getWfSqTfsq(id);
        return success(BeanUtils.toBean(wfSqTfsq, WfSqTfsqRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得申请-退费申请分页")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-sq-tfsq:query')")
    public CommonResult<PageResult<WfSqTfsqRespVO>> getWfSqTfsqPage(@Valid WfSqTfsqPageReqVO pageReqVO) {
        PageResult<WfSqTfsqDO> pageResult = wfSqTfsqService.getWfSqTfsqPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WfSqTfsqRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出申请-退费申请 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-sq-tfsq:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWfSqTfsqExcel(@Valid WfSqTfsqPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WfSqTfsqDO> list = wfSqTfsqService.getWfSqTfsqPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "申请-退费申请.xls", "数据", WfSqTfsqRespVO.class,
                BeanUtils.toBean(list, WfSqTfsqRespVO.class));
    }

    // ==================== 子表（申请-退费申请明细） ====================

    @GetMapping("/wf-sq-tfsqmx/list-by-id")
    @Operation(summary = "获得申请-退费申请明细列表")
    @Parameter(name = "id", description = "退费申请明细ID")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-sq-tfsq:query')")
    public CommonResult<List<WfSqTfsqmxDO>> getWfSqTfsqmxListById(@RequestParam("id") Long id) {
        return success(wfSqTfsqService.getWfSqTfsqmxListById(id));
    }

}