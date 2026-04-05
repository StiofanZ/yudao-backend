package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swrksj.SwrksjDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.swrksj.SwrksjService;
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

@Tag(name = "管理后台 - 税务入库数据")
@RestController
@RequestMapping("/lghjft/sjwh/swrksj")
@Validated
public class SwrksjController {

    @Resource
    private SwrksjService swrksjService;

    @PostMapping("/create")
    @Operation(summary = "创建税务入库数据")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-swrksj:create')")
    public CommonResult<Long> createSwrksj(@Valid @RequestBody SwrksjSaveReqVO createReqVO) {
        return success(swrksjService.createSwrksj(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新税务入库数据")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-swrksj:update')")
    public CommonResult<Boolean> updateSwrksj(@Valid @RequestBody SwrksjSaveReqVO updateReqVO) {
        swrksjService.updateSwrksj(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除税务入库数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-swrksj:delete')")
    public CommonResult<Boolean> deleteSwrksj(@RequestParam("id") Long id) {
        swrksjService.deleteSwrksj(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除税务入库数据")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-swrksj:delete')")
    public CommonResult<Boolean> deleteSwrksjList(@RequestParam("ids") List<Long> ids) {
        swrksjService.deleteSwrksjListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得税务入库数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-swrksj:query')")
    public CommonResult<SwrksjResVO> getSwrksj(@RequestParam("id") Long id) {
        SwrksjDO swrksj = swrksjService.getSwrksj(id);
        return success(BeanUtils.toBean(swrksj, SwrksjResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得税务入库数据分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-swrksj:query')")
    public CommonResult<PageResult<SwrksjResVO>> getSwrksjPage(@Valid SwrksjPageReqVO pageReqVO) {
        PageResult<SwrksjDO> pageResult = swrksjService.getSwrksjPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SwrksjResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出税务入库数据 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-swrksj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSwrksjExcel(@Valid SwrksjPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SwrksjDO> list = swrksjService.getSwrksjPage(pageReqVO).getList();
        ExcelUtils.write(response, "税务入库数据.xls", "数据", SwrksjResVO.class,
                BeanUtils.toBean(list, SwrksjResVO.class));
    }
}
