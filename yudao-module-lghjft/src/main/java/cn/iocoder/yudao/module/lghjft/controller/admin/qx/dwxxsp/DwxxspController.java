package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspAuditReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspDetailRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspRespVO;
import cn.iocoder.yudao.module.lghjft.service.qx.dwxxsp.DwxxspService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 单位信息审批")
@RestController
@RequestMapping("/lghjft/qx/dwxxsp")
@Validated
public class DwxxspController {

    @Resource
    private DwxxspService dwxxspService;

    @GetMapping("/page")
    @Operation(summary = "获得单位信息审批分页")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dwxxsp:query')")
    public CommonResult<PageResult<DwxxspRespVO>> getDwxxspPage(@Valid DwxxspPageReqVO reqVO) {
        return success(dwxxspService.getDwxxspPage(reqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得单位信息审批详情")
    @Parameter(name = "businessType", required = true)
    @Parameter(name = "businessId", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dwxxsp:query')")
    public CommonResult<DwxxspDetailRespVO> getDwxxsp(@RequestParam("businessType") String businessType,
                                                      @RequestParam("businessId") Long businessId) {
        return success(dwxxspService.getDwxxspDetail(businessType, businessId));
    }

    @PutMapping("/audit")
    @Operation(summary = "处理单位信息审批")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-dwxxsp:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody DwxxspAuditReqVO reqVO) {
        dwxxspService.audit(reqVO);
        return success(true);
    }
}
