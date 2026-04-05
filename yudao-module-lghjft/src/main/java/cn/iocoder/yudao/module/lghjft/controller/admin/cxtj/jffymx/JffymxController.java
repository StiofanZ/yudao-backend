package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxSummaryResVO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jffymx.JffymxService;
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

@Tag(name = "管理后台 - 经费分月明细")
@RestController
@RequestMapping("/lghjft/cxtj/jffymx")
@Validated
public class JffymxController {

    @Resource
    private JffymxService jffymxService;

    @GetMapping("/page")
    @Operation(summary = "经费分月明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jffymx:query')")
    public CommonResult<PageResult<JffymxSummaryResVO>> page(@Valid JffymxPageReqVO pageReqVO) {
        return success(jffymxService.selectJffymxList(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费分月明细")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jffymx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid JffymxPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JffymxSummaryResVO> list = jffymxService.selectJffymxList(pageReqVO).getList();
        ExcelUtils.write(response, "经费分月明细.xls", "数据", JffymxSummaryResVO.class, list);
    }
}
