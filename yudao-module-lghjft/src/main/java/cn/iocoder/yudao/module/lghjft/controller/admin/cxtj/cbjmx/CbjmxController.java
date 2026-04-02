package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxhzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxtjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.cbjmx.CbjmxService;
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
@RequestMapping("/lghjft/cxtj/cbjmx")
@Validated
public class CbjmxController {

    @Resource
    private CbjmxService cbjmxService;

    @GetMapping("/get")
    @Operation(summary = "获得筹备金统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjmx:query')")
    public CommonResult<CbjmxResVO> getCbjmx(@RequestParam("id") Long id) {
        CbjmxDO obj = cbjmxService.getCbjmx(id);
        return success(BeanUtils.toBean(obj, CbjmxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得筹备金统计分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjmx:query')")
    public CommonResult<PageResult<CbjmxResVO>> getCbjmxPage(@Valid CbjmxPageReqVO pageReqVO) {
        PageResult<CbjmxDO> pageResult = cbjmxService.getCbjmxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CbjmxResVO.class));
    }

    @GetMapping("/page-tj")
    @Operation(summary = "筹备金统计汇总")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjmx:query')")
    public CommonResult<List<CbjmxtjResVO>> getCbjmxTj(@Valid CbjmxPageReqVO pageReqVO) {
        return success(cbjmxService.getCbjmxTjList(pageReqVO));
    }

    @GetMapping("/page-hz")
    @Operation(summary = "筹备金汇总")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjmx:query')")
    public CommonResult<List<CbjmxhzResVO>> getCbjmxHz(@Valid CbjmxPageReqVO pageReqVO) {
        return success(cbjmxService.getCbjmxHzList(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金统计 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cbjmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjmxExcel(@Valid CbjmxPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CbjmxDO> list = cbjmxService.getCbjmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "筹备金统计.xls", "数据", CbjmxResVO.class,
                BeanUtils.toBean(list, CbjmxResVO.class));
    }
}
