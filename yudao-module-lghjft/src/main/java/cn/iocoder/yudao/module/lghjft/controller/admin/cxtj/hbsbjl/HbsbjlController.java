package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjl.HbsbjlDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.hbsbjl.HbsbjlService;
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

@Tag(name = "管理后台 - 划拨失败记录")
@RestController
@RequestMapping("/lghjft/cxtj/hbsbjl")
@Validated
public class HbsbjlController {

    @Resource
    private HbsbjlService hbsbjlService;

    @PostMapping("/create")
    @Operation(summary = "创建划拨失败记录")
    @PreAuthorize("@ss.hasPermission('lghjft:hbsbjl:create')")
    public CommonResult<Long> createHbsbjl(@Valid @RequestBody HbsbjlSaveReqVO createReqVO) {
        return success(hbsbjlService.createHbsbjl(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新划拨失败记录")
    @PreAuthorize("@ss.hasPermission('lghjft:hbsbjl:update')")
    public CommonResult<Boolean> updateHbsbjl(@Valid @RequestBody HbsbjlSaveReqVO updateReqVO) {
        hbsbjlService.updateHbsbjl(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除划拨失败记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hbsbjl:delete')")
    public CommonResult<Boolean> deleteHbsbjl(@RequestParam("id") Long id) {
        hbsbjlService.deleteHbsbjl(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除划拨失败记录")
    @PreAuthorize("@ss.hasPermission('lghjft:hbsbjl:delete')")
    public CommonResult<Boolean> deleteHbsbjlList(@RequestParam("ids") List<Long> ids) {
        hbsbjlService.deleteHbsbjlListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得划拨失败记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hbsbjl:query')")
    public CommonResult<HbsbjlResVO> getHbsbjl(@RequestParam("id") Long id) {
        HbsbjlDO obj = hbsbjlService.getHbsbjl(id);
        return success(BeanUtils.toBean(obj, HbsbjlResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得划拨失败记录分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbsbjl:query')")
    public CommonResult<PageResult<HbsbjlResVO>> getHbsbjlPage(@Valid HbsbjlPageReqVO pageReqVO) {
        PageResult<HbsbjlDO> pageResult = hbsbjlService.getHbsbjlPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HbsbjlResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出划拨失败记录 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbsbjl:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHbsbjlExcel(@Valid HbsbjlPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HbsbjlDO> list = hbsbjlService.getHbsbjlPage(pageReqVO).getList();
        ExcelUtils.write(response, "划拨失败记录.xls", "数据", HbsbjlResVO.class,
                BeanUtils.toBean(list, HbsbjlResVO.class));
    }
}
