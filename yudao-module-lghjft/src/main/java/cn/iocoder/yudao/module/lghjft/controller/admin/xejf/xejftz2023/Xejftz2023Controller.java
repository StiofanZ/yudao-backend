package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo.Xejftz2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo.Xejftz2023ResVO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xejftz2023.Xejftz2023Service;
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

@Tag(name = "管理后台 - 小额缴费统计")
@RestController
@RequestMapping("/lghjft/xejf/xejftz2023")
@Validated
public class Xejftz2023Controller {

    @Resource
    private Xejftz2023Service xejftz2023Service;

    @GetMapping("/page")
    @Operation(summary = "小额缴费统计")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejftz2023:query')")
    public CommonResult<List<Xejftz2023ResVO>> getXejftz2023List(@Valid Xejftz2023PageReqVO pageReqVO) {
        return success(xejftz2023Service.getXejftz2023List(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额缴费统计 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejftz2023:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid Xejftz2023PageReqVO pageReqVO,
                            HttpServletResponse response) throws IOException {
        List<Xejftz2023ResVO> list = xejftz2023Service.getXejftz2023List(pageReqVO);
        ExcelUtils.write(response, "小额缴费统计.xls", "数据", Xejftz2023ResVO.class, list);
    }
}
