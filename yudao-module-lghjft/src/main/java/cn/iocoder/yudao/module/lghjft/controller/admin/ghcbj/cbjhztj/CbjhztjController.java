package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjhzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjtjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxhzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxtjDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjhztj.CbjhztjDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.cbjmx.CbjmxService;
import cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjhztj.CbjhztjService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 筹备金统计")
@RestController
@RequestMapping("/lghjft/ghcbj/cbjhztj")
@Validated
public class CbjhztjController {

    @Resource
    private CbjhztjService cbjhztjService;

    @Resource
    private CbjmxService cbjmxService;

    @GetMapping("/get")
    @Operation(summary = "获得筹备金汇总统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjhztj:query')")
    public CommonResult<CbjhztjResVO> getCbjhztj(@RequestParam("id") Long id) {
        CbjhztjDO obj = cbjhztjService.getCbjhztj(id);
        return success(BeanUtils.toBean(obj, CbjhztjResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得筹备金明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjhztj:query')")
    public CommonResult<PageResult<CbjhztjResVO>> getCbjhztjPage(@Valid CbjhztjPageReqVO pageReqVO) {
        PageResult<CbjmxDO> pageResult = cbjmxService.getCbjmxPage(toLegacyReq(pageReqVO));
        return success(BeanUtils.toBean(pageResult, CbjhztjResVO.class));
    }

    @GetMapping("/page-tj")
    @Operation(summary = "获得筹备金分户统计分页")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjhztj:query')")
    public CommonResult<PageResult<CbjhztjtjResVO>> getCbjhztjTj(@Valid CbjhztjPageReqVO pageReqVO) {
        PageResult<CbjmxtjDO> pageResult = cbjmxService.getCbjmxtjPage(toLegacyReq(pageReqVO));
        return success(BeanUtils.toBean(pageResult, CbjhztjtjResVO.class));
    }

    @GetMapping("/page-hz")
    @Operation(summary = "获得筹备金汇总列表")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjhztj:query')")
    public CommonResult<List<CbjhztjhzResVO>> getCbjhztjHz(@Valid CbjhztjPageReqVO pageReqVO) {
        List<CbjmxhzDO> list = cbjmxService.getCbjmxhzList(toLegacyReq(pageReqVO));
        return success(BeanUtils.toBean(list, CbjhztjhzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjhztj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjhztjExcel(@Valid CbjhztjPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        CbjmxPageReqVO reqVO = toLegacyReq(pageReqVO);
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CbjmxDO> list = cbjmxService.getCbjmxPage(reqVO).getList();
        ExcelUtils.write(response, "筹备金明细.xls", "数据", CbjhztjResVO.class,
                BeanUtils.toBean(list, CbjhztjResVO.class));
    }

    @GetMapping("/export-tj-excel")
    @Operation(summary = "导出筹备金分户统计 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjhztj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjhztjTjExcel(@Valid CbjhztjPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        CbjmxPageReqVO reqVO = toLegacyReq(pageReqVO);
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CbjmxtjDO> list = cbjmxService.getCbjmxtjList(reqVO);
        ExcelUtils.write(response, "筹备金分户统计.xls", "数据", CbjhztjtjResVO.class,
                BeanUtils.toBean(list, CbjhztjtjResVO.class));
    }

    @GetMapping("/export-hz-excel")
    @Operation(summary = "导出筹备金汇总 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjhztj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjhztjHzExcel(@Valid CbjhztjPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        List<CbjmxhzDO> list = cbjmxService.getCbjmxhzList(toLegacyReq(pageReqVO));
        ExcelUtils.write(response, "筹备金统计.xls", "数据", CbjhztjhzResVO.class,
                BeanUtils.toBean(list, CbjhztjhzResVO.class));
    }

    private CbjmxPageReqVO toLegacyReq(CbjhztjPageReqVO pageReqVO) {
        return BeanUtils.toBean(pageReqVO, CbjmxPageReqVO.class);
    }
}
