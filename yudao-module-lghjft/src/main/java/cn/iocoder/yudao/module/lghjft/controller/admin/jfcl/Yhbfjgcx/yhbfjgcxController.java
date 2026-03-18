package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx;

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

import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.Yhbfjgcx.yhbfjgcxDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.Yhbfjgcx.yhbfjgcxService;

@Tag(name = "管理后台 - 银行拨付结果查询")
@RestController
@RequestMapping("/jfcl/yhbfjgcx")
@Validated
public class yhbfjgcxController {

    @Resource
    private yhbfjgcxService yhbfjgcxService;

    @PostMapping("/create")
    @Operation(summary = "创建银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('jfcl:yhbfjgcx:create')")
    public CommonResult<String> createyhbfjgcx(@Valid @RequestBody yhbfjgcxSaveReqVO createReqVO) {
        return success(yhbfjgcxService.createyhbfjgcx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('jfcl:yhbfjgcx:update')")
    public CommonResult<Boolean> updateyhbfjgcx(@Valid @RequestBody yhbfjgcxSaveReqVO updateReqVO) {
        yhbfjgcxService.updateyhbfjgcx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除银行拨付结果查询")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('jfcl:yhbfjgcx:delete')")
    public CommonResult<Boolean> deleteyhbfjgcx(@RequestParam("id") String id) {
        yhbfjgcxService.deleteyhbfjgcx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除银行拨付结果查询")
                @PreAuthorize("@ss.hasPermission('jfcl:yhbfjgcx:delete')")
    public CommonResult<Boolean> deleteyhbfjgcxList(@RequestParam("ids") List<String> ids) {
        yhbfjgcxService.deleteyhbfjgcxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得银行拨付结果查询")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('jfcl:yhbfjgcx:query')")
    public CommonResult<yhbfjgcxRespVO> getyhbfjgcx(@RequestParam("id") String id) {
        yhbfjgcxDO yhbfjgcx = yhbfjgcxService.getyhbfjgcx(id);
        return success(BeanUtils.toBean(yhbfjgcx, yhbfjgcxRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得银行拨付结果查询分页")
    @PreAuthorize("@ss.hasPermission('jfcl:yhbfjgcx:query')")
    public CommonResult<PageResult<yhbfjgcxRespVO>> getyhbfjgcxPage(@Valid yhbfjgcxPageReqVO pageReqVO) {
        PageResult<yhbfjgcxDO> pageResult = yhbfjgcxService.getyhbfjgcxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, yhbfjgcxRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出银行拨付结果查询 Excel")
    @PreAuthorize("@ss.hasPermission('jfcl:yhbfjgcx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportyhbfjgcxExcel(@Valid yhbfjgcxPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<yhbfjgcxDO> list = yhbfjgcxService.getyhbfjgcxPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "银行拨付结果查询.xls", "数据", yhbfjgcxRespVO.class,
                        BeanUtils.toBean(list, yhbfjgcxRespVO.class));
    }

}