package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjlyxg.HbsbjlyxgDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.hbsbjlyxg.HbsbjlyxgService;
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

@Tag(name = "管理后台 - 划拨失败已修改")
@RestController
@RequestMapping("/lghjft/cxtj/hbsbjlyxg")
@Validated
public class HbsbjlyxgController {

    @Resource
    private HbsbjlyxgService hbsbjlyxgService;

    @PostMapping("/create")
    @Operation(summary = "创建划拨失败已修改")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-hbsbjlyxg:create')")
    public CommonResult<Long> createHbsbjlyxg(@Valid @RequestBody HbsbjlyxgSaveReqVO createReqVO) {
        return success(hbsbjlyxgService.createHbsbjlyxg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新划拨失败已修改")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-hbsbjlyxg:update')")
    public CommonResult<Boolean> updateHbsbjlyxg(@Valid @RequestBody HbsbjlyxgSaveReqVO updateReqVO) {
        hbsbjlyxgService.updateHbsbjlyxg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除划拨失败已修改")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-hbsbjlyxg:delete')")
    public CommonResult<Boolean> deleteHbsbjlyxg(@RequestParam("id") Long id) {
        hbsbjlyxgService.deleteHbsbjlyxg(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除划拨失败已修改")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-hbsbjlyxg:delete')")
    public CommonResult<Boolean> deleteHbsbjlyxgList(@RequestParam("ids") List<Long> ids) {
        hbsbjlyxgService.deleteHbsbjlyxgListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得划拨失败已修改")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-hbsbjlyxg:query')")
    public CommonResult<HbsbjlyxgResVO> getHbsbjlyxg(@RequestParam("id") Long id) {
        HbsbjlyxgDO obj = hbsbjlyxgService.getHbsbjlyxg(id);
        return success(BeanUtils.toBean(obj, HbsbjlyxgResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得划拨失败已修改分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-hbsbjlyxg:query')")
    public CommonResult<PageResult<HbsbjlyxgResVO>> getHbsbjlyxgPage(@Valid HbsbjlyxgPageReqVO pageReqVO) {
        PageResult<HbsbjlyxgDO> pageResult = hbsbjlyxgService.getHbsbjlyxgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HbsbjlyxgResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出划拨失败已修改 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-hbsbjlyxg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHbsbjlyxgExcel(@Valid HbsbjlyxgPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HbsbjlyxgDO> list = hbsbjlyxgService.getHbsbjlyxgPage(pageReqVO).getList();
        ExcelUtils.write(response, "划拨失败已修改.xls", "数据", HbsbjlyxgResVO.class,
                BeanUtils.toBean(list, HbsbjlyxgResVO.class));
    }
}
