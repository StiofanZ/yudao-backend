package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx.vo.XwqyjfyfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx.vo.XwqyjfyfmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjfyfmx.XwqyjfyfmxDO;
import cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfyfmx.XwqyjfyfmxService;
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

@Tag(name = "管理后台 - 小微企业经费60%明细")
@RestController
@RequestMapping("/lghjft/xwqy/xwqyjfyfmx")
@Validated
public class XwqyjfyfmxController {

    @Resource
    private XwqyjfyfmxService xwqyjfyfmxService;

    @GetMapping("/page")
    @Operation(summary = "获得小微企业经费60%明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjfyfmx:query')")
    public CommonResult<PageResult<XwqyjfyfmxDO>> getXwqyjfyfmxPage(@Valid XwqyjfyfmxPageReqVO pageReqVO) {
        return success(xwqyjfyfmxService.getXwqyjfyfmxPage(pageReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得小微企业经费60%明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjfyfmx:query')")
    public CommonResult<XwqyjfyfmxDO> getXwqyjfyfmx(@RequestParam("id") Long id) {
        return success(xwqyjfyfmxService.getXwqyjfyfmx(id));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小微企业经费60%明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjfyfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyjfyfmxExcel(@Valid XwqyjfyfmxPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setOffset(null);
        List<XwqyjfyfmxDO> list = xwqyjfyfmxService.getXwqyjfyfmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "小微企业经费60%明细.xls", "数据", XwqyjfyfmxResVO.class,
                BeanUtils.toBean(list, XwqyjfyfmxResVO.class));
    }
}
