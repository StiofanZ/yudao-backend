package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgResVO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jftzfswjg.JftzfswjgService;
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

@Tag(name = "管理后台 - 分税务机关统计")
@RestController
@RequestMapping("/lghjft/cxtj/jftzfswjg")
@Validated
public class JftzfswjgController {

    @Resource
    private JftzfswjgService jftzfswjgService;

    @GetMapping("/page")
    @Operation(summary = "分税务机关统计")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jftzfswjg:query')")
    public CommonResult<List<JftzfswjgResVO>> getJftzfswjgList(@Valid JftzfswjgPageReqVO pageReqVO) {
        return success(jftzfswjgService.getJftzfswjgList(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分税务机关统计 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jftzfswjg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid JftzfswjgPageReqVO pageReqVO,
                            HttpServletResponse response) throws IOException {
        List<JftzfswjgResVO> list = jftzfswjgService.getJftzfswjgList(pageReqVO);
        ExcelUtils.write(response, "分税务机关统计.xls", "数据", JftzfswjgResVO.class, list);
    }
}
