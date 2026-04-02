package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs.JfclJfjsDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.jfjs.JfjsService;
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

@Tag(name = "管理后台 - 经费结算")
@RestController
@RequestMapping("/lghjft/jfcl/jfjs")
@Validated
public class JfjsController {

    @Resource
    private JfjsService jfjsService;

    @PostMapping("/create")
    @Operation(summary = "创建经费结算")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfjs:create')")
    public CommonResult<Long> createJfjs(@Valid @RequestBody JfjsSaveReqVO createReqVO) {
        return success(jfjsService.createJfjs(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经费结算")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfjs:update')")
    public CommonResult<Boolean> updateJfjs(@Valid @RequestBody JfjsSaveReqVO updateReqVO) {
        jfjsService.updateJfjs(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经费结算")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfjs:delete')")
    public CommonResult<Boolean> deleteJfjs(@RequestParam("id") Long id) {
        jfjsService.deleteJfjs(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得经费结算")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfjs:query')")
    public CommonResult<JfjsResVO> getJfjs(@RequestParam("id") Long id) {
        JfclJfjsDO data = jfjsService.getJfjs(id);
        return success(BeanUtils.toBean(data, JfjsResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经费结算分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfjs:query')")
    public CommonResult<PageResult<JfjsResVO>> getJfjsPage(@Valid JfjsPageReqVO pageReqVO) {
        PageResult<JfclJfjsDO> pageResult = jfjsService.getJfjsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JfjsResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费结算 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfjs:export')")
    public void exportJfjsExcel(@Valid JfjsPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfclJfjsDO> list = jfjsService.getJfjsPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费结算.xls", "数据", JfjsResVO.class,
                BeanUtils.toBean(list, JfjsResVO.class));
    }
}
