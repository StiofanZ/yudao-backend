package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk.GhNrglWtfkDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.wtfk.GhNrglWtfkService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 问题反馈")
@RestController
@RequestMapping("/lghjft/nrgl/wtfk")
@Validated
public class GhNrglWtfkController {

    @Resource
    private GhNrglWtfkService wtfkService;
    @Resource
    private AdminUserApi adminUserApi;

    @PutMapping("/handle-process")
    @Operation(summary = "处理问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:update')")
    public CommonResult<Boolean> handleProcess(@Valid @RequestBody GhNrglWtfkClReqVO handleReqVO) {
        wtfkService.handleProcess(handleReqVO);
        return success(true);
    }

    @GetMapping("/log/list")
    @Operation(summary = "获得问题反馈处理明细列表")
    @Parameter(name = "wtfkId", description = "问题反馈ID", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:query')")
    public CommonResult<List<GhNrglWtfkClmxResVO>> getGhNrglWtfkClmxList(@RequestParam("wtfkId") Long wtfkId) {
        return success(wtfkService.getGhNrglWtfkClmxList(wtfkId));
    }

    @PostMapping("/create")
    @Operation(summary = "创建问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:create')")
    public CommonResult<Long> createGhNrglWtfk(@Valid @RequestBody GhNrglWtfkSaveReqVO createReqVO) {
        return success(wtfkService.createGhNrglWtfk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:update')")
    public CommonResult<Boolean> updateGhNrglWtfk(@Valid @RequestBody GhNrglWtfkSaveReqVO updateReqVO) {
        wtfkService.updateGhNrglWtfk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除问题反馈")
    @Parameters({
            @Parameter(name = "id", description = "编号", required = true),
            @Parameter(name = "isAdminView", description = "是否管理端视图")
    })
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:delete')")
    public CommonResult<Boolean> deleteGhNrglWtfk(@RequestParam("id") Long id,
                                                  @RequestParam(value = "isAdminView", defaultValue = "false") Boolean isAdminView) {
        wtfkService.deleteGhNrglWtfk(id, isAdminView);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:delete')")
    public CommonResult<Boolean> deleteGhNrglWtfkList(@RequestParam("ids") List<Long> ids,
                                                      @RequestParam(value = "isAdminView", defaultValue = "false") Boolean isAdminView) {
        wtfkService.deleteGhNrglWtfkListByIds(ids, isAdminView);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得问题反馈详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:query')")
    public CommonResult<GhNrglWtfkResVO> getGhNrglWtfk(@RequestParam("id") Long id) {
        return success(wtfkService.getGhNrglWtfkDetail(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得问题反馈分页")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:query')")
    public CommonResult<PageResult<GhNrglWtfkResVO>> getGhNrglWtfkPage(@Valid GhNrglWtfkPageReqVO pageReqVO) {
        PageResult<GhNrglWtfkDO> pageResult = wtfkService.getGhNrglWtfkPage(pageReqVO);
        PageResult<GhNrglWtfkResVO> result = BeanUtils.toBean(pageResult, GhNrglWtfkResVO.class);
        fillClrmc(result.getList());
        return success(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出问题反馈 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-wtfk:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGhNrglWtfkExcel(@Valid GhNrglWtfkPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GhNrglWtfkResVO> list = BeanUtils.toBean(wtfkService.getGhNrglWtfkPage(pageReqVO).getList(), GhNrglWtfkResVO.class);
        fillClrmc(list);
        ExcelUtils.write(response, "问题反馈.xls", "数据", GhNrglWtfkResVO.class, list);
    }

    private void fillClrmc(List<GhNrglWtfkResVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Set<Long> clrIds = list.stream().map(GhNrglWtfkResVO::getClrId).filter(Objects::nonNull).collect(Collectors.toSet());
        if (clrIds.isEmpty()) {
            return;
        }
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(clrIds);
        list.forEach(item -> {
            if (item.getClrId() != null && item.getClrmc() == null) {
                AdminUserRespDTO user = userMap.get(item.getClrId());
                if (user != null) {
                    item.setClrmc(user.getNickname());
                }
            }
        });
    }
}
