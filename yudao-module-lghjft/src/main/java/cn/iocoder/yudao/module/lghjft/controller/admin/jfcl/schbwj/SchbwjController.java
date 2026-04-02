package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj.JfclSchbwjDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.schbwj.SchbwjService;
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

@Tag(name = "管理后台 - 实查汇报无结")
@RestController
@RequestMapping("/lghjft/jfcl/schbwj")
@Validated
public class SchbwjController {

    @Resource
    private SchbwjService schbwjService;

    @PostMapping("/create")
    @Operation(summary = "创建实查汇报无结")
    @PreAuthorize("@ss.hasPermission('lghjft:schbwj:create')")
    public CommonResult<Long> createSchbwj(@Valid @RequestBody SchbwjSaveReqVO createReqVO) {
        return success(schbwjService.createSchbwj(createReqVO));
    }

    @PostMapping("/generate")
    @Operation(summary = "生成划拨数据")
    @PreAuthorize("@ss.hasPermission('lghjft:schbwj:create')")
    public CommonResult<String> generateHbData(@RequestBody java.util.Map<String, String> params) {
        String jsrqStart = params.get("jsrqStart");
        String jsrqEnd = params.get("jsrqEnd");
        if (jsrqStart == null || jsrqEnd == null) {
            return success("结算日期起止不能为空");
        }
        String msg = schbwjService.generateHbData(jsrqStart, jsrqEnd, null);
        return success(msg);
    }

    @PutMapping("/update")
    @Operation(summary = "更新实查汇报无结")
    @PreAuthorize("@ss.hasPermission('lghjft:schbwj:update')")
    public CommonResult<Boolean> updateSchbwj(@Valid @RequestBody SchbwjSaveReqVO updateReqVO) {
        schbwjService.updateSchbwj(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除实查汇报无结")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:schbwj:delete')")
    public CommonResult<Boolean> deleteSchbwj(@RequestParam("id") Long id) {
        schbwjService.deleteSchbwj(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得实查汇报无结")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:schbwj:query')")
    public CommonResult<SchbwjResVO> getSchbwj(@RequestParam("id") Long id) {
        JfclSchbwjDO data = schbwjService.getSchbwj(id);
        return success(BeanUtils.toBean(data, SchbwjResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得实查汇报无结分页")
    @PreAuthorize("@ss.hasPermission('lghjft:schbwj:query')")
    public CommonResult<PageResult<JfclSchbwjDO>> getSchbwjPage(@Valid SchbwjPageReqVO pageReqVO) {
        return success(schbwjService.getSchbwjPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出实查汇报无结 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:schbwj:export')")
    public void exportSchbwjExcel(@Valid SchbwjPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfclSchbwjDO> list = schbwjService.getSchbwjPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费划拨文件.xls", "数据", JfclSchbwjDO.class, list);
    }
}
