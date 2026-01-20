package cn.iocoder.yudao.module.lghjft.controller.admin.wtfk;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkLogDO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
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
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

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
    public CommonResult<List<WtfkLogDO>> getWtfkLogList(@RequestParam("feedbackId") Long feedbackId) {
        return success(wtfkService.getWtfkLogList(feedbackId));
    }

    @PostMapping("/create")
    @Operation(summary = "创建工会经费通-问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:create')")
    public CommonResult<Long> createWtfk(@Valid @RequestBody WtfkSaveReqVO createReqVO) {
        return success(wtfkService.createWtfk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工会经费通-问题反馈")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:update')")
    public CommonResult<Boolean> updateWtfk(@Valid @RequestBody WtfkSaveReqVO updateReqVO) {
        wtfkService.updateWtfk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工会经费通-问题反馈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:delete')")
    public CommonResult<Boolean> deleteWtfk(@RequestParam("id") Long id) {
        wtfkService.deleteWtfk(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除工会经费通-问题反馈")
                @PreAuthorize("@ss.hasPermission('lghjft:wtfk:delete')")
    public CommonResult<Boolean> deleteWtfkList(@RequestParam("ids") List<Long> ids) {
        wtfkService.deleteWtfkListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工会经费通-问题反馈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:wtfk:query')")
    public CommonResult<WtfkRespVO> getWtfk(@RequestParam("id") Long id) {
        WtfkDO wtfk = wtfkService.getWtfk(id);
        return success(BeanUtils.toBean(wtfk, WtfkRespVO.class));
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
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WtfkDO> list = wtfkService.getWtfkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "工会经费通-问题反馈.xls", "数据", WtfkRespVO.class,
                        BeanUtils.toBean(list, WtfkRespVO.class));
    }

}