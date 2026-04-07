package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.zgjrgh.ZgjrghService;
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

@Tag(name = "管理后台 - 金融工会信息核对")
@RestController
@RequestMapping("/lghjft/cxtj/zgjrgh")
@Validated
public class ZgjrghController {

    @Resource
    private ZgjrghService zgjrghService;

    @PostMapping("/create")
    @Operation(summary = "创建金融工会信息核对")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zgjrgh:create')")
    public CommonResult<Long> createZgjrgh(@Valid @RequestBody ZgjrghSaveReqVO createReqVO) {
        return success(zgjrghService.createZgjrgh(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新金融工会信息核对")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zgjrgh:update')")
    public CommonResult<Boolean> updateZgjrgh(@Valid @RequestBody ZgjrghSaveReqVO updateReqVO) {
        zgjrghService.updateZgjrgh(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除金融工会信息核对")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zgjrgh:delete')")
    public CommonResult<Boolean> deleteZgjrgh(@RequestParam("id") Long id) {
        zgjrghService.deleteZgjrgh(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得金融工会信息核对")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zgjrgh:query')")
    public CommonResult<ZgjrghResVO> getZgjrgh(@RequestParam("id") Long id) {
        ZgjrghDO obj = zgjrghService.getZgjrgh(id);
        return success(BeanUtils.toBean(obj, ZgjrghResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得金融工会信息核对分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zgjrgh:query')")
    public CommonResult<PageResult<ZgjrghResVO>> getZgjrghPage(@Valid ZgjrghPageReqVO pageReqVO) {
        PageResult<ZgjrghDO> pageResult = zgjrghService.getZgjrghPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ZgjrghResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出金融工会信息核对 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-zgjrgh:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportZgjrghExcel(@Valid ZgjrghPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ZgjrghDO> list = zgjrghService.getZgjrghPage(pageReqVO).getList();
        ExcelUtils.write(response, "金融工会信息核对.xls", "数据", ZgjrghResVO.class,
                BeanUtils.toBean(list, ZgjrghResVO.class));
    }
}
