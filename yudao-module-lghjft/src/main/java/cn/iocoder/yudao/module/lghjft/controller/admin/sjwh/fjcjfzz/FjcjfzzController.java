package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fjcjfzz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fjcjfzz.vo.FjcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fjcjfzz.vo.FjcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fjcjfzz.vo.FjcjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.fjcjfzz.FjcjfzzDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.fjcjfzz.FjcjfzzService;
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

@Tag(name = "管理后台 - 返基层账")
@RestController
@RequestMapping("/lghjft/sjwh/fjcjfzz")
@Validated
public class FjcjfzzController {

    @Resource
    private FjcjfzzService fjcjfzzService;

    @PostMapping("/create")
    @Operation(summary = "创建返基层账")
    @PreAuthorize("@ss.hasPermission('lghjft:fjcjfzz:create')")
    public CommonResult<Long> createFjcjfzz(@Valid @RequestBody FjcjfzzSaveReqVO createReqVO) {
        return success(fjcjfzzService.createFjcjfzz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新返基层账")
    @PreAuthorize("@ss.hasPermission('lghjft:fjcjfzz:update')")
    public CommonResult<Boolean> updateFjcjfzz(@Valid @RequestBody FjcjfzzSaveReqVO updateReqVO) {
        fjcjfzzService.updateFjcjfzz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除返基层账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:fjcjfzz:delete')")
    public CommonResult<Boolean> deleteFjcjfzz(@RequestParam("id") Long id) {
        fjcjfzzService.deleteFjcjfzz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除返基层账")
    @PreAuthorize("@ss.hasPermission('lghjft:fjcjfzz:delete')")
    public CommonResult<Boolean> deleteFjcjfzzList(@RequestParam("ids") List<Long> ids) {
        fjcjfzzService.deleteFjcjfzzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得返基层账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:fjcjfzz:query')")
    public CommonResult<FjcjfzzResVO> getFjcjfzz(@RequestParam("id") Long id) {
        FjcjfzzDO fjcjfzz = fjcjfzzService.getFjcjfzz(id);
        return success(BeanUtils.toBean(fjcjfzz, FjcjfzzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得返基层账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:fjcjfzz:query')")
    public CommonResult<PageResult<FjcjfzzResVO>> getFjcjfzzPage(@Valid FjcjfzzPageReqVO pageReqVO) {
        return success(fjcjfzzService.getFjcjfzzPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出返基层账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:fjcjfzz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFjcjfzzExcel(@Valid FjcjfzzPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FjcjfzzResVO> list = fjcjfzzService.getFjcjfzzPage(pageReqVO).getList();
        ExcelUtils.write(response, "返基层账.xls", "数据", FjcjfzzResVO.class,
                BeanUtils.toBean(list, FjcjfzzResVO.class));
    }
}
