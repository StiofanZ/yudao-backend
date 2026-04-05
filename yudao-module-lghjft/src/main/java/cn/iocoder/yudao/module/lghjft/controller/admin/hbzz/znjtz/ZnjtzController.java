package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.znjtz.ZnjtzDO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.znjtz.ZnjtzService;
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

@Tag(name = "管理后台 - 滞纳金台账")
@RestController
@RequestMapping("/lghjft/hbzz/znjtz")
@Validated
public class ZnjtzController {

    @Resource
    private ZnjtzService znjtzService;

    @PostMapping("/create")
    @Operation(summary = "创建滞纳金台账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjtz:create')")
    public CommonResult<Long> createZnjtz(@Valid @RequestBody ZnjtzSaveReqVO createReqVO) {
        return success(znjtzService.createZnjtz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新滞纳金台账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjtz:update')")
    public CommonResult<Boolean> updateZnjtz(@Valid @RequestBody ZnjtzSaveReqVO updateReqVO) {
        znjtzService.updateZnjtz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除滞纳金台账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjtz:delete')")
    public CommonResult<Boolean> deleteZnjtz(@RequestParam("id") Long id) {
        znjtzService.deleteZnjtz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除滞纳金台账")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjtz:delete')")
    public CommonResult<Boolean> deleteZnjtzList(@RequestParam("ids") List<Long> ids) {
        znjtzService.deleteZnjtzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得滞纳金台账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjtz:query')")
    public CommonResult<ZnjtzResVO> getZnjtz(@RequestParam("id") Long id) {
        ZnjtzDO znjtz = znjtzService.getZnjtz(id);
        return success(BeanUtils.toBean(znjtz, ZnjtzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得滞纳金台账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjtz:query')")
    public CommonResult<PageResult<ZnjtzResVO>> getZnjtzPage(@Valid ZnjtzPageReqVO pageReqVO) {
        PageResult<ZnjtzDO> pageResult = znjtzService.getZnjtzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ZnjtzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出滞纳金台账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-znjtz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportZnjtzExcel(@Valid ZnjtzPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ZnjtzDO> list = znjtzService.getZnjtzPage(pageReqVO).getList();
        ExcelUtils.write(response, "滞纳金台账.xls", "数据", ZnjtzResVO.class,
                BeanUtils.toBean(list, ZnjtzResVO.class));
    }
}
