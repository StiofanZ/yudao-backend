package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo.SyhzxxfyPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo.SyhzxxfyResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxxfy.SyhzxxfyDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.syhzxxfy.SyhzxxfyService;
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

@Tag(name = "管理后台 - 首页汇总信息分月")
@RestController
@RequestMapping("/lghjft/cxtj/syhzxxfy")
@Validated
public class SyhzxxfyController {

    @Resource
    private SyhzxxfyService syhzxxfyService;

    @GetMapping("/get")
    @Operation(summary = "获得首页汇总信息分月")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:syhzxxfy:query')")
    public CommonResult<SyhzxxfyResVO> getSyhzxxfy(@RequestParam("id") String id) {
        SyhzxxfyDO obj = syhzxxfyService.getSyhzxxfy(id);
        return success(BeanUtils.toBean(obj, SyhzxxfyResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得首页汇总信息分月分页")
    @PreAuthorize("@ss.hasPermission('lghjft:syhzxxfy:query')")
    public CommonResult<PageResult<SyhzxxfyResVO>> getSyhzxxfyPage(@Valid SyhzxxfyPageReqVO pageReqVO) {
        PageResult<SyhzxxfyDO> pageResult = syhzxxfyService.getSyhzxxfyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SyhzxxfyResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出首页汇总信息分月 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:syhzxxfy:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSyhzxxfyExcel(@Valid SyhzxxfyPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SyhzxxfyDO> list = syhzxxfyService.getSyhzxxfyPage(pageReqVO).getList();
        ExcelUtils.write(response, "首页汇总信息分月.xls", "数据", SyhzxxfyResVO.class,
                BeanUtils.toBean(list, SyhzxxfyResVO.class));
    }
}
