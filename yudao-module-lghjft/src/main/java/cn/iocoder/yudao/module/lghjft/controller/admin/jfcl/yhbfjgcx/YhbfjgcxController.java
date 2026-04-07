package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfjgcx.YhbfjgcxDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfjgcx.YhbfjgcxService;
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
public class YhbfjgcxController {

    @Resource
    private YhbfjgcxService yhbfjgcxService;

    @PostMapping("/create")
    @Operation(summary = "创建银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:create')")
    public CommonResult<String> createyhbfjgcx(@Valid @RequestBody YhbfjgcxSaveReqVO createReqVO) {
        return success(yhbfjgcxService.createyhbfjgcx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:update')")
    public CommonResult<Boolean> updateyhbfjgcx(@Valid @RequestBody YhbfjgcxSaveReqVO updateReqVO) {
        yhbfjgcxService.updateyhbfjgcx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除银行拨付结果查询")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:delete')")
    public CommonResult<Boolean> deleteyhbfjgcx(@RequestParam("id") String id) {
        yhbfjgcxService.deleteyhbfjgcx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:delete')")
    public CommonResult<Boolean> deleteyhbfjgcxList(@RequestParam("ids") List<String> ids) {
        yhbfjgcxService.deleteyhbfjgcxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得银行拨付结果查询")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:query')")
    public CommonResult<YhbfjgcxResVO> getyhbfjgcx(@RequestParam("id") String id) {
        YhbfjgcxDO yhbfjgcx = yhbfjgcxService.getyhbfjgcx(id);
        return success(BeanUtils.toBean(yhbfjgcx, YhbfjgcxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得银行拨付结果查询分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:query')")
    public CommonResult<PageResult<YhbfjgcxResVO>> getyhbfjgcxPage(@Valid YhbfjgcxPageReqVO pageReqVO) {
        PageResult<YhbfjgcxDO> pageResult = yhbfjgcxService.getyhbfjgcxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, YhbfjgcxResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出银行拨付结果查询 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportyhbfjgcxExcel(@Valid YhbfjgcxPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<YhbfjgcxDO> list = yhbfjgcxService.getyhbfjgcxPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "银行拨付结果查询.xls", "数据", YhbfjgcxResVO.class,
                BeanUtils.toBean(list, YhbfjgcxResVO.class));
    }

}
