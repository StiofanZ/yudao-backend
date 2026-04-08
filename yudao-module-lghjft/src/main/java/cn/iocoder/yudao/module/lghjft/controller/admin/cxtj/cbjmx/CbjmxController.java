package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxhzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxtjDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.cbjmx.CbjmxService;
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

@Tag(name = "管理后台 - 筹备金统计")
@RestController
@RequestMapping("/lghjft/cxtj/cbjmx")
@Validated
public class CbjmxController {

    @Resource
    private CbjmxService cbjmxService;

    /**
     * v1: list - 筹备金明细分页
     */
    @GetMapping("/page")
    @Operation(summary = "获得筹备金明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-cbjmx:query')")
    public CommonResult<PageResult<CbjmxResVO>> getCbjmxPage(@Valid CbjmxPageReqVO pageReqVO) {
        PageResult<CbjmxDO> pageResult = cbjmxService.getCbjmxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CbjmxResVO.class));
    }

    /**
     * v1: listtj - 筹备金统计列表 (GROUP BY 聚合，分页)
     */
    @GetMapping("/listtj")
    @Operation(summary = "获得筹备金统计列表")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-cbjmx:query')")
    public CommonResult<List<CbjmxtjDO>> getCbjmxtjList(@Valid CbjmxPageReqVO reqVO) {
        List<CbjmxtjDO> list = cbjmxService.getCbjmxtjList(reqVO);
        return success(list);
    }

    /**
     * v1: listhz - 筹备金汇总列表 (GROUP BY 聚合，不分页)
     */
    @GetMapping("/listhz")
    @Operation(summary = "获得筹备金汇总列表")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-cbjmx:query')")
    public CommonResult<List<CbjmxhzDO>> getCbjmxhzList(@Valid CbjmxPageReqVO reqVO) {
        List<CbjmxhzDO> list = cbjmxService.getCbjmxhzList(reqVO);
        return success(list);
    }

    /**
     * v1: export - 导出筹备金明细
     */
    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金统计 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-cbjmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjmxExcel(@Valid CbjmxPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CbjmxDO> list = cbjmxService.getCbjmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "筹备金统计.xls", "数据", CbjmxResVO.class,
                BeanUtils.toBean(list, CbjmxResVO.class));
    }
}
