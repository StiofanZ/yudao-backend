package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjfhAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjmxResVO;
import cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjftj.XwqyjftjService;
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

@Tag(name = "管理后台 - 小微经费统计")
@RestController
@RequestMapping("/lghjft/xwqy/xwqyjftj")
@Validated
public class XwqyjftjController {

    @Resource
    private XwqyjftjService xwqyjftjService;

    @GetMapping("/list")
    @Operation(summary = "获得小微经费统计汇总列表")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjftj:query')")
    public CommonResult<List<XwqyjftjAggVO>> getXwqyjftjList(@Valid XwqyjftjPageReqVO reqVO) {
        List<XwqyjftjAggVO> list = xwqyjftjService.getXwqyjftjList(reqVO);
        return success(list);
    }

    @GetMapping("/list-fh")
    @Operation(summary = "获得小微经费统计反馈列表(按企业分组)")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjftj:query')")
    public CommonResult<List<XwqyjftjfhAggVO>> getXwqyjftjfhList(@Valid XwqyjftjPageReqVO reqVO) {
        List<XwqyjftjfhAggVO> list = xwqyjftjService.getXwqyjftjfhList(reqVO);
        return success(list);
    }

    @GetMapping("/list-mx")
    @Operation(summary = "获得小微经费统计明细列表")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjftj:query')")
    public CommonResult<List<XwqyjftjmxResVO>> getXwqyjftjmxList(@Valid XwqyjftjPageReqVO reqVO) {
        List<XwqyjftjmxResVO> list = xwqyjftjService.getXwqyjftjmxList(reqVO);
        return success(list);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小微经费统计 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjftj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyjftjExcel(@Valid XwqyjftjPageReqVO reqVO,
                                    HttpServletResponse response) throws IOException {
        List<XwqyjftjAggVO> list = xwqyjftjService.getXwqyjftjList(reqVO);
        ExcelUtils.write(response, "小微经费统计.xls", "数据", XwqyjftjAggVO.class, list);
    }

    @GetMapping("/export-excel-fh")
    @Operation(summary = "导出小微经费统计反馈 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjftj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyjftjfhExcel(@Valid XwqyjftjPageReqVO reqVO,
                                      HttpServletResponse response) throws IOException {
        List<XwqyjftjfhAggVO> list = xwqyjftjService.getXwqyjftjfhList(reqVO);
        ExcelUtils.write(response, "小微经费统计反馈.xls", "数据", XwqyjftjfhAggVO.class, list);
    }

    @GetMapping("/export-excel-mx")
    @Operation(summary = "导出小微经费统计明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjftj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyjftjmxExcel(@Valid XwqyjftjPageReqVO reqVO,
                                      HttpServletResponse response) throws IOException {
        List<XwqyjftjmxResVO> list = xwqyjftjService.getXwqyjftjmxList(reqVO);
        ExcelUtils.write(response, "小微经费统计明细.xls", "数据", XwqyjftjmxResVO.class, list);
    }
}
