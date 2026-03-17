package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.hbfhz;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.hbfhz.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz.YhbfhzDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.hbfhz.YhbfhzService;

@Tag(name = "管理后台 - 银行拨付汇总")
@RestController
@RequestMapping("/lghjft/yhbfhz")
@Validated
public class YhbfhzController {

    @Resource
    private YhbfhzService yhbfhzService;

    @PostMapping("/create")
    @Operation(summary = "创建银行拨付汇总")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:create')")
    public CommonResult<Integer> createYhbfhz(@Valid @RequestBody YhbfhzSaveReqVO createReqVO) {
        return success(yhbfhzService.createYhbfhz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新银行拨付汇总")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:update')")
    public CommonResult<Boolean> updateYhbfhz(@Valid @RequestBody YhbfhzSaveReqVO updateReqVO) {
        yhbfhzService.updateYhbfhz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除银行拨付汇总")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:delete')")
    public CommonResult<Boolean> deleteYhbfhz(@RequestParam("id") Integer id) {
        yhbfhzService.deleteYhbfhz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除银行拨付汇总")
                @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:delete')")
    public CommonResult<Boolean> deleteYhbfhzList(@RequestParam("ids") List<Integer> ids) {
        yhbfhzService.deleteYhbfhzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得银行拨付汇总")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:query')")
    public CommonResult<YhbfhzRespVO> getYhbfhz(@RequestParam("id") Integer id) {
        YhbfhzDO yhbfhz = yhbfhzService.getYhbfhz(id);
        return success(BeanUtils.toBean(yhbfhz, YhbfhzRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得银行拨付汇总分页")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:query')")
    public CommonResult<PageResult<YhbfhzRespVO>> getYhbfhzPage(@Valid YhbfhzPageReqVO pageReqVO) {
        PageResult<YhbfhzDO> pageResult = yhbfhzService.getYhbfhzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, YhbfhzRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出银行拨付汇总 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportYhbfhzExcel(@Valid YhbfhzPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<YhbfhzDO> list = yhbfhzService.getYhbfhzPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "银行拨付汇总.xls", "数据", YhbfhzRespVO.class,
                        BeanUtils.toBean(list, YhbfhzRespVO.class));
    }

}