package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk.FydsqkDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.fydsqk.FydsqkService;
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

@Tag(name = "管理后台 - 分月代收情况")
@RestController
@RequestMapping("/lghjft/cxtj/fydsqk")
@Validated
public class FydsqkController {

    @Resource
    private FydsqkService fydsqkService;

    @PostMapping("/create")
    @Operation(summary = "创建分月代收情况")
    @PreAuthorize("@ss.hasPermission('lghjft:fydsqk:create')")
    public CommonResult<String> createFydsqk(@Valid @RequestBody FydsqkSaveReqVO createReqVO) {
        return success(fydsqkService.createFydsqk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分月代收情况")
    @PreAuthorize("@ss.hasPermission('lghjft:fydsqk:update')")
    public CommonResult<Boolean> updateFydsqk(@Valid @RequestBody FydsqkSaveReqVO updateReqVO) {
        fydsqkService.updateFydsqk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分月代收情况")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:fydsqk:delete')")
    public CommonResult<Boolean> deleteFydsqk(@RequestParam("id") String id) {
        fydsqkService.deleteFydsqk(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除分月代收情况")
    @PreAuthorize("@ss.hasPermission('lghjft:fydsqk:delete')")
    public CommonResult<Boolean> deleteFydsqkList(@RequestParam("ids") List<String> ids) {
        fydsqkService.deleteFydsqkListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分月代收情况")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:fydsqk:query')")
    public CommonResult<FydsqkResVO> getFydsqk(@RequestParam("id") String id) {
        FydsqkDO obj = fydsqkService.getFydsqk(id);
        return success(BeanUtils.toBean(obj, FydsqkResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分月代收情况分页")
    @PreAuthorize("@ss.hasPermission('lghjft:fydsqk:query')")
    public CommonResult<PageResult<FydsqkResVO>> getFydsqkPage(@Valid FydsqkPageReqVO pageReqVO) {
        PageResult<FydsqkDO> pageResult = fydsqkService.getFydsqkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FydsqkResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分月代收情况 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:fydsqk:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFydsqkExcel(@Valid FydsqkPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FydsqkDO> list = fydsqkService.getFydsqkPage(pageReqVO).getList();
        ExcelUtils.write(response, "分月代收情况.xls", "数据", FydsqkResVO.class,
                BeanUtils.toBean(list, FydsqkResVO.class));
    }
}
