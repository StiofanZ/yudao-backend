package cn.iocoder.yudao.module.lghjft.controller.admin.wtfk;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkDO;
import cn.iocoder.yudao.module.lghjft.service.wtfk.WtfkService;

@Tag(name = "管理后台 - 工会经费通-问题反馈")
@RestController
@RequestMapping("/lghjft/wtfk")
@Validated
public class WtfkController {

    @Resource
    private WtfkService wtfkService;

    @PutMapping("/handle-process")
    @Operation(summary = "处理问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:update')")
    public CommonResult<Boolean> handleProcess(@Valid @RequestBody WtfkProcessReqVO handleReqVO) {
        wtfkService.handleProcess(handleReqVO);
        return success(true);
    }
    @GetMapping("/log/list")
    @Operation(summary = "获得问题反馈处理日志列表")
    @Parameter(name = "feedbackId", description = "反馈编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:query')")
// 修改返回值类型，以匹配 Service 的实现
    public CommonResult<List<Map<String, Object>>> getWtfkLogList(@RequestParam("feedbackId") Long feedbackId) {
        return success(wtfkService.getWtfkLogList(feedbackId));
    }

    @PostMapping("/create")
    @Operation(summary = "创建工会经费通-问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:create')")
    public CommonResult<Long> createWtfk(@Valid @RequestBody WtfkSaveReqVO createReqVO) {
        return success(wtfkService.createWtfk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:update')")
    public CommonResult<Boolean> updateWtfk(@Valid @RequestBody WtfkSaveReqVO updateReqVO) {
        wtfkService.updateWtfk(updateReqVO);
        wtfkService.updateWtfk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工会经费通-问题反馈")
    @Parameters({
            @Parameter(name = "id", description = "编号", required = true),
            @Parameter(name = "isAdminView", description = "是否管理端操作", required = false)
    })
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:delete')")
    public CommonResult<Boolean> deleteWtfk(@RequestParam("id") Long id,
                                            @RequestParam(value = "isAdminView", required = false, defaultValue = "false") Boolean isAdminView) {
        wtfkService.deleteWtfk(id, isAdminView);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除工会经费通-问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:delete')")
    public CommonResult<Boolean> deleteWtfkList(
            @RequestParam("ids") List<Long> ids,
            @RequestParam(value = "isAdminView", required = false, defaultValue = "false") Boolean isAdminView) {

        wtfkService.deleteWtfkListByIds(ids, isAdminView);
        return success(true);
    }
    @GetMapping("/get")
    @Operation(summary = "获得工会经费通-问题反馈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:query')")
    public CommonResult<WtfkRespVO> getWtfk(@RequestParam("id") Long id) {
        // 直接调用 Service 中补齐了姓名的方法
        return success(wtfkService.getWtfkDetail(id));
    }

    @Resource
    private AdminUserApi adminUserApi; // 注入系统用户接口
    @GetMapping("/page")
    @Operation(summary = "获得工会经费通-问题反馈分页")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:query')")
    public CommonResult<PageResult<WtfkRespVO>> getWtfkPage(@Valid WtfkPageReqVO pageReqVO) {
        PageResult<WtfkDO> pageResult = wtfkService.getWtfkPage(pageReqVO);
        PageResult<WtfkRespVO> result = BeanUtils.toBean(pageResult, WtfkRespVO.class);
        if (!result.getList().isEmpty()) {
            // 1. 收集所有处理人 ID
            Set<Long> userIds = result.getList().stream()
                    .map(WtfkRespVO::getProcessorId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            // 2. 批量查询用户信息
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            // 3. 设置姓名
            result.getList().forEach(vo -> {
                if (vo.getProcessorId() != null && userMap.containsKey(vo.getProcessorId())) {
                    vo.setProcessorName(userMap.get(vo.getProcessorId()).getNickname());
                }
            });
        }
        return success(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工会经费通-问题反馈 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWtfkExcel(@Valid WtfkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {

        // 设置为不分页，查询全部符合条件的数据
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);

        // 获取数据列表
        List<WtfkDO> list = wtfkService.getWtfkPage(pageReqVO).getList();

        // 导出 Excel
        ExcelUtils.write(response, "工会经费通-问题反馈.xls", "数据", WtfkRespVO.class,
                BeanUtils.toBean(list, WtfkRespVO.class));
    }

}