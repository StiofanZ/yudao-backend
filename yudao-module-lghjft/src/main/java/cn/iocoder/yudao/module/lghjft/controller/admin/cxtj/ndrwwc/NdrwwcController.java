package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.ndrwwc.NdrwwcService;
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

@Tag(name = "管理后台 - 分年各级分成情况")
@RestController
@RequestMapping("/lghjft/cxtj/ndrwwc")
@Validated
public class NdrwwcController {

    @Resource
    private NdrwwcService ndrwwcService;

    @GetMapping("/get")
    @Operation(summary = "获得分年各级分成情况")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:ndrwwc:query')")
    public CommonResult<NdrwwcResVO> getNdrwwc(@RequestParam("id") String id) {
        NdrwwcDO obj = ndrwwcService.getNdrwwc(id);
        return success(BeanUtils.toBean(obj, NdrwwcResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分年各级分成情况分页")
    @PreAuthorize("@ss.hasPermission('lghjft:ndrwwc:query')")
    public CommonResult<PageResult<NdrwwcResVO>> getNdrwwcPage(@Valid NdrwwcPageReqVO pageReqVO) {
        PageResult<NdrwwcDO> pageResult = ndrwwcService.getNdrwwcPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, NdrwwcResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分年各级分成情况 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:ndrwwc:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportNdrwwcExcel(@Valid NdrwwcPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<NdrwwcDO> list = ndrwwcService.getNdrwwcPage(pageReqVO).getList();
        ExcelUtils.write(response, "分年各级分成情况.xls", "数据", NdrwwcResVO.class,
                BeanUtils.toBean(list, NdrwwcResVO.class));
    }
}
