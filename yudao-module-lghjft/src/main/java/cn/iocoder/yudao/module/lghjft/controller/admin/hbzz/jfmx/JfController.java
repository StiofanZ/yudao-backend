package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfSummaryResVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx.JfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 -  经费明细对象")
@RestController
@RequestMapping("/lghjft/jf")
@Validated
public class JfController {

    @Resource
    private JfService jfService;


    /**
     * 经费明细列表（v1 /jftz/jfmx/list）
     */
    @GetMapping("/list")
    @Operation(summary = "获得经费明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
    public CommonResult<PageResult<JfResVO>> getJfmxList(@Valid JfPageReqVO jfmx) {
        PageResult<JfResVO> pageResult = jfService.selectJfmxList(jfmx);
        return success(pageResult);
    }

    /**
     * 经费台账明细列表（v1 /jftz/jfmx/listmx）
     */
    @GetMapping("/listmx")
    @Operation(summary = "获得经费台账明细")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
    public CommonResult<PageResult<JfResVO>> getJftzmxList(@Valid JfPageReqVO jfmx) {
        PageResult<JfResVO> pageResult = jfService.selectJftzmxList(jfmx);
        return success(pageResult);
    }



//    @PostMapping("/create")
//    @Operation(summary = "创建 经费明细对象")
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:create')")
//    public CommonResult<Integer> createJf(@Valid @RequestBody JfSaveReqVO createReqVO) {
//        return success(jfService.createJf(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新 经费明细对象")
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:update')")
//    public CommonResult<Boolean> updateJf(@Valid @RequestBody JfSaveReqVO updateReqVO) {
//        jfService.updateJf(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除 经费明细对象")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:delete')")
//    public CommonResult<Boolean> deleteJf(@RequestParam("id") Integer id) {
//        jfService.deleteJf(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除 经费明细对象")
//                @PreAuthorize("@ss.hasPermission('lghjft:jf:delete')")
//    public CommonResult<Boolean> deleteJfList(@RequestParam("ids") List<Integer> ids) {
//        jfService.deleteJfListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得 经费明细对象")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
//    public CommonResult<JfResVO> getJf(@RequestParam("id") Integer id) {
//        JfDO jf = jfService.getJf(id);
//        return success(BeanUtils.toBean(jf, JfResVO.class));
//    }
//
@GetMapping("/listfymx")
@Operation(summary = "经费返还月度明细")
@PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
public CommonResult<PageResult<JfSummaryResVO>> getJffymxList(@Valid JfPageReqVO jfmx) {
    return success(jfService.selectJffymxList(jfmx));
}

    @GetMapping("/listfnmx")
    @Operation(summary = "经费返还年度明细")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
    public CommonResult<PageResult<JfSummaryResVO>> getJffnmxList(@Valid JfPageReqVO jfmx) {
        return success(jfService.selectJffnmxList(jfmx));
    }

    @GetMapping("/listfsjzqmx")
    @Operation(summary = "经费返还汇缴周期明细")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
    public CommonResult<PageResult<JfSummaryResVO>> getJffsjzqmxList(@Valid JfPageReqVO jfmx) {
        return success(jfService.selectJffsjzqmxList(jfmx));
    }

    @GetMapping("/listtzfn")
    @Operation(summary = "经费台账年度汇总")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
    public CommonResult<PageResult<JfSummaryResVO>> getJftzfnList(@Valid JfPageReqVO jfmx) {
        return success(jfService.selectJftzfnList(jfmx));
    }

    @GetMapping("/listtzfswjg")
    @Operation(summary = "经费台账按税务机关汇总")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
    public CommonResult<PageResult<JfSummaryResVO>> getJftzfswjgList(@Valid JfPageReqVO jfmx) {
        return success(jfService.selectJftzfswjgList(jfmx));
    }

    @GetMapping("/listszdzdhd")
    @Operation(summary = "省级对账")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
    public CommonResult<PageResult<JfSummaryResVO>> getSzdzhdList(JfPageReqVO jfmx) {
        // v1 不分页，强制设置
        jfmx.setPageNo(1);
        jfmx.setPageSize(PageParam.PAGE_SIZE_NONE);
        return success(jfService.selectSzdzhdList(jfmx));
    }

//    @GetMapping("/page")
//    @Operation(summary = "获得 经费明细对象分页")
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
//    public CommonResult<PageResult<JfResVO>> getJfPage(@Valid JfPageReqVO pageReqVO) {
//        PageResult<JfDO> pageResult = jfService.getJfPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, JfResVO.class));
//    }
//
    @GetMapping("/export-excel")
    @Operation(summary = "导出 经费明细对象 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJfExcel(@Valid JfPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfResVO> list = jfService.selectJftzmxList(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, " 经费明细对象.xls", "数据", JfResVO.class,
                BeanUtils.toBean(list, JfResVO.class));
    }

}
