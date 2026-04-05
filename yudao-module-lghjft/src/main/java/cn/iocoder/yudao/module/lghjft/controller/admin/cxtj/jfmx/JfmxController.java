package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jfmx.CxtjJfmxService;
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

@Tag(name = "管理后台 - 经费明细(查询统计)")
@RestController("cxtjJfmxController")
@RequestMapping("/lghjft/cxtj/jfmx")
@Validated
public class JfmxController {

    @Resource
    private CxtjJfmxService cxtjJfmxService;

    @GetMapping("/page")
    @Operation(summary = "获得经费明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:query')")
    public CommonResult<PageResult<JfmxResVO>> getJfmxPage(@Valid JfmxPageReqVO pageReqVO) {
        return success(cxtjJfmxService.getJfmxPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJfmxExcel(@Valid JfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfmxResVO> list = cxtjJfmxService.getJfmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费明细.xls", "数据", JfmxResVO.class, list);
    }
}
