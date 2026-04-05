package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxSummaryResVO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jffnmx.JffnmxService;
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

@Tag(name = "管理后台 - 经费分年明细")
@RestController
@RequestMapping("/lghjft/cxtj/jffnmx")
@Validated
public class JffnmxController {

    @Resource
    private JffnmxService jffnmxService;

    @GetMapping("/page")
    @Operation(summary = "经费分年明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jffnmx:query')")
    public CommonResult<PageResult<JffnmxSummaryResVO>> page(@Valid JffnmxPageReqVO pageReqVO) {
        return success(jffnmxService.selectJffnmxList(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费分年明细")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jffnmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid JffnmxPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JffnmxSummaryResVO> list = jffnmxService.selectJffnmxList(pageReqVO).getList();
        ExcelUtils.write(response, "经费分年明细.xls", "数据", JffnmxSummaryResVO.class, list);
    }
}
