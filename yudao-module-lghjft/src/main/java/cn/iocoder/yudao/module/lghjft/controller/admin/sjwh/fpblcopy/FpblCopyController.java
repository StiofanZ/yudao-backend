package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fpblcopy;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fpblcopy.vo.FpblCopyPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fpblcopy.vo.FpblCopyRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fpblcopy.vo.FpblCopySaveReqVO;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.fpblcopy.FpblCopyDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.fpblcopy.FpblCopyService;
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


@Tag(name = "管理后台 - 分配比例")
@RestController
@RequestMapping("/dm/fpbl-copy")
@Validated
public class FpblCopyController {

    @Resource
    private FpblCopyService fpblCopyService;

    @PostMapping("/create")
    @Operation(summary = "创建分配比例")
    @PreAuthorize("@ss.hasPermission('dm:fpbl-copy:create')")
    public CommonResult<Integer> createFpblCopy(@Valid @RequestBody FpblCopySaveReqVO createReqVO) {
        // 执行自定义业务校验
        createReqVO.validateBusinessRules();
        return success(fpblCopyService.createFpblCopy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分配比例")
    @PreAuthorize("@ss.hasPermission('dm:fpbl-copy:update')")
    public CommonResult<Boolean> updateFpblCopy(@Valid @RequestBody FpblCopySaveReqVO updateReqVO) {
        updateReqVO.validateBusinessRules();
        fpblCopyService.updateFpblCopy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分配比例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('dm:fpbl-copy:delete')")
    public CommonResult<Boolean> deleteFpblCopy(@RequestParam("id") Integer id) {
        fpblCopyService.deleteFpblCopy(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除分配比例")
                @PreAuthorize("@ss.hasPermission('dm:fpbl-copy:delete')")
    public CommonResult<Boolean> deleteFpblCopyList(@RequestParam("ids") List<Integer> ids) {
        fpblCopyService.deleteFpblCopyListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分配比例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('dm:fpbl-copy:query')")
    public CommonResult<FpblCopyRespVO> getFpblCopy(@RequestParam("id") Integer id) {
        FpblCopyDO fpblCopy = fpblCopyService.getFpblCopy(id);
        return success(BeanUtils.toBean(fpblCopy, FpblCopyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分配比例分页")
    @PreAuthorize("@ss.hasPermission('dm:fpbl-copy:query')")
    public CommonResult<PageResult<FpblCopyRespVO>> getFpblCopyPage(@Valid FpblCopyPageReqVO pageReqVO) {
        PageResult<FpblCopyDO> pageResult = fpblCopyService.getFpblCopyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FpblCopyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分配比例 Excel")
    @PreAuthorize("@ss.hasPermission('dm:fpbl-copy:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFpblCopyExcel(@Valid FpblCopyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FpblCopyDO> list = fpblCopyService.getFpblCopyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "分配比例.xls", "数据", FpblCopyRespVO.class,
                        BeanUtils.toBean(list, FpblCopyRespVO.class));
    }

}