package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.cbjzz.CbjzzDO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.cbjzz.CbjzzService;
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

@Tag(name = "管理后台 - 筹备金做账")
@RestController
@RequestMapping("/lghjft/hbzz/cbjzz")
@Validated
public class CbjzzController {

    @Resource
    private CbjzzService cbjzzService;

    @PostMapping("/create")
    @Operation(summary = "创建筹备金做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-cbjzz:create')")
    public CommonResult<Long> createCbjzz(@Valid @RequestBody CbjzzSaveReqVO createReqVO) {
        return success(cbjzzService.createCbjzz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新筹备金做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-cbjzz:update')")
    public CommonResult<Boolean> updateCbjzz(@Valid @RequestBody CbjzzSaveReqVO updateReqVO) {
        cbjzzService.updateCbjzz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除筹备金做账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-cbjzz:delete')")
    public CommonResult<Boolean> deleteCbjzz(@RequestParam("id") Long id) {
        cbjzzService.deleteCbjzz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除筹备金做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-cbjzz:delete')")
    public CommonResult<Boolean> deleteCbjzzList(@RequestParam("ids") List<Long> ids) {
        cbjzzService.deleteCbjzzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得筹备金做账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-cbjzz:query')")
    public CommonResult<CbjzzResVO> getCbjzz(@RequestParam("id") Long id) {
        CbjzzDO cbjzz = cbjzzService.getCbjzz(id);
        return success(BeanUtils.toBean(cbjzz, CbjzzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得筹备金做账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-cbjzz:query')")
    public CommonResult<PageResult<CbjzzResVO>> getCbjzzPage(@Valid CbjzzPageReqVO pageReqVO) {
        PageResult<CbjzzDO> pageResult = cbjzzService.getCbjzzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CbjzzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金做账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-cbjzz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjzzExcel(@Valid CbjzzPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CbjzzDO> list = cbjzzService.getCbjzzPage(pageReqVO).getList();
        ExcelUtils.write(response, "筹备金做账.xls", "数据", CbjzzResVO.class,
                BeanUtils.toBean(list, CbjzzResVO.class));
    }
}
