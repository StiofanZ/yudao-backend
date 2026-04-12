package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx95;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx95.vo.Xwqyjfmx95PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx95.vo.Xwqyjfmx95ResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;
import cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfmx95.Xwqyjfmx95Service;
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

@Tag(name = "管理后台 - 小微企业95%")
@RestController
@RequestMapping("/lghjft/xwqy/xwqyjfmx95")
@Validated
public class Xwqyjfmx95Controller {

    @Resource
    private Xwqyjfmx95Service xwqyjfmx95Service;

    @GetMapping("/page")
    @Operation(summary = "获得小微企业95%分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjfmx95:query')")
    public CommonResult<PageResult<XwqyjfDO>> getXwqyjfmx95Page(@Valid Xwqyjfmx95PageReqVO pageReqVO) {
        return success(xwqyjfmx95Service.getXwqyjfmx95Page(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小微企业95% Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqyjfmx95:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyjfmx95Excel(@Valid Xwqyjfmx95PageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        List<XwqyjfDO> list = xwqyjfmx95Service.getXwqyjfmx95List(pageReqVO);
        ExcelUtils.write(response, "小微企业经费95%明细.xls", "数据", Xwqyjfmx95ResVO.class,
                BeanUtils.toBean(list, Xwqyjfmx95ResVO.class));
    }
}
