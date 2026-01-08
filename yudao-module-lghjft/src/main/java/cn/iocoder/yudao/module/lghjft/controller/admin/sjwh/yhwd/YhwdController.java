package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo.YhwdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo.YhwdRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo.YhwdSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.yhwd.YhwdDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.yhwd.YhwdService;
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

@Tag(name = "管理后台 - 银行网点")
@RestController
@RequestMapping("/dm/yhwd")
@Validated
public class YhwdController {

    @Resource
    private YhwdService yhwdService;

    @PostMapping("/create")
    @Operation(summary = "创建银行网点")
    @PreAuthorize("@ss.hasPermission('dm:yhwd:create')")
    public CommonResult<Long> createYhwd(@Valid @RequestBody YhwdSaveReqVO createReqVO) {
        return success(yhwdService.createYhwd(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新银行网点")
    @PreAuthorize("@ss.hasPermission('dm:yhwd:update')")
    public CommonResult<Boolean> updateYhwd(@Valid @RequestBody YhwdSaveReqVO updateReqVO) {
        yhwdService.updateYhwd(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除银行网点")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('dm:yhwd:delete')")
    public CommonResult<Boolean> deleteYhwd(@RequestParam("id") Long id) {
        yhwdService.deleteYhwd(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除银行网点")
                @PreAuthorize("@ss.hasPermission('dm:yhwd:delete')")
    public CommonResult<Boolean> deleteYhwdList(@RequestParam("ids") List<Long> ids) {
        yhwdService.deleteYhwdListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得银行网点")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('dm:yhwd:query')")
    public CommonResult<YhwdRespVO> getYhwd(@RequestParam("id") Long id) {
        YhwdDO yhwd = yhwdService.getYhwd(id);
        return success(BeanUtils.toBean(yhwd, YhwdRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得银行网点分页")
    @PreAuthorize("@ss.hasPermission('dm:yhwd:query')")
    public CommonResult<PageResult<YhwdRespVO>> getYhwdPage(@Valid YhwdPageReqVO pageReqVO) {
        PageResult<YhwdDO> pageResult = yhwdService.getYhwdPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, YhwdRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出银行网点 Excel")
    @PreAuthorize("@ss.hasPermission('dm:yhwd:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportYhwdExcel(@Valid YhwdPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<YhwdDO> list = yhwdService.getYhwdPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "银行网点.xls", "数据", YhwdRespVO.class,
                        BeanUtils.toBean(list, YhwdRespVO.class));
    }

}