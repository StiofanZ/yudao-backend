package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfbjs.JfclJfbjsDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.jfbjs.JfbjsService;
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

@Tag(name = "管理后台 - 经费补结算")
@RestController
@RequestMapping("/lghjft/jfcl/jfbjs")
@Validated
public class JfbjsController {

    @Resource
    private JfbjsService jfbjsService;

    @PostMapping("/create")
    @Operation(summary = "创建经费补结算")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:create')")
    public CommonResult<Long> createJfbjs(@Valid @RequestBody JfbjsSaveReqVO createReqVO) {
        return success(jfbjsService.createJfbjs(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经费补结算")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:update')")
    public CommonResult<Boolean> updateJfbjs(@Valid @RequestBody JfbjsSaveReqVO updateReqVO) {
        jfbjsService.updateJfbjs(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经费补结算")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:delete')")
    public CommonResult<Boolean> deleteJfbjs(@RequestParam("id") Long id) {
        jfbjsService.deleteJfbjs(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得经费补结算")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:query')")
    public CommonResult<JfbjsResVO> getJfbjs(@RequestParam("id") Long id) {
        JfclJfbjsDO data = jfbjsService.getJfbjs(id);
        return success(BeanUtils.toBean(data, JfbjsResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经费补结算分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:query')")
    public CommonResult<PageResult<JfbjsResVO>> getJfbjsPage(@Valid JfbjsPageReqVO pageReqVO) {
        PageResult<JfclJfbjsDO> pageResult = jfbjsService.getJfbjsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JfbjsResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费补结算 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:export')")
    public void exportJfbjsExcel(@Valid JfbjsPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfclJfbjsDO> list = jfbjsService.getJfbjsPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费补结算.xls", "数据", JfbjsResVO.class,
                BeanUtils.toBean(list, JfbjsResVO.class));
    }
}
