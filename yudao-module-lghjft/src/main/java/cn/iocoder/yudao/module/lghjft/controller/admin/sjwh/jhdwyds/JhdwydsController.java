package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.jhdwyds.JhdwydsService;
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

@Tag(name = "管理后台 - 应代收单位")
@RestController
@RequestMapping("/lghjft/sjwh/jhdwyds")
@Validated
public class JhdwydsController {

    @Resource
    private JhdwydsService JhdwydsService;

    @PostMapping("/create")
    @Operation(summary = "创建应代收单位")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jhdwyds:create')")
    public CommonResult<Integer> createjhdwyds(@Valid @RequestBody JhdwydsSaveReqVO createReqVO) {
        return success(JhdwydsService.createjhdwyds(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应代收单位")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jhdwyds:update')")
    public CommonResult<Boolean> updatejhdwyds(@Valid @RequestBody JhdwydsSaveReqVO updateReqVO) {
        JhdwydsService.updatejhdwyds(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应代收单位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jhdwyds:delete')")
    public CommonResult<Boolean> deletejhdwyds(@RequestParam("id") Integer id) {
        JhdwydsService.deletejhdwyds(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除应代收单位")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jhdwyds:delete')")
    public CommonResult<Boolean> deletejhdwydsList(@RequestParam("ids") List<Integer> ids) {
        JhdwydsService.deletejhdwydsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应代收单位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jhdwyds:query')")
    public CommonResult<JhdwydsResVO> getjhdwyds(@RequestParam("id") Integer id) {
        JhdwydsDO jhdwyds = JhdwydsService.getjhdwyds(id);
        return success(BeanUtils.toBean(jhdwyds, JhdwydsResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应代收单位分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jhdwyds:query')")
    public CommonResult<PageResult<JhdwydsResVO>> getjhdwydsPage(@Valid JhdwydsPageReqVO pageReqVO) {
        PageResult<JhdwydsDO> pageResult = JhdwydsService.getjhdwydsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JhdwydsResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应代收单位 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jhdwyds:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportjhdwydsExcel(@Valid JhdwydsPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JhdwydsDO> list = JhdwydsService.getjhdwydsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应代收单位.xls", "数据", JhdwydsResVO.class,
                BeanUtils.toBean(list, JhdwydsResVO.class));
    }

}