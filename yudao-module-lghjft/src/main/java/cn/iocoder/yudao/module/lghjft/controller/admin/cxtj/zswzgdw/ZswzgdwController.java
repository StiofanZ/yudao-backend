package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.zswzgdw.ZswzgdwService;
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

@Tag(name = "管理后台 - 征收未主管单位")
@RestController
@RequestMapping("/lghjft/cxtj/zswzgdw")
@Validated
public class ZswzgdwController {

    @Resource
    private ZswzgdwService zswzgdwService;

    @PostMapping("/create")
    @Operation(summary = "创建征收未主管单位")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zswzgdw:create')")
    public CommonResult<String> createZswzgdw(@Valid @RequestBody ZswzgdwSaveReqVO createReqVO) {
        return success(zswzgdwService.createZswzgdw(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新征收未主管单位")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zswzgdw:update')")
    public CommonResult<Boolean> updateZswzgdw(@Valid @RequestBody ZswzgdwSaveReqVO updateReqVO) {
        zswzgdwService.updateZswzgdw(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete/{djxhs}")
    @Operation(summary = "批量删除征收未主管单位")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zswzgdw:delete')")
    public CommonResult<Boolean> deleteZswzgdw(@PathVariable("djxhs") String[] djxhs) {
        zswzgdwService.deleteZswzgdwByDjxhs(djxhs);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得征收未主管单位（含确认列表）")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zswzgdw:query')")
    public CommonResult<ZswzgdwResVO> getZswzgdw(@RequestParam("djxh") String djxh) {
        // V1: selectZswzgdwByDjxh — WITH cascade (returns zswzgdwQrList via LEFT JOIN)
        ZswzgdwDO obj = zswzgdwService.getZswzgdw(djxh);
        ZswzgdwResVO resVO = BeanUtils.toBean(obj, ZswzgdwResVO.class);
        if (resVO != null && obj.getZswzgdwQrList() != null) {
            resVO.setZswzgdwQrList(BeanUtils.toBean(obj.getZswzgdwQrList(), ZswzgdwQrVO.class));
        }
        return success(resVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得征收未主管单位分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zswzgdw:query')")
    public CommonResult<PageResult<ZswzgdwResVO>> getZswzgdwPage(@Valid ZswzgdwPageReqVO pageReqVO) {
        PageResult<ZswzgdwDO> pageResult = zswzgdwService.getZswzgdwPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ZswzgdwResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出征收未主管单位 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zswzgdw:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportZswzgdwExcel(@Valid ZswzgdwPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ZswzgdwDO> list = zswzgdwService.getZswzgdwPage(pageReqVO).getList();
        ExcelUtils.write(response, "征收未主管单位数据.xls", "数据", ZswzgdwResVO.class,
                BeanUtils.toBean(list, ZswzgdwResVO.class));
    }
}
