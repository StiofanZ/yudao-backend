package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzImportVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.lrthpz.LrthpzDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.lrthpz.LrthpzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 录入退回凭证")
@RestController
@RequestMapping("/lghjft/jfcl/lrthpz")
@Validated
public class LrthpzController {

    @Resource
    private LrthpzService lrthpzService;

    @PostMapping("/create")
    @Operation(summary = "创建录入退回凭证")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-lrthpz:create')")
    public CommonResult<Long> createLrthpz(@Valid @RequestBody LrthpzSaveReqVO createReqVO) {
        return success(lrthpzService.createLrthpz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新录入退回凭证")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-lrthpz:update')")
    public CommonResult<Boolean> updateLrthpz(@Valid @RequestBody LrthpzSaveReqVO updateReqVO) {
        lrthpzService.updateLrthpz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除录入退回凭证")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-lrthpz:delete')")
    public CommonResult<Boolean> deleteLrthpz(@RequestParam("id") Long id) {
        lrthpzService.deleteLrthpz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除录入退回凭证")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-lrthpz:delete')")
    public CommonResult<Boolean> deleteLrthpzList(@RequestParam("ids") String ids) {
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        lrthpzService.deleteLrthpzBatch(idList);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得录入退回凭证")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-lrthpz:query')")
    public CommonResult<LrthpzResVO> getLrthpz(@RequestParam("id") Long id) {
        LrthpzDO data = lrthpzService.getLrthpz(id);
        return success(BeanUtils.toBean(data, LrthpzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得录入退回凭证分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-lrthpz:query')")
    public CommonResult<PageResult<LrthpzResVO>> getLrthpzPage(@Valid LrthpzPageReqVO pageReqVO) {
        PageResult<LrthpzDO> pageResult = lrthpzService.getLrthpzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LrthpzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出录入退回凭证 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-lrthpz:export')")
    public void exportLrthpzExcel(@Valid LrthpzPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LrthpzDO> list = lrthpzService.getLrthpzPage(pageReqVO).getList();
        ExcelUtils.write(response, "录入退回凭证.xls", "数据", LrthpzResVO.class,
                BeanUtils.toBean(list, LrthpzResVO.class));
    }

    @PostMapping("/import")
    @Operation(summary = "导入退回凭证")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-lrthpz:create')")
    public CommonResult<String> importData(@RequestParam("file") MultipartFile file) throws Exception {
        List<LrthpzImportVO> list = ExcelUtils.read(file, LrthpzImportVO.class);
        int successCount = lrthpzService.importLrthpz(list);
        return success("成功导入 " + successCount + " 条数据");
    }
}
