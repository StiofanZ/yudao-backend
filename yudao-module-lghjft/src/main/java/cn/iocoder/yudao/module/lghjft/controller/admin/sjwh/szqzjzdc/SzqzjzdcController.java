package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.szqzjzdc.SzqzjzdcDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.szqzjzdc.SzqzjzdcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 省总做账导出")
@RestController
@RequestMapping("/lghjft/sjwh/szqzjzdc")
@Validated
public class SzqzjzdcController {

    @Resource
    private SzqzjzdcService szqzjzdcService;

    @GetMapping("/get")
    @Operation(summary = "获得省总做账导出")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-szqzjzdc:query')")
    public CommonResult<SzqzjzdcResVO> getSzqzjzdc(@RequestParam("id") String id) {
        SzqzjzdcDO szqzjzdc = szqzjzdcService.getSzqzjzdc(id);
        return success(BeanUtils.toBean(szqzjzdc, SzqzjzdcResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得省总做账导出分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-szqzjzdc:query')")
    public CommonResult<PageResult<SzqzjzdcResVO>> getSzqzjzdcPage(@Valid SzqzjzdcPageReqVO pageReqVO) {
        PageResult<SzqzjzdcDO> pageResult = szqzjzdcService.getSzqzjzdcPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SzqzjzdcResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出省总做账导出 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-szqzjzdc:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSzqzjzdcExcel(@Valid SzqzjzdcPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SzqzjzdcDO> list = szqzjzdcService.getSzqzjzdcPage(pageReqVO).getList();
        ExcelUtils.write(response, "省总做账导出.xls", "数据", SzqzjzdcResVO.class,
                BeanUtils.toBean(list, SzqzjzdcResVO.class));
    }
}
