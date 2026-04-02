package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.top.TopService;
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

@Tag(name = "管理后台 - 缴费排行")
@RestController
@RequestMapping("/lghjft/cxtj/top")
@Validated
public class TopController {

    @Resource
    private TopService topService;

    @GetMapping("/get")
    @Operation(summary = "获得缴费排行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:top:query')")
    public CommonResult<TopResVO> getTop(@RequestParam("id") String id) {
        TopDO obj = topService.getTop(id);
        return success(BeanUtils.toBean(obj, TopResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得缴费排行分页")
    @PreAuthorize("@ss.hasPermission('lghjft:top:query')")
    public CommonResult<PageResult<TopResVO>> getTopPage(@Valid TopPageReqVO pageReqVO) {
        PageResult<TopDO> pageResult = topService.getTopPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TopResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出缴费排行 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:top:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTopExcel(@Valid TopPageReqVO pageReqVO,
                               HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TopDO> list = topService.getTopPage(pageReqVO).getList();
        ExcelUtils.write(response, "缴费排行.xls", "数据", TopResVO.class,
                BeanUtils.toBean(list, TopResVO.class));
    }
}
