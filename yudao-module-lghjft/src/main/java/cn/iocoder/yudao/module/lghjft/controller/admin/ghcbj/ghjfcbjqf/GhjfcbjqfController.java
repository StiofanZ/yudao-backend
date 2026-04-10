package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjqfDO;
import cn.iocoder.yudao.module.lghjft.service.ghcbj.ghjfcbjqf.GhjfcbjqfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 筹备金全返")
@RestController
@RequestMapping("/lghjft/cxtj/ghjfcbjqf")
@Validated
public class GhjfcbjqfController {

    @Resource
    private GhjfcbjqfService ghjfcbjqfService;

    @GetMapping("/get")
    @Operation(summary = "获得筹备金全返详情")
    @Parameter(name = "id", description = "工会经费ID", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-ghjfcbjqf:query')")
    public CommonResult<GhjfcbjqfResVO> getGhjfcbjqf(@RequestParam("id") Long id) {
        GhjfcbjqfDO data = ghjfcbjqfService.getGhjfcbjqf(id);
        return success(BeanUtils.toBean(data, GhjfcbjqfResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得筹备金全返分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ghjfcbjqf:query')")
    public CommonResult<PageResult<GhjfcbjqfResVO>> getGhjfcbjqfPage(@Valid GhjfcbjqfPageReqVO pageReqVO) {
        PageResult<GhjfcbjqfDO> pageResult = ghjfcbjqfService.getGhjfcbjqfPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhjfcbjqfResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金全返 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ghjfcbjqf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGhjfcbjqfExcel(@Valid GhjfcbjqfPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhjfcbjqfDO> list = ghjfcbjqfService.getGhjfcbjqfPage(pageReqVO).getList();
        ExcelUtils.write(response, "筹备金全返.xls", "数据", GhjfcbjqfResVO.class,
                BeanUtils.toBean(list, GhjfcbjqfResVO.class));
    }
}
