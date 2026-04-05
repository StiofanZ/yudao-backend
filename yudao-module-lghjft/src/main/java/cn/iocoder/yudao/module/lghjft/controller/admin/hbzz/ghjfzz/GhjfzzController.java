package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo.GhjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo.GhjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo.GhjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.ghjfzz.GhjfzzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 工会经费做账")
@RestController
@RequestMapping("/lghjft/hbzz/ghjfzz")
@Validated
public class GhjfzzController {

    @Resource
    private GhjfzzService ghjfzzService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/dept-yhzh")
    @Operation(summary = "获取部门银行账号")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-ghjfzz:query')")
    public CommonResult<Map<String, Object>> getDeptYhzh(@RequestParam("deptId") Long deptId) {
        try {
            Map<String, Object> dept = jdbcTemplate.queryForMap(
                    "SELECT yhzh, yhzh1, yhzh2, yhzh3 FROM sys_dept WHERE dept_id = ?", deptId);
            return success(dept);
        } catch (Exception e) {
            return success(null);
        }
    }

    @PostMapping("/create")
    @Operation(summary = "创建工会经费做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-ghjfzz:create')")
    public CommonResult<Long> createGhjfzz(@Valid @RequestBody GhjfzzSaveReqVO createReqVO) {
        return success(ghjfzzService.createGhjfzz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工会经费做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-ghjfzz:update')")
    public CommonResult<Boolean> updateGhjfzz(@Valid @RequestBody GhjfzzSaveReqVO updateReqVO) {
        ghjfzzService.updateGhjfzz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工会经费做账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-ghjfzz:delete')")
    public CommonResult<Boolean> deleteGhjfzz(@RequestParam("id") Long id) {
        ghjfzzService.deleteGhjfzz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除工会经费做账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-ghjfzz:delete')")
    public CommonResult<Boolean> deleteGhjfzzList(@RequestParam("ids") List<Long> ids) {
        ghjfzzService.deleteGhjfzzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工会经费做账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-ghjfzz:query')")
    public CommonResult<GhjfzzResVO> getGhjfzz(@RequestParam("id") Long id) {
        return success(ghjfzzService.getGhjfzz(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工会经费做账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-ghjfzz:query')")
    public CommonResult<PageResult<GhjfzzResVO>> getGhjfzzPage(@Valid GhjfzzPageReqVO pageReqVO) {
        return success(ghjfzzService.getGhjfzzPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工会经费做账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-ghjfzz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGhjfzzExcel(@Valid GhjfzzPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhjfzzResVO> list = ghjfzzService.getGhjfzzPage(pageReqVO).getList();
        ExcelUtils.write(response, "工会经费做账.xls", "数据", GhjfzzResVO.class, list);
    }
}
