package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfbfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfbfmx.vo.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jfbfmx.JfbfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.szqzjzdc.SzqzjzdcDO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.jfbfmx.JfbfmxService;
import cn.iocoder.yudao.module.lghjft.service.sjwh.szqzjzdc.SzqzjzdcService;
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

@Tag(name = "管理后台 - 经费拨付明细")
@RestController
@RequestMapping("/lghjft/hbzz/jfbfmx")
@Validated
public class JfbfmxController {

    @Resource
    private JfbfmxService jfbfmxService;

    @Resource
    private SzqzjzdcService szqzjzdcService;

    @PostMapping("/create")
    @Operation(summary = "创建经费拨付明细")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:create')")
    public CommonResult<Long> createJfbfmx(@Valid @RequestBody JfbfmxSaveReqVO createReqVO) {
        return success(jfbfmxService.createJfbfmx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经费拨付明细")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:update')")
    public CommonResult<Boolean> updateJfbfmx(@Valid @RequestBody JfbfmxSaveReqVO updateReqVO) {
        jfbfmxService.updateJfbfmx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经费拨付明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:delete')")
    public CommonResult<Boolean> deleteJfbfmx(@RequestParam("id") Long id) {
        jfbfmxService.deleteJfbfmx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除经费拨付明细")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:delete')")
    public CommonResult<Boolean> deleteJfbfmxList(@RequestParam("ids") List<Long> ids) {
        jfbfmxService.deleteJfbfmxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得经费拨付明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<JfbfmxResVO> getJfbfmx(@RequestParam("id") Long id) {
        JfbfmxDO jfbfmx = jfbfmxService.getJfbfmx(id);
        return success(BeanUtils.toBean(jfbfmx, JfbfmxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经费拨付省总汇总")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<PageResult<JfbfmxSummaryResVO>> getSzmxPage(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getSzmxPage(pageReqVO));
    }

    @GetMapping("/page-hy")
    @Operation(summary = "获得经费拨付行业明细")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<PageResult<JfbfmxDetailResVO>> getHymxPage(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getHymxPage(pageReqVO));
    }

    @GetMapping("/page-xj")
    @Operation(summary = "获得经费拨付区县明细")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<PageResult<JfbfmxDetailResVO>> getXjmxPage(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getXjmxPage(pageReqVO));
    }

    @GetMapping("/page-sj")
    @Operation(summary = "获得经费拨付市级明细")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<PageResult<JfbfmxDetailResVO>> getSjmxPage(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getSjmxPage(pageReqVO));
    }

    @GetMapping("/page-sd")
    @Operation(summary = "获得经费拨付属地明细")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<PageResult<JfbfmxDetailResVO>> getSdmxPage(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getSdmxPage(pageReqVO));
    }

    @GetMapping("/page-tj-dept")
    @Operation(summary = "经费拨付按机构统计")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<List<JfbfmxtjResVO>> getTjByDept(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getTjByDept(pageReqVO));
    }

    @GetMapping("/page-tj-jsbj")
    @Operation(summary = "经费拨付按结算标记统计")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<List<JfbfmxjsbjResVO>> getTjByJsbj(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getTjByJsbj(pageReqVO));
    }

    @GetMapping("/page-tj-cbj")
    @Operation(summary = "经费拨付按筹备金标记统计")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<List<JfbfmxcbjResVO>> getTjByCbj(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getTjByCbj(pageReqVO));
    }

    @GetMapping("/page-tj-zspm")
    @Operation(summary = "经费拨付按征收品目统计")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<List<JfbfmxzspmResVO>> getTjByZspm(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getTjByZspm(pageReqVO));
    }

    @GetMapping("/page-tj-hkpch")
    @Operation(summary = "经费拨付按汇款批次统计")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<List<JfbfmxhkResVO>> getTjByHkpch(@Valid JfbfmxPageReqVO pageReqVO) {
        return success(jfbfmxService.getTjByHkpch(pageReqVO));
    }

    @GetMapping("/page-sdjz")
    @Operation(summary = "获得经费拨付属地记账明细")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:query')")
    public CommonResult<List<SzqzjzdcResVO>> getSdjzList(@Valid JfbfmxPageReqVO pageReqVO) {
        SzqzjzdcPageReqVO reqVO = BeanUtils.toBean(pageReqVO, SzqzjzdcPageReqVO.class);
        List<SzqzjzdcDO> list = szqzjzdcService.getLegacySdjzList(reqVO);
        return success(BeanUtils.toBean(list, SzqzjzdcResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费拨付省总汇总 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSzmxExcel(@Valid JfbfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfbfmxSummaryResVO> list = jfbfmxService.getSzmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费拨付明细.xls", "数据", JfbfmxSummaryResVO.class, list);
    }

    @GetMapping("/export-hy-excel")
    @Operation(summary = "导出经费拨付行业明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHymxExcel(@Valid JfbfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfbfmxDetailResVO> list = jfbfmxService.getHymxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费拨付行业明细.xls", "数据", JfbfmxDetailResVO.class, list);
    }

    @GetMapping("/export-xj-excel")
    @Operation(summary = "导出经费拨付区县明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXjmxExcel(@Valid JfbfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfbfmxDetailResVO> list = jfbfmxService.getXjmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费拨付区县明细.xls", "数据", JfbfmxDetailResVO.class, list);
    }

    @GetMapping("/export-sj-excel")
    @Operation(summary = "导出经费拨付市级明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSjmxExcel(@Valid JfbfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfbfmxDetailResVO> list = jfbfmxService.getSjmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费拨付市级明细.xls", "数据", JfbfmxDetailResVO.class, list);
    }

    @GetMapping("/export-sd-excel")
    @Operation(summary = "导出经费拨付属地明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSdmxExcel(@Valid JfbfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfbfmxDetailResVO> list = jfbfmxService.getSdmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费拨付属地明细.xls", "数据", JfbfmxDetailResVO.class, list);
    }

    @GetMapping("/export-hyjz-excel")
    @Operation(summary = "导出经费拨付行业记账数据 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHyjzExcel(@Valid JfbfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        SzqzjzdcPageReqVO reqVO = BeanUtils.toBean(pageReqVO, SzqzjzdcPageReqVO.class);
        List<SzqzjzdcDO> list = szqzjzdcService.getLegacyHyjzList(reqVO);
        ExcelUtils.write(response, "做账数据.xls", "数据", SzqzjzdcResVO.class,
                BeanUtils.toBean(list, SzqzjzdcResVO.class));
    }

    @GetMapping("/export-sdjz-excel")
    @Operation(summary = "导出经费拨付属地记账数据 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jfbfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSdjzExcel(@Valid JfbfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        SzqzjzdcPageReqVO reqVO = BeanUtils.toBean(pageReqVO, SzqzjzdcPageReqVO.class);
        List<SzqzjzdcDO> list = szqzjzdcService.getLegacySdjzList(reqVO);
        ExcelUtils.write(response, "做账数据.xls", "数据", SzqzjzdcResVO.class,
                BeanUtils.toBean(list, SzqzjzdcResVO.class));
    }
}
