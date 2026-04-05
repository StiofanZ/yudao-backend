package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo.SzdzhdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo.SzdzhdResVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.szdzhd.SzdzhdService;
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

@Tag(name = "管理后台 - 省总到账核对")
@RestController
@RequestMapping("/lghjft/hbzz/szdzhd")
@Validated
public class SzdzhdController {

    @Resource
    private SzdzhdService szdzhdService;

    @GetMapping("/page")
    @Operation(summary = "获得省总到账核对分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-szdzhd:query')")
    public CommonResult<PageResult<SzdzhdResVO>> getSzdzhdPage(@Valid SzdzhdPageReqVO pageReqVO) {
        return success(szdzhdService.getSzdzhdPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出省总到账核对 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-szdzhd:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSzdzhdExcel(@Valid SzdzhdPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SzdzhdResVO> list = szdzhdService.getSzdzhdPage(pageReqVO).getList();
        ExcelUtils.write(response, "省总到账核对.xls", "数据", SzdzhdResVO.class, list);
    }
}
