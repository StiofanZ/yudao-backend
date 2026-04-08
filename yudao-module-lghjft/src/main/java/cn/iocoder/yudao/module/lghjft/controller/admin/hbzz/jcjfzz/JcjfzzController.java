package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.jcjfzz.JcjfzzService;
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

@Tag(name = "管理后台 - 基层经费到账")
@RestController
@RequestMapping("/lghjft/hbzz/jcjfzz")
@Validated
public class JcjfzzController {

    @Resource
    private JcjfzzService jcjfzzService;

    @PostMapping("/create")
    @Operation(summary = "创建基层经费到账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jcjfzz:create')")
    public CommonResult<Long> createJcjfzz(@Valid @RequestBody JcjfzzSaveReqVO createReqVO) {
        return success(jcjfzzService.createJcjfzz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基层经费到账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jcjfzz:update')")
    public CommonResult<Boolean> updateJcjfzz(@Valid @RequestBody JcjfzzSaveReqVO updateReqVO) {
        jcjfzzService.updateJcjfzz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基层经费到账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jcjfzz:delete')")
    public CommonResult<Boolean> deleteJcjfzz(@RequestParam("id") Long id) {
        jcjfzzService.deleteJcjfzz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除基层经费到账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jcjfzz:delete')")
    public CommonResult<Boolean> deleteJcjfzzList(@RequestParam("ids") List<Long> ids) {
        jcjfzzService.deleteJcjfzzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基层经费到账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jcjfzz:query')")
    public CommonResult<JcjfzzResVO> getJcjfzz(@RequestParam("id") Long id) {
        return success(jcjfzzService.getJcjfzz(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基层经费到账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jcjfzz:query')")
    public CommonResult<PageResult<JcjfzzResVO>> getJcjfzzPage(@Valid JcjfzzPageReqVO pageReqVO) {
        return success(jcjfzzService.getJcjfzzPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基层经费到账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-jcjfzz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJcjfzzExcel(@Valid JcjfzzPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JcjfzzResVO> list = jcjfzzService.getJcjfzzPage(pageReqVO).getList();
        ExcelUtils.write(response, "基层经费到账.xls", "数据", JcjfzzResVO.class, list);
    }
}
