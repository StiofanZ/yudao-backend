package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.ghjfjfdw.GhjfjfdwService;
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

@Tag(name = "管理后台 - 近三年缴费情况")
@RestController
@RequestMapping("/lghjft/cxtj/ghjfjfdw")
@Validated
public class GhjfjfdwController {

    @Resource
    private GhjfjfdwService ghjfjfdwService;

    @GetMapping("/get")
    @Operation(summary = "获得近三年缴费情况")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ghjfjfdw:query')")
    public CommonResult<GhjfjfdwResVO> getGhjfjfdw(@RequestParam("id") String id) {
        GhjfjfdwDO obj = ghjfjfdwService.getGhjfjfdw(id);
        return success(BeanUtils.toBean(obj, GhjfjfdwResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得近三年缴费情况分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ghjfjfdw:query')")
    public CommonResult<PageResult<GhjfjfdwResVO>> getGhjfjfdwPage(@Valid GhjfjfdwPageReqVO pageReqVO) {
        PageResult<GhjfjfdwDO> pageResult = ghjfjfdwService.getGhjfjfdwPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhjfjfdwResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出近三年缴费情况 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ghjfjfdw:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGhjfjfdwExcel(@Valid GhjfjfdwPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhjfjfdwDO> list = ghjfjfdwService.getGhjfjfdwPage(pageReqVO).getList();
        ExcelUtils.write(response, "近三年缴费情况.xls", "数据", GhjfjfdwResVO.class,
                BeanUtils.toBean(list, GhjfjfdwResVO.class));
    }
}
