package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx60;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx60.vo.Xwqyjfmx60PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx60.vo.Xwqyjfmx60ResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjfyfmx.XwqyjfyfmxDO;
import cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfmx60.Xwqyjfmx60Service;
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

@Tag(name = "管理后台 - 小微企业60%")
@RestController
@RequestMapping("/lghjft/xwqy/xwqyjfmx60")
@Validated
public class Xwqyjfmx60Controller {

    @Resource
    private Xwqyjfmx60Service xwqyjfmx60Service;

    @GetMapping("/page")
    @Operation(summary = "获得小微企业60%分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjfmx60:query')")
    public CommonResult<PageResult<XwqyjfyfmxDO>> getXwqyjfmx60Page(@Valid Xwqyjfmx60PageReqVO pageReqVO) {
        return success(xwqyjfmx60Service.getXwqyjfmx60Page(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小微企业60% Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjfmx60:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyjfmx60Excel(@Valid Xwqyjfmx60PageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        List<XwqyjfyfmxDO> list = xwqyjfmx60Service.getXwqyjfmx60List(pageReqVO);
        ExcelUtils.write(response, "小微企业经费60%明细.xls", "数据", Xwqyjfmx60ResVO.class,
                BeanUtils.toBean(list, Xwqyjfmx60ResVO.class));
    }
}
