package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo.JftzfnPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo.JftzfnSummaryResVO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jftzfn.JftzfnService;
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

@Tag(name = "管理后台 - 经费台账分年")
@RestController
@RequestMapping("/lghjft/cxtj/jftzfn")
@Validated
public class JftzfnController {

    @Resource
    private JftzfnService jftzfnService;

    @GetMapping("/page")
    @Operation(summary = "经费台账分年分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jftzfn:query')")
    public CommonResult<PageResult<JftzfnSummaryResVO>> page(@Valid JftzfnPageReqVO pageReqVO) {
        return success(jftzfnService.selectJftzfnList(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费台账分年")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jftzfn:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid JftzfnPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JftzfnSummaryResVO> list = jftzfnService.selectJftzfnList(pageReqVO).getList();
        ExcelUtils.write(response, "经费台账分年.xls", "数据", JftzfnSummaryResVO.class, list);
    }
}
