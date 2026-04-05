package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo.SyhzxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo.SyhzxxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxx.SyhzxxDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.syhzxx.SyhzxxService;
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

@Tag(name = "管理后台 - 首页汇总信息")
@RestController
@RequestMapping("/lghjft/cxtj/syhzxx")
@Validated
public class SyhzxxController {

    @Resource
    private SyhzxxService syhzxxService;

    @GetMapping("/get")
    @Operation(summary = "获得首页汇总信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-syhzxx:query')")
    public CommonResult<SyhzxxResVO> getSyhzxx(@RequestParam("id") String id) {
        SyhzxxDO obj = syhzxxService.getSyhzxx(id);
        return success(BeanUtils.toBean(obj, SyhzxxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得首页汇总信息分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-syhzxx:query')")
    public CommonResult<PageResult<SyhzxxResVO>> getSyhzxxPage(@Valid SyhzxxPageReqVO pageReqVO) {
        PageResult<SyhzxxDO> pageResult = syhzxxService.getSyhzxxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SyhzxxResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出首页汇总信息 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-syhzxx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSyhzxxExcel(@Valid SyhzxxPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SyhzxxDO> list = syhzxxService.getSyhzxxPage(pageReqVO).getList();
        ExcelUtils.write(response, "首页汇总信息.xls", "数据", SyhzxxResVO.class,
                BeanUtils.toBean(list, SyhzxxResVO.class));
    }
}
