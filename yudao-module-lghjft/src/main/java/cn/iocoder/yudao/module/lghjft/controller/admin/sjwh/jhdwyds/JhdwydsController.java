package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds;
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

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.jhdwyds.JhdwydsService;

@Tag(name = "管理后台 - 应代收单位")
@RestController
@RequestMapping("/lghjft/jhdwyds")
@Validated
public class JhdwydsController {

    @Resource
    private JhdwydsService jhdwydsService;

    @PostMapping("/create")
    @Operation(summary = "创建应代收单位")
    @PreAuthorize("@ss.hasPermission('lghjft:jhdwyds:create')")
    public CommonResult<Integer> createJhdwyds(@Valid @RequestBody JhdwydsSaveReqVO createReqVO) {
        return success(jhdwydsService.createJhdwyds(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应代收单位")
    @PreAuthorize("@ss.hasPermission('lghjft:jhdwyds:update')")
    public CommonResult<Boolean> updateJhdwyds(@Valid @RequestBody JhdwydsSaveReqVO updateReqVO) {
        jhdwydsService.updateJhdwyds(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应代收单位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jhdwyds:delete')")
    public CommonResult<Boolean> deleteJhdwyds(@RequestParam("id") Integer id) {
        jhdwydsService.deleteJhdwyds(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除应代收单位")
                @PreAuthorize("@ss.hasPermission('lghjft:jhdwyds:delete')")
    public CommonResult<Boolean> deleteJhdwydsList(@RequestParam("ids") List<Integer> ids) {
        jhdwydsService.deleteJhdwydsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应代收单位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jhdwyds:query')")
    public CommonResult<JhdwydsRespVO> getJhdwyds(@RequestParam("id") Integer id) {
        JhdwydsDO jhdwyds = jhdwydsService.getJhdwyds(id);
        return success(BeanUtils.toBean(jhdwyds, JhdwydsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应代收单位分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jhdwyds:query')")
    public CommonResult<PageResult<JhdwydsRespVO>> getJhdwydsPage(@Valid JhdwydsPageReqVO pageReqVO) {
        PageResult<JhdwydsDO> pageResult = jhdwydsService.getJhdwydsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JhdwydsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应代收单位 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:jhdwyds:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJhdwydsExcel(@Valid JhdwydsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JhdwydsDO> list = jhdwydsService.getJhdwydsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应代收单位.xls", "数据", JhdwydsRespVO.class,
                        BeanUtils.toBean(list, JhdwydsRespVO.class));
    }

}