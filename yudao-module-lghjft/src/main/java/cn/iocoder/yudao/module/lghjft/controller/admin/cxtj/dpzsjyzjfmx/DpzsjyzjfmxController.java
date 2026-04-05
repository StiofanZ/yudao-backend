package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo.DpzsjyzjfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo.DpzsjyzjfmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dpzsjyzjfmx.DpzsjyzjfmxDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.dpzsjyzjfmx.DpzsjyzjfmxService;
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

@Tag(name = "管理后台 - 近一周缴费")
@RestController
@RequestMapping("/lghjft/cxtj/dpzsjyzjfmx")
@Validated
public class DpzsjyzjfmxController {

    @Resource
    private DpzsjyzjfmxService dpzsjyzjfmxService;

    @GetMapping("/get")
    @Operation(summary = "获得近一周缴费")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-dpzsjyzjfmx:query')")
    public CommonResult<DpzsjyzjfmxResVO> getDpzsjyzjfmx(@RequestParam("id") String id) {
        DpzsjyzjfmxDO obj = dpzsjyzjfmxService.getDpzsjyzjfmx(id);
        return success(BeanUtils.toBean(obj, DpzsjyzjfmxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得近一周缴费分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-dpzsjyzjfmx:query')")
    public CommonResult<PageResult<DpzsjyzjfmxResVO>> getDpzsjyzjfmxPage(@Valid DpzsjyzjfmxPageReqVO pageReqVO) {
        PageResult<DpzsjyzjfmxDO> pageResult = dpzsjyzjfmxService.getDpzsjyzjfmxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DpzsjyzjfmxResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出近一周缴费 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-dpzsjyzjfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDpzsjyzjfmxExcel(@Valid DpzsjyzjfmxPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DpzsjyzjfmxDO> list = dpzsjyzjfmxService.getDpzsjyzjfmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "近一周缴费.xls", "数据", DpzsjyzjfmxResVO.class,
                BeanUtils.toBean(list, DpzsjyzjfmxResVO.class));
    }
}
