package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.dhjftz.DhjftzService;
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

@Tag(name = "管理后台 - 到户经费台账查询")
@RestController
@RequestMapping("/lghjft/cxtj/dhjftz")
@Validated
public class DhjftzController {

    @Resource
    private DhjftzService dhjftzService;

    @GetMapping("/get")
    @Operation(summary = "获得到户经费台账")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-dhjftz:query')")
    public CommonResult<DhjftzResVO> getDhjftz(@RequestParam("djxh") String djxh) {
        DhjftzDO obj = dhjftzService.getDhjftz(djxh);
        return success(BeanUtils.toBean(obj, DhjftzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得到户经费台账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-dhjftz:query')")
    public CommonResult<PageResult<DhjftzResVO>> getDhjftzPage(@Valid DhjftzPageReqVO pageReqVO) {
        PageResult<DhjftzDO> pageResult = dhjftzService.getDhjftzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DhjftzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出到户经费台账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-dhjftz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDhjftzExcel(@Valid DhjftzPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DhjftzDO> list = dhjftzService.getDhjftzPage(pageReqVO).getList();
        ExcelUtils.write(response, "到户经费台账.xls", "数据", DhjftzResVO.class,
                BeanUtils.toBean(list, DhjftzResVO.class));
    }
}
