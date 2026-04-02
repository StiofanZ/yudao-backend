package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.sgbflr.SgbflrDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.sgbflr.SgbflrService;
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

@Tag(name = "管理后台 - 手工拨付录入")
@RestController
@RequestMapping("/lghjft/jfcl/sgbflr")
@Validated
public class SgbflrController {

    @Resource
    private SgbflrService sgbflrService;

    @PostMapping("/create")
    @Operation(summary = "创建手工拨付录入")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-sgbflr:create')")
    public CommonResult<Long> createSgbflr(@Valid @RequestBody SgbflrSaveReqVO createReqVO) {
        return success(sgbflrService.createSgbflr(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新手工拨付录入")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-sgbflr:update')")
    public CommonResult<Boolean> updateSgbflr(@Valid @RequestBody SgbflrSaveReqVO updateReqVO) {
        sgbflrService.updateSgbflr(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除手工拨付录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-sgbflr:delete')")
    public CommonResult<Boolean> deleteSgbflr(@RequestParam("id") Long id) {
        sgbflrService.deleteSgbflr(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得手工拨付录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-sgbflr:query')")
    public CommonResult<SgbflrResVO> getSgbflr(@RequestParam("id") Long id) {
        SgbflrDO data = sgbflrService.getSgbflr(id);
        return success(BeanUtils.toBean(data, SgbflrResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得手工拨付录入分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-sgbflr:query')")
    public CommonResult<PageResult<SgbflrResVO>> getSgbflrPage(@Valid SgbflrPageReqVO pageReqVO) {
        PageResult<SgbflrDO> pageResult = sgbflrService.getSgbflrPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SgbflrResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出手工拨付录入 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-sgbflr:export')")
    public void exportSgbflrExcel(@Valid SgbflrPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SgbflrDO> list = sgbflrService.getSgbflrPage(pageReqVO).getList();
        ExcelUtils.write(response, "手工拨付录入.xls", "数据", SgbflrResVO.class,
                BeanUtils.toBean(list, SgbflrResVO.class));
    }
}
