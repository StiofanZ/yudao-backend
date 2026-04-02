package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.yjhxx.GhYjhxxDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.yjhxx.GhYjhxxService;
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

@Tag(name = "管理后台 - 已建会信息")
@RestController
@RequestMapping("/lghjft/cxtj/yjhxx")
@Validated
public class GhYjhxxController {

    @Resource
    private GhYjhxxService ghYjhxxService;

    @PostMapping("/create")
    @Operation(summary = "创建已建会信息")
    @PreAuthorize("@ss.hasPermission('lghjft:yjhxx:create')")
    public CommonResult<Long> createGhYjhxx(@Valid @RequestBody GhYjhxxSaveReqVO createReqVO) {
        return success(ghYjhxxService.createGhYjhxx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新已建会信息")
    @PreAuthorize("@ss.hasPermission('lghjft:yjhxx:update')")
    public CommonResult<Boolean> updateGhYjhxx(@Valid @RequestBody GhYjhxxSaveReqVO updateReqVO) {
        ghYjhxxService.updateGhYjhxx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除已建会信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:yjhxx:delete')")
    public CommonResult<Boolean> deleteGhYjhxx(@RequestParam("id") Long id) {
        ghYjhxxService.deleteGhYjhxx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除已建会信息")
    @PreAuthorize("@ss.hasPermission('lghjft:yjhxx:delete')")
    public CommonResult<Boolean> deleteGhYjhxxList(@RequestParam("ids") List<Long> ids) {
        ghYjhxxService.deleteGhYjhxxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得已建会信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:yjhxx:query')")
    public CommonResult<GhYjhxxResVO> getGhYjhxx(@RequestParam("id") Long id) {
        GhYjhxxDO obj = ghYjhxxService.getGhYjhxx(id);
        return success(BeanUtils.toBean(obj, GhYjhxxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得已建会信息分页")
    @PreAuthorize("@ss.hasPermission('lghjft:yjhxx:query')")
    public CommonResult<PageResult<GhYjhxxResVO>> getGhYjhxxPage(@Valid GhYjhxxPageReqVO pageReqVO) {
        PageResult<GhYjhxxDO> pageResult = ghYjhxxService.getGhYjhxxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhYjhxxResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出已建会信息 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:yjhxx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGhYjhxxExcel(@Valid GhYjhxxPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhYjhxxDO> list = ghYjhxxService.getGhYjhxxPage(pageReqVO).getList();
        ExcelUtils.write(response, "已建会信息.xls", "数据", GhYjhxxResVO.class,
                BeanUtils.toBean(list, GhYjhxxResVO.class));
    }
}
