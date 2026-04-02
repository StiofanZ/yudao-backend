package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx.vo.yhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx.vo.yhbfjgcxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx.vo.yhbfjgcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.Yhbfjgcx.yhbfjgcxDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.Yhbfjgcx.yhbfjgcxService;
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

@Tag(name = "管理后台 - 银行拨付结果查询")
@RestController
@RequestMapping("/lghjft/jfcl/yhbfjgcx")
@Validated
public class yhbfjgcxController {

    @Resource
    private yhbfjgcxService yhbfjgcxService;

    @PostMapping("/create")
    @Operation(summary = "创建银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfjgcx:create')")
    public CommonResult<String> createyhbfjgcx(@Valid @RequestBody yhbfjgcxSaveReqVO createReqVO) {
        return success(yhbfjgcxService.createyhbfjgcx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfjgcx:update')")
    public CommonResult<Boolean> updateyhbfjgcx(@Valid @RequestBody yhbfjgcxSaveReqVO updateReqVO) {
        yhbfjgcxService.updateyhbfjgcx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除银行拨付结果查询")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfjgcx:delete')")
    public CommonResult<Boolean> deleteyhbfjgcx(@RequestParam("id") String id) {
        yhbfjgcxService.deleteyhbfjgcx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除银行拨付结果查询")
                @PreAuthorize("@ss.hasPermission('lghjft:yhbfjgcx:delete')")
    public CommonResult<Boolean> deleteyhbfjgcxList(@RequestParam("ids") List<String> ids) {
        yhbfjgcxService.deleteyhbfjgcxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得银行拨付结果查询")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfjgcx:query')")
    public CommonResult<yhbfjgcxResVO> getyhbfjgcx(@RequestParam("id") String id) {
        yhbfjgcxDO yhbfjgcx = yhbfjgcxService.getyhbfjgcx(id);
        return success(BeanUtils.toBean(yhbfjgcx, yhbfjgcxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得银行拨付结果查询分页")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfjgcx:query')")
    public CommonResult<PageResult<yhbfjgcxResVO>> getyhbfjgcxPage(@Valid yhbfjgcxPageReqVO pageReqVO) {
        PageResult<yhbfjgcxDO> pageResult = yhbfjgcxService.getyhbfjgcxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, yhbfjgcxResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出银行拨付结果查询 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:yhbfjgcx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportyhbfjgcxExcel(@Valid yhbfjgcxPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<yhbfjgcxDO> list = yhbfjgcxService.getyhbfjgcxPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "银行拨付结果查询.xls", "数据", yhbfjgcxResVO.class,
                BeanUtils.toBean(list, yhbfjgcxResVO.class));
    }

}