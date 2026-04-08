package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfmxwh.JfmxwhDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.jfmxwh.JfmxwhService;
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

@Tag(name = "管理后台 - 经费明细维护")
@RestController
@RequestMapping("/lghjft/jfcl/jfmxwh")
@Validated
public class JfmxwhController {

    @Resource
    private JfmxwhService jfmxwhService;

    @PostMapping("/create")
    @Operation(summary = "创建经费明细维护")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfmxwh:create')")
    public CommonResult<Long> createJfmxwh(@Valid @RequestBody JfmxwhSaveReqVO createReqVO) {
        return success(jfmxwhService.createJfmxwh(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经费明细维护")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfmxwh:update')")
    public CommonResult<Boolean> updateJfmxwh(@Valid @RequestBody JfmxwhSaveReqVO updateReqVO) {
        jfmxwhService.updateJfmxwh(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经费明细维护")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfmxwh:delete')")
    public CommonResult<Boolean> deleteJfmxwh(@RequestParam("id") Long id) {
        jfmxwhService.deleteJfmxwh(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除经费明细维护")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfmxwh:delete')")
    public CommonResult<Boolean> deleteJfmxwhList(@RequestParam("ids") List<Long> ids) {
        jfmxwhService.deleteJfmxwhList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得经费明细维护")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfmxwh:query')")
    public CommonResult<JfmxwhResVO> getJfmxwh(@RequestParam("id") Long id) {
        JfmxwhDO data = jfmxwhService.getJfmxwh(id);
        return success(BeanUtils.toBean(data, JfmxwhResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经费明细维护分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfmxwh:query')")
    public CommonResult<PageResult<JfmxwhResVO>> getJfmxwhPage(@Valid JfmxwhPageReqVO pageReqVO) {
        PageResult<JfmxwhDO> pageResult = jfmxwhService.getJfmxwhPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JfmxwhResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费明细维护 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfmxwh:export')")
    public void exportJfmxwhExcel(@Valid JfmxwhPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfmxwhDO> list = jfmxwhService.getJfmxwhPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费明细维护.xls", "数据", JfmxwhResVO.class,
                BeanUtils.toBean(list, JfmxwhResVO.class));
    }
}
