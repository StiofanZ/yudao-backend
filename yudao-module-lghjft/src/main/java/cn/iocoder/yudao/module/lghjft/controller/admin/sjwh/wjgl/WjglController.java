package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.wjgl.WjglService;
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

@Tag(name = "管理后台 - 文件管理")
@RestController
@RequestMapping("/lghjft/wjgl")
@Validated
public class WjglController {

    @Resource
    private WjglService fileService;

    @GetMapping("/page")
    @Operation(summary = "获得文件管理分页")
    @PreAuthorize("@ss.hasPermission('lghjft:wjgl:query')")
    public CommonResult<PageResult<WjglResVO>> getFilePage(@Valid WjglPageReqVO pageReqVO) {
        return success(fileService.getFilePage(pageReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得文件管理详情")
    @Parameter(name = "fileid", description = "文件编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:wjgl:query')")
    public CommonResult<WjglResVO> getFile(@RequestParam("fileid") Long fileid) {
        return success(fileService.getFile(fileid));
    }

    @PostMapping("/create")
    @Operation(summary = "创建文件管理")
    @PreAuthorize("@ss.hasPermission('lghjft:wjgl:create')")
    public CommonResult<Boolean> createFile(@Valid @RequestBody WjglSaveReqVO createReqVO) {
        fileService.createFile(createReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新文件管理")
    @PreAuthorize("@ss.hasPermission('lghjft:wjgl:update')")
    public CommonResult<Boolean> updateFile(@Valid @RequestBody WjglSaveReqVO updateReqVO) {
        fileService.updateFile(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文件管理")
    @Parameter(name = "fileids", description = "文件编号数组", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:wjgl:delete')")
    public CommonResult<Boolean> deleteFiles(@RequestParam("fileids") Long[] fileids) {
        fileService.deleteFiles(fileids);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出文件管理 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:wjgl:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFileExcel(@Valid WjglPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        List<WjglResVO> list = fileService.getFileList(pageReqVO);
        ExcelUtils.write(response, "文件管理.xls", "数据", WjglResVO.class, list);
    }
}
