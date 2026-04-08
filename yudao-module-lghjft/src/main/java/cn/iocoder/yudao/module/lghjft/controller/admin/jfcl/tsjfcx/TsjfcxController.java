package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcx.TsjfcxDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.tsjfcx.TsjfcxService;
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

@Tag(name = "管理后台 - 特殊经费查询")
@RestController
@RequestMapping("/lghjft/jfcl/tsjfcx")
@Validated
public class TsjfcxController {

    @Resource
    private TsjfcxService tsjfcxService;

    // --- 1. GET /page (v1: GET /list) ---
    @GetMapping("/page")
    @Operation(summary = "获得特殊经费查询分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcx:query')")
    public CommonResult<PageResult<TsjfcxResVO>> getTsjfcxPage(@Valid TsjfcxPageReqVO pageReqVO) {
        PageResult<TsjfcxDO> pageResult = tsjfcxService.getTsjfcxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TsjfcxResVO.class));
    }

    // --- 2. GET /export-excel (v1: POST /export) ---
    @GetMapping("/export-excel")
    @Operation(summary = "导出特殊经费查询 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcx:export')")
    public void exportTsjfcxExcel(@Valid TsjfcxPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TsjfcxDO> list = tsjfcxService.getTsjfcxPage(pageReqVO).getList();
        ExcelUtils.write(response, "特殊经费查询.xls", "数据", TsjfcxResVO.class,
                BeanUtils.toBean(list, TsjfcxResVO.class));
    }

    // --- 3. GET /get (v1: GET /{ghjfId} — detail with child gh_jf_tsjf records) ---
    @GetMapping("/get")
    @Operation(summary = "获得特殊经费查询详细信息（含子表）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcx:query')")
    public CommonResult<TsjfcxResVO> getTsjfcx(@RequestParam("id") Long id) {
        TsjfcxDO data = tsjfcxService.getTsjfcx(id);
        return success(BeanUtils.toBean(data, TsjfcxResVO.class));
    }

    // --- 4. POST /create (v1: POST — add with child insert) ---
    @PostMapping("/create")
    @Operation(summary = "创建特殊经费查询")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcx:create')")
    public CommonResult<Long> createTsjfcx(@Valid @RequestBody TsjfcxSaveReqVO createReqVO) {
        return success(tsjfcxService.createTsjfcx(createReqVO));
    }

    // --- 5. PUT /update (v1: PUT — edit, transactional delete+insert child) ---
    @PutMapping("/update")
    @Operation(summary = "更新特殊经费查询")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcx:update')")
    public CommonResult<Boolean> updateTsjfcx(@Valid @RequestBody TsjfcxSaveReqVO updateReqVO) {
        tsjfcxService.updateTsjfcx(updateReqVO);
        return success(true);
    }

    // --- 6. DELETE /delete (v1: DELETE /{ghjfIds} — batch delete cascade) ---
    @DeleteMapping("/delete")
    @Operation(summary = "删除特殊经费查询（支持批量，级联删除子表）")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-tsjfcx:delete')")
    public CommonResult<Boolean> deleteTsjfcx(@RequestParam("ids") Long[] ids) {
        tsjfcxService.deleteTsjfcxByIds(ids);
        return success(true);
    }
}
