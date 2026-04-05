package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo.DgjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo.DgjftzResVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.dgjftz.DgjftzService;
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

@Tag(name = "管理后台 - 代管经费台账")
@RestController
@RequestMapping("/lghjft/hbzz/dgjftz")
@Validated
public class DgjftzController {

    @Resource
    private DgjftzService dgjftzService;

    @GetMapping("/page")
    @Operation(summary = "获得代管经费台账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-dgjftz:query')")
    public CommonResult<PageResult<DgjftzResVO>> getDgjftzPage(@Valid DgjftzPageReqVO pageReqVO) {
        return success(dgjftzService.getDgjftzPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出代管经费台账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-dgjftz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDgjftzExcel(@Valid DgjftzPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DgjftzResVO> list = dgjftzService.getDgjftzPage(pageReqVO).getList();
        ExcelUtils.write(response, "代管经费台账.xls", "数据", DgjftzResVO.class, list);
    }
}
