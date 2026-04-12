package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JftzmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JftzmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.SzdzhdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.SzdzhdResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx.CxtjJfmxDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jfmx.CxtjJfmxService;
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

@Tag(name = "管理后台 - 经费明细(查询统计)")
@RestController("cxtjJfmxController")
@RequestMapping("/lghjft/cxtj/jfmx")
@Validated
public class JfmxController {

    @Resource
    private CxtjJfmxService cxtjJfmxService;

    @GetMapping("/get")
    @Operation(summary = "获得经费明细")
    @Parameter(name = "spuuid", description = "spuuid", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:query')")
    public CommonResult<JfmxResVO> getJfmx(@RequestParam("spuuid") String spuuid) {
        CxtjJfmxDO obj = cxtjJfmxService.getJfmx(spuuid);
        return success(BeanUtils.toBean(obj, JfmxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经费明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:query')")
    public CommonResult<PageResult<JfmxResVO>> getJfmxPage(@Valid JfmxPageReqVO pageReqVO) {
        return success(cxtjJfmxService.getJfmxPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJfmxExcel(@Valid JfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfmxResVO> list = cxtjJfmxService.getJfmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费明细.xls", "数据", JfmxResVO.class, list);
    }

    // ==================== 经费台账明细 (V1 /listmx + /tzexport) ====================

    @GetMapping("/listmx")
    @Operation(summary = "获得经费台账明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:query')")
    public CommonResult<PageResult<JftzmxResVO>> getJftzmxPage(@Valid JftzmxPageReqVO pageReqVO) {
        return success(cxtjJfmxService.getJftzmxPage(pageReqVO));
    }

    @GetMapping("/tzexport")
    @Operation(summary = "导出经费台账明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJftzmxExcel(@Valid JftzmxPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JftzmxResVO> list = cxtjJfmxService.getJftzmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费台账明细.xls", "数据", JftzmxResVO.class, list);
    }

    // ==================== 省总到账核对 (V1 /listszdzhd + /szdzhdexport) ====================

    @GetMapping("/listszdzhd")
    @Operation(summary = "获得省总到账核对分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:query')")
    public CommonResult<PageResult<SzdzhdResVO>> getSzdzhdPage(@Valid SzdzhdPageReqVO pageReqVO) {
        return success(cxtjJfmxService.getSzdzhdPage(pageReqVO));
    }

    @GetMapping("/szdzhdexport")
    @Operation(summary = "导出省总到账核对 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSzdzhdExcel(@Valid SzdzhdPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SzdzhdResVO> list = cxtjJfmxService.getSzdzhdPage(pageReqVO).getList();
        ExcelUtils.write(response, "省总到账核对.xls", "数据", SzdzhdResVO.class, list);
    }
}
