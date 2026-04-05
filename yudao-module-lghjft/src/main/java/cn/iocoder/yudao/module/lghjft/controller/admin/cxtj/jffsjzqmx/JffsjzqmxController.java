package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxResVO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jffsjzqmx.JffsjzqmxService;
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

@Tag(name = "管理后台 - 分年各级分成情况")
@RestController
@RequestMapping("/lghjft/cxtj/jffsjzqmx")
@Validated
public class JffsjzqmxController {

    @Resource
    private JffsjzqmxService jffsjzqmxService;

    @GetMapping("/page")
    @Operation(summary = "分年各级分成情况")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jffsjzqmx:query')")
    public CommonResult<List<JffsjzqmxResVO>> getJffsjzqmxList(@Valid JffsjzqmxPageReqVO pageReqVO) {
        return success(jffsjzqmxService.getJffsjzqmxList(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分年各级分成情况 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jffsjzqmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid JffsjzqmxPageReqVO pageReqVO,
                            HttpServletResponse response) throws IOException {
        List<JffsjzqmxResVO> list = jffsjzqmxService.getJffsjzqmxList(pageReqVO);
        ExcelUtils.write(response, "分年各级分成情况.xls", "数据", JffsjzqmxResVO.class, list);
    }
}
