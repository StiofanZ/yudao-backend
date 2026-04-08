package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.GhJfTsjfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.TsjfclDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.tsjfcl.TsjfclService;
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

@Tag(name = "管理后台 - 特殊经费处理")
@RestController
@RequestMapping("/lghjft/jfcl/tsjfcl")
@Validated
public class TsjfclController {

    @Resource
    private TsjfclService tsjfclService;

    // --- 1. GET /page (v1: GET /list) ---
    @GetMapping("/page")
    @Operation(summary = "获得特殊经费处理分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcl:query')")
    public CommonResult<PageResult<TsjfclResVO>> getTsjfclPage(@Valid TsjfclPageReqVO pageReqVO) {
        PageResult<TsjfclDO> pageResult = tsjfclService.getTsjfclPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TsjfclResVO.class));
    }

    // --- 2. GET /export-excel (v1: POST /export) ---
    @GetMapping("/export-excel")
    @Operation(summary = "导出特殊经费处理 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcl:export')")
    public void exportTsjfclExcel(@Valid TsjfclPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TsjfclDO> list = tsjfclService.getTsjfclPage(pageReqVO).getList();
        ExcelUtils.write(response, "特殊经费处理.xls", "数据", TsjfclResVO.class,
                BeanUtils.toBean(list, TsjfclResVO.class));
    }

    // --- 3. GET /get (v1: GET /{ghjfId} — detail with child records) ---
    @GetMapping("/get")
    @Operation(summary = "获得特殊经费处理详细信息（含子表）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcl:query')")
    public CommonResult<TsjfclResVO> getTsjfcl(@RequestParam("id") Long id) {
        TsjfclDO data = tsjfclService.getTsjfcl(id);
        return success(BeanUtils.toBean(data, TsjfclResVO.class));
    }

    // --- 4. POST /create (v1: POST — add with child insert) ---
    @PostMapping("/create")
    @Operation(summary = "创建特殊经费处理")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcl:create')")
    public CommonResult<Long> createTsjfcl(@Valid @RequestBody TsjfclSaveReqVO createReqVO) {
        return success(tsjfclService.createTsjfcl(createReqVO));
    }

    // --- 5. PUT /update (v1: PUT — edit, transactional delete+insert child) ---
    @PutMapping("/update")
    @Operation(summary = "更新特殊经费处理")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcl:update')")
    public CommonResult<Boolean> updateTsjfcl(@Valid @RequestBody TsjfclSaveReqVO updateReqVO) {
        tsjfclService.updateTsjfcl(updateReqVO);
        return success(true);
    }

    // --- 6. DELETE /delete (v1: DELETE /{ghjfIds} — batch delete cascade) ---
    @DeleteMapping("/delete")
    @Operation(summary = "删除特殊经费处理（支持批量，级联删除子表）")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcl:delete')")
    public CommonResult<Boolean> deleteTsjfcl(@RequestParam("ids") Long[] ids) {
        tsjfclService.deleteTsjfclByIds(ids);
        return success(true);
    }

    // --- 7. POST /batchTsjfclpl (v1: POST /batchTsjfclpl — batch process special funds) ---
    @PostMapping("/batchTsjfclpl")
    @Operation(summary = "特殊经费批量处理")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcl:update')")
    public CommonResult<Boolean> batchTsjfclpl(@RequestBody List<GhJfTsjfDO> selectedRecords) {
        tsjfclService.batchTsjfclpl(selectedRecords);
        return success(true);
    }
}
